package net.lks.legendaryserver.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class LightSword extends SwordItem {
    public LightSword(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 3600,1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,3,1));
    return super.postHit(stack, target, attacker);
    }



}
