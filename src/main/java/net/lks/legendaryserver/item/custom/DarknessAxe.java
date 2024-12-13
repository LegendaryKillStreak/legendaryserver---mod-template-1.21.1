package net.lks.legendaryserver.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class DarknessAxe extends AxeItem {
    public DarknessAxe(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,140, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.UNLUCK,600,1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,40,1));
        return super.postHit(stack, target, attacker);
    }
}
