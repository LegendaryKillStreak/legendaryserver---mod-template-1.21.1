package net.lksls.legendaryserver.block.custom;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.block.entity.custom.CoreFusionerBlockEntity;
import net.lksls.legendaryserver.block.entity.custom.RefineryBlockEntity;
import net.lksls.legendaryserver.screen.custom.CoreFusionerScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;

import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;

import org.jetbrains.annotations.Nullable;

public class CoreFusionerBlock extends BlockWithEntity {

    public static final BooleanProperty LIT = Properties.LIT;

    public CoreFusionerBlock(Settings settings) {
        super(settings.luminance(state -> state.get(LIT) ? 12 : 0));
        setDefaultState(getDefaultState().with(LIT, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL; // model should have variants for LIT=true/false
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CoreFusionerBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type
    ) {
        return type == ModBlockEntities.CORE_FUSIONER_BE
                ? (w, p, s, be) -> CoreFusionerBlockEntity.tick(w, p, s, (CoreFusionerBlockEntity) be)
                : null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) {

        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof CoreFusionerBlockEntity fusioner)) {
            return ActionResult.PASS;
        }

        player.openHandledScreen(new ExtendedScreenHandlerFactory<BlockPos>() {

            @Override
            public Text getDisplayName() {
                return Text.literal("Core Fusioner");
            }

            @Override
            public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
                return pos;
            }

            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                return new CoreFusionerScreenHandler(syncId, inv, pos);
            }
        });

        return ActionResult.SUCCESS;
    }

}

