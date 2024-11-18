package net.lks.legendaryserver.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lks.legendaryserver.LegendaryServerMod;
import net.lks.legendaryserver.block.custom.LightCoreBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block LIGHT_CORE_BLOCK = registerBlock("light_core_block",
            new LightCoreBlock(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.HEAVY_CORE).strength(20f,1200f)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LegendaryServerMod.MOD_ID, name), block);
    }


    private static void  registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(LegendaryServerMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        LegendaryServerMod.LOGGER.info("Registering Mod Blocks for " + LegendaryServerMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.LIGHT_CORE_BLOCK);
        });

    }
}
