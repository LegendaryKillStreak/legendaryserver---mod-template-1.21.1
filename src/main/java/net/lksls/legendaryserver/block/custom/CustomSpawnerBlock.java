package net.lksls.legendaryserver.block.custom;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.block.entity.custom.CustomSpawnerBlockEntity;
import net.lksls.legendaryserver.screen.custom.CustomSpawnerScreenHandler;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CustomSpawnerBlock extends BlockWithEntity {

    public CustomSpawnerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null; // oder richtige Codec-Implementierung, wenn du sie brauchst
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {

            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                BlockEntity be = world.getBlockEntity(pos);

                if (be instanceof CustomSpawnerBlockEntity spawner) {

                    // Drop all stored items
                    ItemScatterer.spawn(world, pos, spawner.getStorage());

                    // Drop the core slot
                    ItemScatterer.spawn(world, pos, spawner.getCore());

                    // Drop XP as orbs
                    long xp = spawner.getXpStored();
                    if (xp > 0) {
                        ExperienceOrbEntity.spawn(serverWorld, Vec3d.ofCenter(pos), (int) xp);
                    }

                    // Drop spawner blocks equal to stack count
                    int stackCount = spawner.getStackCount();
                    if (stackCount > 0) {
                        ItemStack drop = new ItemStack(ModBlocks.CUSTOM_SPAWNER_BLOCK, stackCount);
                        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), drop);
                    }

                    world.removeBlockEntity(pos);
                }
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }





    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CustomSpawnerBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type
    ) {
        if (world.isClient) return null;

        return type == ModBlockEntities.CUSTOM_SPAWNER_BE
                ? (w, p, s, be) -> CustomSpawnerBlockEntity.tick(w, p, s, (CustomSpawnerBlockEntity) be)
                : null;
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) {

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CustomSpawnerBlockEntity spawner)) {
            return ActionResult.PASS;
        }

        // Normal right-click = stack spawners or open GUI
        if (!world.isClient) {

            // Player is holding a spawner block?
            ItemStack held = player.getMainHandStack();
            if (held.getItem() == ModBlocks.CUSTOM_SPAWNER_BLOCK.asItem()) {

                if (spawner.getStackCount() < CustomSpawnerBlockEntity.MAX_STACK) {

                    // Increase stack count
                    spawner.addSpawnerToStack(1);
                    held.decrement(1);

                    // ⭐ Play stacking sound
                    world.playSound(
                            null,
                            pos,
                            SoundEvents.BLOCK_ANVIL_USE,   // choose any sound you like
                            SoundCategory.BLOCKS,
                            1.0f,
                            1.0f
                    );

                    return ActionResult.SUCCESS;
                }
            }

            // Open GUI
            player.openHandledScreen(new ExtendedScreenHandlerFactory<BlockPos>() {
                @Override
                public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
                    return pos;
                }

                @Override
                public Text getDisplayName() {
                    return Text.literal("Spawner");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity p) {
                    return new CustomSpawnerScreenHandler(syncId, inv, pos);
                }
            });
        }

        return ActionResult.SUCCESS;
    }



}

