package net.lksls.legendaryserver.enchantment;

import com.mojang.serialization.MapCodec;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.enchantment.custom.ChargedEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> CHARGED =
            registerEntityEffect("charged", ChargedEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(LegendaryServerMod.MOD_ID, name), codec);
    }


    public static void registerEnchantmentEffects() {
        LegendaryServerMod.LOGGER.info("Registering Mod Enchantment Effects for" + LegendaryServerMod.MOD_ID);
    }
}
