package net.lksls.legendaryserver.world;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<? , ?>> NICKEL_ORE_KEY = registryKey("nickel_ore");
    public static final RegistryKey<ConfiguredFeature<? , ?>> TITANIUM_ORE_KEY = registryKey("titanium_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHTWOOD_KEY = registryKey("midnightwood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LOST_GRASS_KEY = registryKey("lost_grass");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDLESS_VIOLET_KEY = registryKey("endless_violet");

    public static void bootstrap(Registerable<ConfiguredFeature<? , ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldNickelOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.NICKEL_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.NICKEL_DEEPSLATE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldTitaniumOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.TITANIUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.TITANIUM_DEEPSLATE_ORE.getDefaultState()));


        register(context, NICKEL_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldNickelOres, 4));
        register(context, TITANIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTitaniumOres, 7));

        register(context, MIDNIGHTWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.MIDNIGHTWOOD_LOG),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.of(ModBlocks.MIDNIGHTWOOD_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), ConstantIntProvider.create(5),
                        0.25f, 0.5f, 0.15f, 0.05f),
                new TwoLayersFeatureSize(1, 0, 2)).dirtProvider(BlockStateProvider.of(ModBlocks.MIDNIGHT_GRASS)).build());

        register(context, LOST_GRASS_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(128,6,2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LOST_GRASS)))));
        register(context, ENDLESS_VIOLET_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(256,4,5, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ENDLESS_VIOLET)))));



    }



    public static RegistryKey<ConfiguredFeature<? , ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(LegendaryServerMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<? , ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<? , ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
