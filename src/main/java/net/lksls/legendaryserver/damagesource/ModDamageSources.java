package net.lksls.legendaryserver.damagesource;

import net.lksls.legendaryserver.damagetype.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;

public class ModDamageSources {
    private final RegistryWrapper.WrapperLookup registryLookup;
    private final RegistryEntry<net.minecraft.entity.damage.DamageType> radiationDamageTypeEntry;

    public ModDamageSources(RegistryWrapper.WrapperLookup registryLookup) {
        this.registryLookup = registryLookup;

        // This is where the RegistryEntry is obtained.
        // This part should now be working for you.
        RegistryWrapper.Impl<net.minecraft.entity.damage.DamageType> damageTypeRegistryWrapper =
                registryLookup.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE);
        this.radiationDamageTypeEntry =
                damageTypeRegistryWrapper.getOrThrow(ModDamageTypes.RADIATION);
    }

    // Method to create a Radiation DamageSource without a specific attacker or position
    public DamageSource radiation(LivingEntity attacker) {
        return new DamageSource(this.radiationDamageTypeEntry, attacker);
    }

    // Optional: Method to create a Radiation DamageSource originating from an entity
    public DamageSource radiation(Entity source, LivingEntity attacker) {
        return new DamageSource(this.radiationDamageTypeEntry, source, attacker);
    }

    // Optional: Method to create a Radiation DamageSource at a specific position
    // NOTE: Due to API limitations reported (private constructor for Vec3d, no withPosition),
    // the Vec3d coordinates (x, y, z) cannot be directly embedded into the DamageSource object itself
    // through public API in this version.
    // You will need to handle the 'x, y, z' coordinates at the point where the damage is applied,
    // or rely on the 'source' entity's position if applicable.
    public DamageSource radiationAt(Entity source, LivingEntity attacker, double x, double y, double z) {
        // Create the DamageSource using the available public constructor.
        // The specific position (x, y, z) will need to be used by the code that calls this method
        // to apply the damage, as the DamageSource object itself cannot store it directly here.
        return new DamageSource(this.radiationDamageTypeEntry, source, attacker);
    }

    public RegistryWrapper.WrapperLookup getRegistryLookup() {
        return registryLookup;
    }
}
