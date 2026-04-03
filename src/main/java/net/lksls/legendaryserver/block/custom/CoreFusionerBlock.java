package net.lksls.legendaryserver.block.custom;

import com.mojang.serialization.MapCodec;
import net.lksls.legendaryserver.block.entity.custom.CoreFusionerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CoreFusionerBlock extends BlockWithEntity {
    public CoreFusionerBlock(Settings settings) {super(settings);}

    @Override
    protected VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {

        return super.getRaycastShape(state, world, pos);
    }

    public static final MapCodec<CoreFusionerBlock> CODEC = CoreFusionerBlock.createCodec(CoreFusionerBlock::new);

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CoreFusionerBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CoreFusionerBlockEntity) {
                ItemScatterer.spawn(world, pos, ((CoreFusionerBlockEntity) blockEntity));
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof  CoreFusionerBlockEntity coreFusionerBlockEntity) {
            if (coreFusionerBlockEntity.isEmpty() && !stack.isEmpty()) {
                coreFusionerBlockEntity.setStack(0, stack);
                world.playSound(player,pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                stack.decrement(1);

            } else if (stack.isEmpty()) {
                ItemStack stackOnCoreFusioner = coreFusionerBlockEntity.getStack(0);
                player.setStackInHand(Hand.MAIN_HAND, stackOnCoreFusioner);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                coreFusionerBlockEntity.clear();

            }
        }


        return ItemActionResult.SUCCESS;

    }
}

