package net.lks.legendaryserver.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lks.legendaryserver.LegendaryServerMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final  Item LIGHT_SWORD = registerItem("light_sword", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendaryServerMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LegendaryServerMod.LOGGER.info("Registering Mod Items for " + LegendaryServerMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(LIGHT_SWORD);
        });

    }
}