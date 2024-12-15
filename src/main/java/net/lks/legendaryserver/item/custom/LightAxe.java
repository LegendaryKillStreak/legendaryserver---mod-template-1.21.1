package net.lks.legendaryserver.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class LightAxe extends AxeItem {
    public LightAxe(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 36000,1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,200,1));
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,80,0));

        super.postDamageEntity(stack, target, attacker);
    }

}
