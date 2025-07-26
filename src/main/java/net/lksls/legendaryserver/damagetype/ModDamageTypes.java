package net.lksls.legendaryserver.damagetype;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {
    // This defines the RegistryKey for your "radiation" damage type.
    // The Identifier.of("yourmodid", "radiation") links to your JSON file:
    // data/yourmodid/damage_type/radiation.json
    public static final RegistryKey<DamageType> RADIATION = register("radiation");

    // Helper method to create RegistryKeys for DamageTypes
    private static RegistryKey<DamageType> register(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("lksls", name));
    }

    // This method is often called in your main mod's onInitialize()
    // to ensure these static fields are initialized early.
    public static void registerModDamageTypes() {
        // Calling this method simply forces the static fields above to be initialized.
        // There's no explicit Fabric API registration call here, as RegistryKeys
        // are just identifiers; the actual DamageType is registered via JSON/datapacks.
    }
}