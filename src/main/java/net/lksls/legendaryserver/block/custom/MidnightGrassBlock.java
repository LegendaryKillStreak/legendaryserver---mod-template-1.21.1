package net.lksls.legendaryserver.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.lksls.legendaryserver.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

// Assuming ModBlocks is correctly defined and contains MIDNIGHT_GRASS and DARKROOT_SOIL
// Example: import your.package.ModBlocks;

public class MidnightGrassBlock extends SpreadableBlock {
    // Define the CODEC for MidnightGrassBlock specifically.
    // This is required because SpreadableBlock's CODEC might be typed for SnowyBlock
    // or another specific subclass, leading to type mismatches.
    public static final MapCodec<MidnightGrassBlock> CODEC = createCodec(MidnightGrassBlock::new);

    // Your block's constructor should include settings.ticksRandomly()
    // Example:
    public MidnightGrassBlock(Settings settings) {
        super(settings.ticksRandomly());
    }

    /**
     * Determines if this block can be placed at the given position by a player.
     * This method mimics GrassBlock's placement rules: it can be placed on its
     * 'dirt' equivalent (DarkrootSoil) or another MidnightGrass block,
     * provided there is air directly above the placement position.
     *
     * @param state The BlockState of this block.
     * @param world The world where the block is being placed.
     * @param pos The position where the block is being placed.
     * @return True if the block can be placed, false otherwise.
     */


