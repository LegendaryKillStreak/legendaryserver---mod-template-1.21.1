package net.lksls.legendaryserver.effect;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.damagesource.ModDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class RadiationEffect extends StatusEffect {

        protected RadiationEffect(StatusEffectCategory category, int color) {
            super(category, color);
        }

        @Override
        public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
            // Always check if on the server side for damage application
            if (!entity.getWorld().isClient()) {
                // Get the ModDamageSources instance from your main mod class
                ModDamageSources modDamageSources = LegendaryServerMod.getDamageSources(entity.getWorld());

                float damage = 2.0f + (amplifier * 1.5f);

                if (entity.isAlive()) {
                    // Use the radiation damage source provided by your helper
                    entity.damage(modDamageSources.radiation(null), damage);
                }
            }
            return true;
        }

        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            int ticksPerDamage = 400; // 20 second
            return duration % ticksPerDamage == 0;
        }
    }
