package net.lks.legendaryserver.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DarknessSword extends SwordItem {
    public DarknessSword(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,140, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.UNLUCK,600,1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,60,1));
        return super.postHit(stack, target, attacker);
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal("Darkness Sword")
                .formatted(Formatting.RED);
    }


}
