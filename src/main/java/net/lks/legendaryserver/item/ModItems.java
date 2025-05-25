package net.lks.legendaryserver.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lks.legendaryserver.LegendaryServerMod;
import net.lks.legendaryserver.item.custom.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {


    public static final  Item LIGHT_SWORD = registerItem("light_sword",
            new LightSword(ModToolMaterials.LIGHT, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.LIGHT, 3, -2.4f))));

    public static final  Item LIGHT_PICKAXE = registerItem("light_pickaxe",
            new LightPickaxe(ModToolMaterials.LIGHT, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.LIGHT, 1, -2.8f))));

    public static final  Item LIGHT_AXE = registerItem("light_axe",
            new LightAxe(ModToolMaterials.LIGHT, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.LIGHT, 6, -3.2f))));

    public static final  Item LIGHT_SHOVEL = registerItem("light_shovel",
            new LightShovel(ModToolMaterials.LIGHT, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.LIGHT, 1.5f, -3.0f))));

    public static final  Item LIGHT_HOE = registerItem("light_hoe",
            new LightHoe(ModToolMaterials.LIGHT, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.LIGHT, 0, -3f))));





    public static final Item DARKNESS_SWORD = registerItem("darkness_sword",
            new DarknessSword(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.DARK, 3, -2.4f))));

    public static final Item DARKNESS_HOE = registerItem("darkness_hoe",
            new DarknessHoe(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.DARK, 0, -3f))));

    public static final Item DARKNESS_PICKAXE = registerItem("darkness_pickaxe",
            new DarknessPickaxe(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.DARK, 1, -2.8f))));

    public static final Item DARKNESS_AXE = registerItem("darkness_axe",
            new DarknessAxe(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.DARK, 6, -3.2f))));

    public static final Item DARKNESS_SHOVEL = registerItem("darkness_shovel",
            new DarknessShovel(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.DARK, 1.5f, -3f))));



    public static final  Item LIGHT_CORE = registerItem("light_core", new Item(new Item.Settings()));
    public static final  Item DARK_CORE = registerItem("dark_core", new Item(new Item.Settings()));

    public static final Item COIN = registerItem("coin", new Item(new Item.Settings()));

    public static final Item COIN_FIVE = registerItem("coin_five", new Item(new Item.Settings()));
    public static final Item COIN_TEN = registerItem("coin_ten", new Item(new Item.Settings()));
    public static final Item COIN_TWENTY = registerItem("coin_twenty", new Item(new Item.Settings()));
    public static final Item COIN_FIFTY = registerItem("coin_fifty", new Item(new Item.Settings()));
    public static final Item COIN_100 = registerItem("coin_100", new Item(new Item.Settings()));
    public static final Item COIN_150 = registerItem("coin_150", new Item(new Item.Settings()));
    public static final Item COIN_200 = registerItem("coin_200", new Item(new Item.Settings()));
    public static final Item COIN_250 = registerItem("coin_250", new Item(new Item.Settings()));
    public static final Item COIN_500 = registerItem("coin_500", new Item(new Item.Settings()));
    public static final Item COIN_750 = registerItem("coin_750", new Item(new Item.Settings()));
    public static final Item COIN_1000 = registerItem("coin_1000", new Item(new Item.Settings()));
    public static final Item COIN_2000 = registerItem("coin_2000", new Item(new Item.Settings()));
    public static final Item COIN_5000 = registerItem("coin_5000", new Item(new Item.Settings()));
    public static final Item COIN_10000 = registerItem("coin_10000", new Item(new Item.Settings()));
    public static final Item COIN_15000 = registerItem("coin_15000", new Item(new Item.Settings()));
    public static final Item COIN_100000 = registerItem("coin_100000", new Item(new Item.Settings()));


    public static final Item DUNGEON_KEY_SHADOWY_CREATURES = registerItem("dungeon_key_shadowy_creatures", new Item(new Item.Settings()));
    public static final Item DUNGEON_KEY_ICY_CAVES = registerItem("dungeon_key_icy_caves", new Item(new Item.Settings()));







    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendaryServerMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LegendaryServerMod.LOGGER.info("Registering Mod Items for " + LegendaryServerMod.MOD_ID);



        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(LIGHT_CORE);
            entries.add(ModItems.LIGHT_SWORD);
            entries.add(ModItems.DARK_CORE);
        });
    }
}