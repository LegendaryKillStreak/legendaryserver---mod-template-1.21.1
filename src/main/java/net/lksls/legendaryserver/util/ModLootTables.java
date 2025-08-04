package net.lksls.legendaryserver.util;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTables {
    public static RegistryKey<LootTable> FORGOTTEN_GRAVE = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(LegendaryServerMod.MOD_ID, "chests/forgotten_grave"));
}