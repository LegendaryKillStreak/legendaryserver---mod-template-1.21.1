package net.lksls.legendaryserver.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.custom.DarkCoreBlock;
import net.lksls.legendaryserver.block.custom.LightCoreBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block LIGHT_CORE_BLOCK = registerBlock("light_core_block",
            new LightCoreBlock(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.HEAVY_CORE).strength(20f,1200f).luminance(value -> 15)));
    public static final Block DARK_CORE_BLOCK = registerBlock("dark_core_block",
            new DarkCoreBlock(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.HEAVY_CORE).strength(50f,1200f).luminance(value -> 0)));

    public static final Block RAW_NICKEL_BLOCK = registerBlock("raw_nickel_block",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.METAL).strength(5f,6f).luminance(value -> 0)));
    public static final Block NICKEL_BLOCK = registerBlock("nickel_block",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.METAL).strength(4f,5f).luminance(value -> 0)));

    public static final Block RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.METAL).strength(7f,6f).luminance(value -> 0)));
    public static final Block TITANIUM_BLOCK = registerBlock("titanium_block",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.METAL).strength(5f,5f).luminance(value -> 0)));

    public static final Block NICKEL_ORE = registerBlock("nickel_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create().strength(4f).requiresTool()));
    public static final Block NICKEL_DEEPSLATE_ORE = registerBlock("nickel_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block TITANIUM_ORE = registerBlock("titanium_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create().strength(5f).requiresTool()));
    public static final Block TITANIUM_DEEPSLATE_ORE = registerBlock("titanium_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
                    AbstractBlock.Settings.create().strength(6f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

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
            entries.add(ModBlocks.DARK_CORE_BLOCK);
            entries.add(ModBlocks.RAW_NICKEL_BLOCK);
        });

    }
}
