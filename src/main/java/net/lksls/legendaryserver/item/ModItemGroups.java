package net.lksls.legendaryserver.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SEASON_ONE_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LegendaryServerMod.MOD_ID, "season_one_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.LIGHT_SWORD))
                    .displayName(Text.translatable("itemgroup.lksls.season_one_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.LIGHT_SWORD);
                        entries.add(ModItems.LIGHT_CORE);
                        entries.add(ModBlocks.LIGHT_CORE_BLOCK);
                        entries.add(ModBlocks.DARK_CORE_BLOCK);
                        entries.add(ModItems.DARK_SOUL_CORE);
                        entries.add(ModItems.DARK_SHARD);
                        entries.add(ModItems.DARK_CORE);
                        entries.add(ModItems.DARKNESS_SWORD);
                        entries.add(ModItems.LIGHT_AXE);
                        entries.add(ModItems.LIGHT_HOE);
                        entries.add(ModItems.LIGHT_PICKAXE);
                        entries.add(ModItems.LIGHT_SHOVEL);
                        entries.add(ModItems.DARKNESS_AXE);
                        entries.add(ModItems.DARKNESS_PICKAXE);
                        entries.add(ModItems.DARKNESS_DRILL);
                        entries.add(ModItems.DARKNESS_SHOVEL);
                        entries.add(ModItems.DARKNESS_HOE);
                        entries.add(ModItems.COIN);
                        entries.add(ModItems.COIN_FIVE);
                        entries.add(ModItems.COIN_TEN);
                        entries.add(ModItems.COIN_TWENTY);
                        entries.add(ModItems.COIN_FIFTY);
                        entries.add(ModItems.COIN_100);
                        entries.add(ModItems.COIN_150);
                        entries.add(ModItems.COIN_200);
                        entries.add(ModItems.COIN_250);
                        entries.add(ModItems.COIN_500);
                        entries.add(ModItems.COIN_750);
                        entries.add(ModItems.COIN_1000);
                        entries.add(ModItems.COIN_2000);
                        entries.add(ModItems.COIN_5000);
                        entries.add(ModItems.COIN_10000);
                        entries.add(ModItems.COIN_15000);
                        entries.add(ModItems.COIN_20000);
                        entries.add(ModItems.COIN_50000);
                        entries.add(ModItems.COIN_75000);
                        entries.add(ModItems.COIN_100000);
                        entries.add(ModItems.COIN_150000);
                        entries.add(ModItems.COIN_200000);
                        entries.add(ModItems.COIN_250000);
                        entries.add(ModItems.COIN_25000);
                        entries.add(ModItems.COIN_500000);
                        entries.add(ModItems.DUNGEON_KEY_SHADOWY_CREATURES);
                        entries.add(ModItems.DUNGEON_KEY_ICY_CAVES);
                        entries.add(ModItems.DUNGEON_KEY_DEADLY_HEAT);
                        entries.add(ModItems.DUNGEON_ICON_SHADOWY_CREATURES);
                        entries.add(ModItems.DUNGEON_ICON_ICY_CAVES);
                        entries.add(ModItems.DUNGEON_ICON_DEADLY_HEAT);
                        entries.add(ModItems.LIGHT_CORE_HELMET);
                        entries.add(ModItems.LIGHT_CORE_CHESTPLATE);
                        entries.add(ModItems.LIGHT_CORE_LEGGINGS);
                        entries.add(ModItems.LIGHT_CORE_BOOTS);
                        entries.add(ModItems.DARK_CORE_HELMET);
                        entries.add(ModItems.DARK_CORE_CHESTPLATE);
                        entries.add(ModItems.DARK_CORE_LEGGINGS);
                        entries.add(ModItems.DARK_CORE_BOOTS);
                        entries.add(ModItems.DAVY_JONES_THEME_MUSIC_DISC);
                        entries.add(ModItems.PAIN_RESONANCE_MUSIC_DISC);
                        entries.add(ModItems.TEARS_MUSIC_DISC);
                        entries.add(ModItems.HORIZON_ZERO_DAWN_MUSIC_DISC);
                        entries.add(ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE);
                        entries.add(ModItems.ECHO_DUST);
                        entries.add(ModItems.RAW_NICKEL);
                        entries.add(ModItems.NICKEL_INGOT);
                        entries.add(ModItems.GAMMA_RADIATING_XENTHRITE);
                        entries.add(ModItems.BETA_RADIATING_XENTHRITE);
                        entries.add(ModItems.DIM_ROSE_QUARTZ);
                        entries.add(ModBlocks.MYSTERIOUS_END_ORE);
                        entries.add(ModBlocks.DIM_ROSE_QUARTZ_BLOCK);
                        entries.add(ModBlocks.NICKEL_DEEPSLATE_ORE);
                        entries.add(ModBlocks.NICKEL_ORE);
                        entries.add(ModBlocks.RAW_NICKEL_BLOCK);
                        entries.add(ModBlocks.NICKEL_BLOCK);
                        entries.add(ModItems.RAW_TITANIUM);
                        entries.add(ModItems.TITANIUM_INGOT);
                        entries.add(ModItems.IMPURE_NOCTYRIAN);
                        entries.add(ModBlocks.TITANIUM_DEEPSLATE_ORE);
                        entries.add(ModBlocks.TITANIUM_ORE);
                        entries.add(ModBlocks.RAW_TITANIUM_BLOCK);
                        entries.add(ModBlocks.TITANIUM_BLOCK);
                        entries.add(ModBlocks.DARKROOT_SOIL);
                        entries.add(ModItems.DRILL_ENGINE);
                        entries.add(ModBlocks.MIDNIGHT_GRASS);
                        entries.add(ModBlocks.MIDNIGHTWOOD_LOG);
                        entries.add(ModBlocks.MIDNIGHTWOOD_WOOD);
                        entries.add(ModBlocks.STRANGE_DARKROOT_SOIL);
                        entries.add(ModBlocks.ALMOST_DIM_STONE);
                        entries.add(ModBlocks.DIM_STONE);
                        entries.add(ModBlocks.EXCITING_DIM_STONE);
                        entries.add(ModBlocks.ENDLESS_VIOLET);
                        entries.add(ModBlocks.STRIPPED_MIDNIGHTWOOD_LOG);
                        entries.add(ModBlocks.STRIPPED_MIDNIGHTWOOD_WOOD);

                        entries.add(ModBlocks.MIDNIGHTWOOD_PLANKS);
                        entries.add(ModBlocks.MIDNIGHTWOOD_LEAVES);

                        entries.add(ModBlocks.MIDNIGHTWOOD_SAPLING);

                        entries.add(ModBlocks.LOST_GRASS);





                    }).build());

    public static void registerItemGroups() {
        LegendaryServerMod.LOGGER.info("Registering Item Groups for " + LegendaryServerMod.MOD_ID);
    }
}
