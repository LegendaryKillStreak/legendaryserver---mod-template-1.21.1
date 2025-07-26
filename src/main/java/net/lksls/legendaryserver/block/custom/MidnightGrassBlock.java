package net.lksls.legendaryserver.block.custom;

import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.world.ModConfiguredFeatures;
import net.lksls.legendaryserver.world.ModPlacedFeatures;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;




// Assuming ModBlocks is correctly imported and defined elsewhere
// import your.package.ModBlocks; // Example import

public class MidnightGrassBlock extends PillarBlock implements Fertilizable {

    public MidnightGrassBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState stateAbove = world.getBlockState(pos.up());

        // --- Decay Logic ---
        // Check if there is a block above that is NOT air
        if (!stateAbove.isAir()) {
            // If the block above is NOT a standard plant, a replaceable plant (like grass),
            // one of your custom plants, or your Midnightwood Sapling, then decay.
            if (!stateAbove.isIn(BlockTags.FLOWERS) && // Check for common flowers tag
                    !stateAbove.isOf(Blocks.SHORT_GRASS) && // Explicitly check for vanilla short grass
                    !stateAbove.isOf(Blocks.TALL_GRASS) && // Explicitly check for vanilla tall grass
                    !stateAbove.isOf(Blocks.FERN) && // Explicitly check for vanilla fern
                    !stateAbove.isOf(Blocks.LARGE_FERN) && // Explicitly check for vanilla large fern
                    !stateAbove.isOf(Blocks.DEAD_BUSH) && // Explicitly check for vanilla dead bush
                    !stateAbove.isOf(Blocks.DANDELION) && // Explicitly check for vanilla dandelion
                    !stateAbove.isOf(Blocks.POPPY) && // Explicitly check for vanilla poppy
                    // Add any other vanilla plants you want to consider "non-decaying" here
                    !stateAbove.isOf(ModBlocks.LOST_GRASS) && // Your custom plant
                    !stateAbove.isOf(ModBlocks.ENDLESS_VIOLET) && // Your custom plant
                    !stateAbove.isOf(ModBlocks.MIDNIGHTWOOD_SAPLING)) // ADD THIS LINE for your sapling
            {
                world.setBlockState(pos, ModBlocks.DARKROOT_SOIL.getDefaultState()); // Ensure ModBlocks is correctly imported and defined
                return; // Stop further processing for this tick
            }
        }

        // --- Spreading Logic (similar to vanilla grass_block) ---
        // Only attempt to spread if the current block has enough light
        if (world.getLightLevel(pos.up()) >= 9) {
            // Iterate through a fixed number of attempts to find a suitable neighbor
            for (int i = 0; i < 4; ++i) { // It's common to try 4 or more times for spreading
                // Get a random horizontal direction
                Direction randomHorizontalDirection = Direction.Type.HORIZONTAL.random(random);
                BlockPos neighborPos = pos.offset(randomHorizontalDirection);
                BlockState neighborState = world.getBlockState(neighborPos);

                // Check if the neighbor block is Darkroot Soil or Dirt
                // And if the block above the neighbor is air and has enough light
                if ((neighborState.isOf(ModBlocks.DARKROOT_SOIL) || neighborState.isOf(Blocks.DIRT)) &&
                        world.getLightLevel(neighborPos.up()) >= 4 && // Light level for the block above the target
                        world.getBlockState(neighborPos.up()).isAir()) {

                    // Random chance to spread (similar to vanilla grass)
                    if (random.nextInt(10) == 0) { // 10% chance to spread to a suitable neighbor
                        world.setBlockState(neighborPos, this.getDefaultState());
                    }
                }
            }
        }

        super.randomTick(state, world, pos, random);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isAir(); // Only fertilizable if the block above is air
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        // Can grow if it's fertilizable
        return this.isFertilizable(world, pos, state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        // Define the radius for spreading
        int radius = 2; // e.g., 2 blocks in each direction (5x5 square area)
        final int ENDLESS_VIOLET_CHANCE = 30; // 1 in 30 chance for Endless Violet

        // Iterate through a square area around the fertilized block
        for (BlockPos iteratePos : BlockPos.iterate(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius))) {
            // Check if the current block in the iteration is MidnightGrassBlock
            // and if the block above it is air
            if (world.getBlockState(iteratePos).isOf(this) && world.getBlockState(iteratePos.up()).isAir()) {
                // Determine which plant to grow: Lost Grass is common, Endless Violet is rare
                BlockState plantToGrow;
                if (random.nextInt(ENDLESS_VIOLET_CHANCE) == 0) { // Check if random number is 0 (1/250 chance)
                    plantToGrow = ModBlocks.ENDLESS_VIOLET.getDefaultState();
                } else {
                    plantToGrow = ModBlocks.LOST_GRASS.getDefaultState();
                }

                // 50% chance to grow a plant on suitable MidnightGrassBlock
                if (random.nextBoolean()) {
                    world.setBlockState(iteratePos.up(), plantToGrow, 3); // 3 for Block.NOTIFY_ALL
                }
            }
        }
    }
}