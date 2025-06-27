package net.lksls.legendaryserver.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal("Darkness Axe")
                .formatted(Formatting.RED);
    }

}
