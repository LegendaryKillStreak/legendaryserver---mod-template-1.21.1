package net.lksls.legendaryserver.block.custom;

import com.mojang.serialization.MapCodec;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.block.entity.custom.RefineryBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RefineryBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<RefineryBlock> CODEC = createCodec(RefineryBlock::new);
    public static final BooleanProperty LIT = Properties.LIT;


    public RefineryBlock(Settings settings) {
        super(settings); this.setDefaultState(
                this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT); }

    @Override public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RefineryBlockEntity(pos, state);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    //*@Override
    //*protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        //*if (state.getBlock() != newState.getBlock()) {
            //*BlockEntity blockEntity = world.getBlockEntity(pos);
            //*if (blockEntity instanceof RefineryBlockEntity) {
                //*ItemScatterer.spawn(world, pos, ((RefineryBlockEntity) blockEntity));
                //*world.updateComparators(pos, this);
                //*}
            //*super.onStateReplaced(state, world, pos, newState, moved);

            //*}
        //*}

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            if (world.getBlockEntity(pos) instanceof RefineryBlockEntity refineryBlockEntity) {
                ItemScatterer.spawn(world, pos, refineryBlockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    //*this was part of the testing, that i learned during kaupenjoes tutorial specifically for block entities
//* @Override
//*protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
    //*                                          PlayerEntity player, Hand hand, BlockHitResult hit) {
        //*if (world.getBlockEntity(pos) instanceof RefineryBlockEntity refineryBlockEntity) {
            //*if (refineryBlockEntity.isEmpty() && !stack.isEmpty()) {
                //*refineryBlockEntity.setStack(0, stack);
                //*world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1, 2f);
                //*stack.decrement(1);

                //*refineryBlockEntity.markDirty();
                //*world.updateListeners(pos, state, state, 0);

                //*} else if (stack.isEmpty() && !player.isSneaking()) {
                //*ItemStack stackOnRefinery = refineryBlockEntity.getStack(0);
                //*player.setStackInHand(Hand.MAIN_HAND, stackOnRefinery);
                //*world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1, 1f);
                //*refineryBlockEntity.clear();

                //*refineryBlockEntity.markDirty();
                //*world.updateListeners(pos, state, state, 0);
                //*} else if (player.isSneaking() && !world.isClient()) {
                //*player.openHandledScreen(refineryBlockEntity);

                //*}

            //*}
        //*     return ItemActionResult.SUCCESS;

//*    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((RefineryBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ItemActionResult.SUCCESS;
    }


    //*@Override
    //*protected VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {

    //* return super.getRaycastShape(state, world, pos);
    //*}


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.REFINERY_BE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            world.playSound(xPos, yPos, zPos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
        }

        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52 : defaultOffset;

        world.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.BUBBLE_POP, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);

        if(world.getBlockEntity(pos) instanceof RefineryBlockEntity refineryBlockEntity && !refineryBlockEntity.getStack(1).isEmpty()) {
            world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, refineryBlockEntity.getStack(1)),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}
