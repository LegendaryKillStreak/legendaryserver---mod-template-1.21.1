package net.lks.legendaryserver.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class DarknessPickaxe extends PickaxeItem {
    public DarknessPickaxe(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,20, 1));

        return super.postHit(stack, target, attacker);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof ServerPlayerEntity player && selected) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,40,1));
        }


        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
