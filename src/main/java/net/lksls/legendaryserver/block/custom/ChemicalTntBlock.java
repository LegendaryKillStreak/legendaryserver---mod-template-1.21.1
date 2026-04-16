package net.lksls.legendaryserver.block.custom;

import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.block.entity.custom.ChemicalTntEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ChemicalTntBlock extends Block {

    public ChemicalTntBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (!world.isClient) {

            ChemicalTntEntity tnt = new ChemicalTntEntity(
                    ModBlockEntities.CHEMICAL_TNT_ENTITY,
                    world
            );

            tnt.refreshPositionAndAngles(
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    0.0f,
                    0.0f
            );

            tnt.setFuse(40);
            world.spawnEntity(tnt);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world,
                                             BlockPos pos, PlayerEntity player, Hand hand,
                                             BlockHitResult hit) {

        if (stack.isOf(Items.FLINT_AND_STEEL) || stack.isOf(Items.FIRE_CHARGE)) {

            if (!world.isClient) {

                ChemicalTntEntity tnt = new ChemicalTntEntity(
                        ModBlockEntities.CHEMICAL_TNT_ENTITY,
                        world
                );

                tnt.refreshPositionAndAngles(
                        pos.getX() + 0.5,
                        pos.getY(),
                        pos.getZ() + 0.5,
                        0.0f,
                        0.0f
                );

                tnt.setFuse(40);
                world.spawnEntity(tnt);
                world.removeBlock(pos, false);
            }

            stack.damage(1, player, EquipmentSlot.MAINHAND);
            return ItemActionResult.SUCCESS;
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}