    /**
     * Called when a block is placed in the world (by player or world generation)
     * or when it is loaded (e.g., chunk loading).
     * This method no longer schedules an immediate tick, mirroring vanilla GrassBlock.
     * Grass blocks rely on the world's random tick system for their behavior.
     *
     * @param world The world.
     * @param pos The position of the block.
     * @param state The BlockState of the block.
     * @param placer The entity that placed the block (can be null for world gen).
     * @param itemStack The item stack used to place the block.
     */
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        // Debugging: confirm placement
        System.out.println("MidnightGrass: onPlaced at " + pos);
        // Grass blocks do not schedule an immediate tick upon placement.
        // They rely on the world's random tick system.
    }

    /**
     * Called when a scheduled tick for this block occurs (via random ticks).
     * This method implements the decay and spreading logic, mimicking a GrassBlock.
     * - It decays to DarkrootSoil if conditions for survival are not met (e.g., solid block above, insufficient light).
     * - If it survives, it attempts to spread to adjacent DarkrootSoil blocks under sufficient light conditions.
     *
     * @param state The BlockState of this block.
     * @param world The server world.
     * @param pos The position of this block.
     * @param random A random object provided by the game's ticking system.
     */
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Debugging: scheduledTick fired
        System.out.println("MidnightGrass: scheduledTick fired at " + pos);

        // First, check if the MidnightGrass block should decay to DarkrootSoil.
        // This happens if a solid block is placed above it or if light conditions are unfavorable.
        if (!canMidnightGrassSurvive(state, world, pos)) {
            // Debugging: Print when transformation occurs and check if DarkrootSoil is null
            System.out.println("MidnightGrass: Decaying at " + pos + ". ModBlocks.DARKROOT_SOIL is null: " + (ModBlocks.DARKROOT_SOIL == null));
            if (ModBlocks.DARKROOT_SOIL != null) {
                System.out.println("MidnightGrass: Transforming " + pos + " to " + ModBlocks.DARKROOT_SOIL.getName().getString());
                world.setBlockState(pos, ModBlocks.DARKROOT_SOIL.getDefaultState(), Block.NOTIFY_ALL);
            } else {
                System.err.println("ERROR: ModBlocks.DARKROOT_SOIL is null! Cannot transform MidnightGrass.");
                // Fallback to vanilla dirt if DarkrootSoil is not available (for debugging purposes)
                world.setBlockState(pos, Blocks.DIRT.getDefaultState(), Block.NOTIFY_ALL);
            }
        } else {
            // If the MidnightGrass block survives, it can attempt to spread.
            // Spreading only occurs if the sky light level at the block above is 9 or higher.
            if (world.getLightLevel(LightType.SKY, pos.up()) >= 9) {
                BlockState midnightGrassState = this.getDefaultState(); // The BlockState for MidnightGrass

                // Attempt to spread to 4 random adjacent blocks.
                for (int i = 0; i < 4; ++i) {
                    // Generate a random position within a 3x3x3 cube centered on the current block.
                    BlockPos targetPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    BlockState targetBlockState = world.getBlockState(targetPos);

                    // Debugging: Spreading attempt
                    System.out.println("MidnightGrass: Spreading attempt from " + pos + " to " + targetPos +
                            " - Target Block: " + targetBlockState.getBlock().getName().getString() +
                            ", Is DarkrootSoil: " + targetBlockState.isOf(ModBlocks.DARKROOT_SOIL));

                    // Check if the target block is DarkrootSoil and if MidnightGrass can spread to it.
                    // The canMidnightGrassSpread method checks light conditions and if the block above the target is not solid.
                    if (targetBlockState.isOf(ModBlocks.DARKROOT_SOIL) && canMidnightGrassSpread(midnightGrassState, world, targetPos)) {
                        // Debugging: Successful spread
                        System.out.println("MidnightGrass: Spreading from " + pos + " to " + targetPos + " (transforming to MidnightGrass)");
                        // Transform the DarkrootSoil block into MidnightGrass.
                        world.setBlockState(targetPos, midnightGrassState, Block.NOTIFY_ALL);
                    }
                }
            }
            // IMPORTANT: Do NOT reschedule a tick here. Grass blocks rely on the world's
            // random tick system to call scheduledTick, not self-scheduling.
        }
    }

    /**
     * Helper method to check if the MidnightGrass block can survive at its current position.
     * Mimics SpreadableBlock's `canSurvive` logic.
     * Conditions for survival:
     * - No solid block directly above it.
     * - Sufficient light: Sky light level >= 9 OR Block light level >= 7 at its position.
     *
     * @param state The BlockState of this block.
     * @param world The world.
     * @param pos The position of this block.
     * @return True if the block can survive, false otherwise (will decay).
     */
    protected boolean canMidnightGrassSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPosAbove = pos.up();
        BlockState blockStateAbove = world.getBlockState(blockPosAbove);

        // Debugging: Check block above for survival
        System.out.println("MidnightGrass: canSurvive check at " + pos +
                " - Block Above: " + blockStateAbove.getBlock().getName().getString() +
                ", isOpaqueFullCube: " + blockStateAbove.isOpaqueFullCube(world, blockPosAbove));

        // If there's a full solid block (like stone, dirt, etc.) above, it cannot survive.
        // `isOpaqueFullCube` is used to check for solid blocks that completely fill their space.
        if (blockStateAbove.isOpaqueFullCube(world, blockPosAbove)) {
            return false;
        }

        // Check light levels. Grass requires adequate light to survive.
        int skyLight = world.getLightLevel(LightType.SKY, pos);
        int blockLight = world.getLightLevel(LightType.BLOCK, pos);

        // Debugging: Light levels for survival
        System.out.println("MidnightGrass: canSurvive check at " + pos +
                " - Sky Light: " + skyLight + ", Block Light: " + blockLight);

        // It survives if sky light is 9 or more, OR block light is 7 or more.
        return skyLight >= 9 || blockLight >= 7;
    }

    /**
     * Helper method to check if MidnightGrass can spread to a target position.
     * Mimics SpreadableBlock's `canSpread` logic.
     * Conditions for spreading:
     * - The grass block itself must be able to survive (using `canMidnightGrassSurvive`).
     * - The block directly above the target position must NOT be a solid block.
     *
     * @param state The BlockState of the MidnightGrass block (used for checking survival conditions).
     * @param world The world.
     * @param pos The target position where spreading is being considered.
     * @return True if MidnightGrass can spread to the target position, false otherwise.
     */
    protected boolean canMidnightGrassSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPosAboveTarget = pos.up();
        BlockState blockStateAboveTarget = world.getBlockState(blockPosAboveTarget);

        // Debugging: Check block above target for spreading
        System.out.println("MidnightGrass: canSpread check at " + pos +
                " - Block Above Target: " + blockStateAboveTarget.getBlock().getName().getString() +
                ", isOpaqueFullCube: " + blockStateAboveTarget.isOpaqueFullCube(world, blockPosAboveTarget));

        // It can spread if the source grass block can survive AND the block above the target
        // position is not a solid block (meaning it's air, a plant, etc., allowing light through).
        return canMidnightGrassSurvive(state, world, pos) && !blockStateAboveTarget.isOpaqueFullCube(world, blockPosAboveTarget);
    }

    /**
     * Implements the abstract method 'getCodec()' from 'SpreadableBlock'.
     * This method provides the MapCodec for serializing and deserializing the block's state.
     *
     * @return A MapCodec for MidnightGrassBlock.
     */
    @Override
    protected MapCodec<? extends SpreadableBlock> getCodec() {
        return CODEC; // Return the specific CODEC for MidnightGrassBlock
    }





}