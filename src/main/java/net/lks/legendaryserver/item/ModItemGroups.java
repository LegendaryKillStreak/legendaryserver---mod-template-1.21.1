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

                    }).build());

    public static void registerItemGroups() {
        LegendaryServerMod.LOGGER.info("Registering Item Groups for " + LegendaryServerMod.MOD_ID);
    }
}
