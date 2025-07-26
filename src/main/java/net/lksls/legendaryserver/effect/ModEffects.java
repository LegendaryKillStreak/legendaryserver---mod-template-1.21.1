package net.lksls.legendaryserver.effect;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {


    public static final RegistryEntry<StatusEffect> RADIATION = registerStatusEffect("radiation",
            new RadiationEffect(StatusEffectCategory.HARMFUL, 0x00FFD3)
                    .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            Identifier.of(LegendaryServerMod.MOD_ID, "radiation"),-0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(LegendaryServerMod.MOD_ID, name), statusEffect);
    }




    public static void registerEffects() {
        LegendaryServerMod.LOGGER.info("Registering Mod Effects for "+ LegendaryServerMod.MOD_ID);
    }
}
