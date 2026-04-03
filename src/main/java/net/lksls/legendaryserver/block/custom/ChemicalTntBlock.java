package net.lksls.legendaryserver.block.custom;

import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.block.entity.custom.ChemicalTntEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class ChemicalTntBlock{ //**extends TntBlock**// {

//    public ChemicalTntBlock(Settings settings) {
//        super(settings);
//    }
//
//    // TNT ignited by explosions
//    @Override
//    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
//        if (!world.isClient) {
//            ChemicalTntEntity tnt = new ChemicalTntEntity(ModBlockEntities.CHEMICAL_TNT_BE, world);
//            tnt.setFuse(40); // custom fuse
//            tnt.refreshPositionAndAngles(
//                    pos.getX() + 0.5,
//                    pos.getY(),
//                    pos.getZ() + 0.5,
//                    0, 0
//            );
//            world.spawnEntity(tnt);
//        }
//    }
//
//    // TNT ignited by flint & steel or fire charge
//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos,
//                              PlayerEntity player, Hand hand, BlockHitResult hit) {
//
//        ItemStack item = player.getStackInHand(hand);
//
//        if (item.isOf(Items.FLINT_AND_STEEL) || item.isOf(Items.FIRE_CHARGE)) {
//            if (!world.isClient) {
//                ChemicalTntEntity tnt = new ChemicalTntEntity(ModBlockEntities.CHEMICAL_TNT_BE, world);
//                tnt.setFuse(40);
//                tnt.refreshPositionAndAngles(
//                        pos.getX() + 0.5,
//                        pos.getY(),
//                        pos.getZ() + 0.5,
//                        0, 0
//                );
//                world.spawnEntity(tnt);
//
//                world.removeBlock(pos, false);
//            }
//
//            item.damage(1, player, p -> p.sendToolBreakStatus(hand));
//            return ActionResult.SUCCESS;
//        }
//
//        return ActionResult.PASS;
//    }
}


