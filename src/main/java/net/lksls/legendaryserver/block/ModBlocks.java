package net.lksls.legendaryserver.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.custom.*;
import net.lksls.legendaryserver.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
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


    public static final Block ALTERNATIVE_END_STONE = registerBlock("alternative_end_stone",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.STONE).strength(10f,1100f).luminance(value -> 1)));

    public static final Block EXCITING_DIM_STONE = registerBlock("exciting_dim_stone",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.STONE).strength(12f,100f).luminance(value -> 9)));

    public static final Block ALMOST_DIM_STONE = registerBlock("almost_dim_stone",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.STONE).strength(5f,120f).luminance(value -> 15)));

    public static final Block DIM_STONE = registerBlock("dim_stone",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.STONE).strength(20f,120f).luminance(value -> 6)));

    public static final Block DIM_ROSE_QUARTZ_BLOCK = registerBlock("dim_rose_quartz_block",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool().sounds(BlockSoundGroup.STONE).strength(20f,120f).luminance(value -> 3)));

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
    public static final Block DARKROOT_SOIL = registerBlock("darkroot_soil",
            new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
    public static final Block MIDNIGHT_GRASS = registerBlock("midnight_grass",
            new MidnightGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK).ticksRandomly()));

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

    public static final Block STRANGE_DARKROOT_SOIL = registerBlock("strange_darkroot_soil",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 2),
                    AbstractBlock.Settings.create().strength(3f,1f).luminance(value -> 9).sounds(BlockSoundGroup.SUSPICIOUS_GRAVEL)));

    public static final Block MYSTERIOUS_END_ORE = registerBlock("mysterious_end_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 9),
                    AbstractBlock.Settings.create().strength(2f,1f).luminance(value -> 5).sounds(BlockSoundGroup.GLASS)));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(LegendaryServerMod.MOD_ID, name), block);



    }

    public static final Block MIDNIGHTWOOD_LOG = registerBlock("midnightwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).strength(3f).luminance(value -> 3)));
    public static final Block MIDNIGHTWOOD_WOOD = registerBlock("midnightwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD).strength(3f).luminance(value -> 3)));
    public static final Block STRIPPED_MIDNIGHTWOOD_LOG = registerBlock("stripped_midnightwood_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG).strength(3f).luminance(value -> 3)));
    public static final Block STRIPPED_MIDNIGHTWOOD_WOOD = registerBlock("stripped_midnightwood_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f).luminance(value -> 3)));

    public static final Block MIDNIGHTWOOD_PLANKS = registerBlock("midnightwood_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).strength(3f).luminance(value -> 3)));
    public static final Block MIDNIGHTWOOD_LEAVES = registerBlock("midnightwood_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).luminance(value -> 3)));

    public static final Block MIDNIGHTWOOD_SAPLING = registerBlock("midnightwood_sapling",
            new ModSaplingBlock(ModSaplingGenerators.MIDNIGHTWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).luminance(value -> 3), ModBlocks.MIDNIGHT_GRASS));

    public static final Block LOST_GRASS = registerBlock("lost_grass",
            new FlowerBlock(StatusEffects.DARKNESS, 4, AbstractBlock.Settings.copy(Blocks.ALLIUM).luminance(value -> 3)));

    public static final Block LAMENT_SPINDLE = registerBlock("lament_spindle",
            new FlowerBlock(StatusEffects.DARKNESS, 4, AbstractBlock.Settings.copy(Blocks.ALLIUM).luminance(value -> 3)));

    public static final Block POTTED_LOST_GRASS = registerBlockWithoutBlockItem("potted_lost_grass",
            new FlowerPotBlock(LOST_GRASS, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)));

    public static final Block ENDLESS_VIOLET = registerBlock("endless_violet",
            new FlowerBlock(StatusEffects.DARKNESS, 4, AbstractBlock.Settings.copy(Blocks.ALLIUM).luminance(value -> 2)));

    public static final Block POTTED_ENDLESS_VIOLET = registerBlockWithoutBlockItem("potted_endless_violet",
            new FlowerPotBlock(ENDLESS_VIOLET, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
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
