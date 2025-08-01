package net.lksls.legendaryserver.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class LightSword extends SwordItem {
    public LightSword(ToolMaterial toolMaterial, Settings settings) {
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
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,400,1));

        super.postDamageEntity(stack, target, attacker);
    }
    @Override
    public Text getName(ItemStack stack) {
        return Text.literal("Sword of Light")
                .formatted(Formatting.RED);
    }



}
