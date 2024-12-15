package net.lks.legendaryserver.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.lks.legendaryserver.LegendaryServerMod;
import net.lks.legendaryserver.block.ModBlocks;
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
                        entries.add(ModItems.DARK_CORE);
                        entries.add(ModItems.DARKNESS_SWORD);
                        entries.add(ModItems.LIGHT_AXE);
                        entries.add(ModItems.LIGHT_HOE);
                        entries.add(ModItems.LIGHT_PICKAXE);
                        entries.add(ModItems.LIGHT_SHOVEL);
                        entries.add(ModItems.DARKNESS_AXE);
                        entries.add(ModItems.DARKNESS_PICKAXE);
                        entries.add(ModItems.DARKNESS_SHOVEL);
                        entries.add(ModItems.DARKNESS_HOE);
                        entries.add(ModItems.COIN);
                        entries.add(ModItems.COIN_TEN);
                        entries.add(ModItems.COIN_FIVE);
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


                    }).build());

    public static void registerItemGroups() {
        LegendaryServerMod.LOGGER.info("Registering Item Groups for " + LegendaryServerMod.MOD_ID);
    }
}
