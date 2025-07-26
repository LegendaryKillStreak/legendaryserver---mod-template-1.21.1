package net.lksls.legendaryserver.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.item.custom.*;
import net.lksls.legendaryserver.sound.ModSounds;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.lksls.legendaryserver.item.custom.GammaRadiatingXenthrite;



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
    public static final  Item GAMMA_RADIATING_XENTHRITE = registerItem("gamma_radiating_xenthrite",
            new GammaRadiatingXenthrite(new Item.Settings().rarity(Rarity.EPIC)));
    public static final  Item BETA_RADIATING_XENTHRITE = registerItem("beta_radiating_xenthrite",
            new BetaRadiatingXenthrite(new Item.Settings().rarity(Rarity.EPIC)));





    public static final Item DARKNESS_SWORD = registerItem("darkness_sword",
            new DarknessSword(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.DARK, 3, -2.4f))));

    public static final Item DARKNESS_HOE = registerItem("darkness_hoe",
            new DarknessHoe(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.DARK, 0, -3f))));

    public static final Item DARKNESS_PICKAXE = registerItem("darkness_pickaxe",
            new DarknessPickaxe(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.DARK, 1, -2.8f))));

    public static final Item DARKNESS_DRILL = registerItem("darkness_drill",
            new DarkDrill(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.DARK, -10, -1f))));

    public static final Item DARKNESS_AXE = registerItem("darkness_axe",
            new DarknessAxe(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.DARK, 6, -3.2f))));

    public static final Item DARKNESS_SHOVEL = registerItem("darkness_shovel",
            new DarknessShovel(ModToolMaterials.DARK, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.DARK, 1.5f, -3f))));

    public static final Item LIGHT_CORE_HELMET = registerItem("light_core_helmet",
            new ModArmorItem(ModArmorMaterials.LIGHT_CORE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(41))));
    public static final Item LIGHT_CORE_CHESTPLATE = registerItem("light_core_chestplate",
            new ModArmorItem(ModArmorMaterials.LIGHT_CORE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(41))));
    public static final Item LIGHT_CORE_LEGGINGS = registerItem("light_core_leggings",
            new ArmorItem(ModArmorMaterials.LIGHT_CORE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(41))));
    public static final Item LIGHT_CORE_BOOTS = registerItem("light_core_boots",
            new ModArmorItem(ModArmorMaterials.LIGHT_CORE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(41))));

    public static final Item DARK_CORE_HELMET = registerItem("dark_core_helmet",
            new ModArmorItem(ModArmorMaterials.DARK_CORE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(54))));
    public static final Item DARK_CORE_CHESTPLATE = registerItem("dark_core_chestplate",
            new ModArmorItem(ModArmorMaterials.DARK_CORE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(54))));
    public static final Item DARK_CORE_LEGGINGS = registerItem("dark_core_leggings",
            new ArmorItem(ModArmorMaterials.DARK_CORE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(54))));
    public static final Item DARK_CORE_BOOTS = registerItem("dark_core_boots",
            new ModArmorItem(ModArmorMaterials.DARK_CORE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(54))));




    public static final  Item LIGHT_CORE = registerItem("light_core", new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final  Item DARK_CORE = registerItem("dark_core", new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final  Item DARK_SOUL_CORE = registerItem("dark_soul_core", new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final  Item DARK_SHARD = registerItem("dark_shard", new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final  Item IMPURE_NOCTYRIAN = registerItem("impure_noctyrian", new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final  Item DIM_ROSE_QUARTZ = registerItem("dim_rose_quartz", new Item(new Item.Settings().rarity(Rarity.RARE)));

    public static final  Item DRILL_ENGINE = registerItem("drill_engine", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final Item ECHO_DUST = registerItem("echo_dust", new Item(new Item.Settings()));





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
    public static final Item COIN_20000 = registerItem("coin_20000", new Item(new Item.Settings()));
    public static final Item COIN_50000 = registerItem("coin_50000", new Item(new Item.Settings()));
    public static final Item COIN_75000 = registerItem("coin_75000", new Item(new Item.Settings()));
    public static final Item COIN_100000 = registerItem("coin_100000", new Item(new Item.Settings()));
    public static final Item COIN_250000 = registerItem("coin_250000", new Item(new Item.Settings()));
    public static final Item COIN_500000 = registerItem("coin_500000", new Item(new Item.Settings()));
    public static final Item COIN_25000 = registerItem("coin_25000", new Item(new Item.Settings()));
    public static final Item COIN_200000 = registerItem("coin_200000", new Item(new Item.Settings()));
    public static final Item COIN_150000 = registerItem("coin_150000", new Item(new Item.Settings()));



    public static final Item DUNGEON_KEY_SHADOWY_CREATURES = registerItem("dungeon_key_shadowy_creatures", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DUNGEON_KEY_ICY_CAVES = registerItem("dungeon_key_icy_caves", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DUNGEON_KEY_DEADLY_HEAT = registerItem("dungeon_key_deadly_heat", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DUNGEON_ICON_SHADOWY_CREATURES = registerItem("dungeon_icon_shadowy_creatures", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DUNGEON_ICON_ICY_CAVES = registerItem("dungeon_icon_icy_caves", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DUNGEON_ICON_DEADLY_HEAT = registerItem("dungeon_icon_deadly_heat", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final Item DAVY_JONES_THEME_MUSIC_DISC = registerItem("davy_jones_theme_music_disc",
            new Item(new Item.Settings().rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.DAVY_JONES_THEME_KEY).maxCount(1)));

    public static final Item PAIN_RESONANCE_MUSIC_DISC = registerItem("pain_resonance_music_disc",
            new Item(new Item.Settings().rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.PAIN_RESONANCE_KEY).maxCount(1)));

    public static final Item TEARS_MUSIC_DISC = registerItem("tears_music_disc",
            new Item(new Item.Settings().rarity(Rarity.RARE).jukeboxPlayable(ModSounds.TEARS_KEY).maxCount(1)));

    public static final Item HORIZON_ZERO_DAWN_MUSIC_DISC = registerItem("horizon_zero_dawn_music_disc",
            new Item(new Item.Settings().rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.HORIZON_ZERO_DAWN_KEY).maxCount(1)));

    public static final Item RAW_NICKEL = registerItem("raw_nickel",
            new Item(new Item.Settings()));

    public static final Item NICKEL_INGOT = registerItem("nickel_ingot",
            new Item(new Item.Settings()));

    public static final Item RAW_TITANIUM = registerItem("raw_titanium",
            new Item(new Item.Settings()));

    public static final Item TITANIUM_INGOT = registerItem("titanium_ingot",
            new Item(new Item.Settings()));



    public static final Item SOUL_CORE_ARMOR_SMITHING_TEMPLATE = registerItem("soul_core_armor_smithing_template",
             SmithingTemplateItem.of(Identifier.of(LegendaryServerMod.MOD_ID, "soul_core"),FeatureFlags.VANILLA));








    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LegendaryServerMod.MOD_ID, name), item);

    }

    public static void registerModItems() {
        LegendaryServerMod.LOGGER.info("Registering Mod Items for " + LegendaryServerMod.MOD_ID);



        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(LIGHT_CORE);
            entries.add(ModItems.NICKEL_INGOT);
            entries.add(ModItems.RAW_NICKEL);
            entries.add(ModItems.DARK_CORE);
            entries.add(ModItems.IMPURE_NOCTYRIAN);

        });
    }
}