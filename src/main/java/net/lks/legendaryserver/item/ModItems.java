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