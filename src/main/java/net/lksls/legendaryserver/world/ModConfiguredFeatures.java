package net.lksls.legendaryserver.world;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.datagen.ModBlockTagProvider;
import net.lksls.legendaryserver.util.ModTags;
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
    public static final RegistryKey<ConfiguredFeature<? , ?>> STRANGE_DARKROOTSOIL_KEY = registryKey("strange_darkrootsoil_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHTWOOD_KEY = registryKey("midnightwood");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LOST_GRASS_KEY = registryKey("lost_grass");

    // Define the RegistryKey for your bone meal configured feature (random patch)
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDNIGHT_GRASS_BONEMEAL =
            RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of("lksls", "midnight_grass_bonemeal"));

    // REMOVED: MIDNIGHT_GRASS_BONEMEAL_PLACED RegistryKey, as PlacedFeature will be created dynamically
    // public static final RegistryKey<PlacedFeature> MIDNIGHT_GRASS_BONEMEAL_PLACED =
    //         RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of("lksls", "midnight_grass_bonemeal_placed"));

    public static final RegistryKey<ConfiguredFeature<?, ?>> ENDLESS_VIOLET_KEY = registryKey("endless_violet");

    // REVERTED: bootstrap method now accepts a single Registerable context for ConfiguredFeatures
    public static void bootstrap(Registerable<ConfiguredFeature<? , ?>> context) {
        // --- START: Midnight Grass Bonemeal Feature ---
        // Register the ConfiguredFeature for the bone meal effect.
        // The PlacedFeature is created inline for the RandomPatchFeatureConfig.
        context.register(MIDNIGHT_GRASS_BONEMEAL,
                new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                        new RandomPatchFeatureConfig(
                                64, // Number of blocks to attempt to place
                                7,  // Spread on the X/Z axis
                                3,  // Spread on the Y axis
                                // The feature to place (a PlacedFeature that places a single Midnight Grass block)
                                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.MIDNIGHT_GRASS)))
                        )));

        // REMOVED: Registration of MIDNIGHT_GRASS_BONEMEAL_PLACED, as it's no longer needed here.
        // placedFeatureContext.register(MIDNIGHT_GRASS_BONEMEAL_PLACED,
        //         new PlacedFeature(
        //                 configuredFeatureContext.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(MIDNIGHT_GRASS_BONEMEAL),
        //                 List.of()
        //         ));
        // --- END: Midnight Grass Bonemeal Feature ---

        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest darkrootsoilReplaceables = new TagMatchRuleTest(ModBlocks.DARKROOT_SOIL);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldNickelOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.NICKEL_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.NICKEL_DEEPSLATE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldTitaniumOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.TITANIUM_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.TITANIUM_DEEPSLATE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> overworldStrangeDarkrootsoil =
                List.of(OreFeatureConfig.createTarget(darkrootsoilReplaceables, ModBlocks.STRANGE_DARKROOT_SOIL.getDefaultState()),



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

    // register helper method remains unchanged, as it already takes Registerable<ConfiguredFeature>
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<? , ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<? , ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static void init() {
        // IMPORTANT: The 'bootstrap' method now requires a single Registerable<ConfiguredFeature> context.
        // You will need to update how you call ModConfiguredFeatures.bootstrap in your main mod class
        // or data generation class if it was previously passing two contexts.
        //
        // Example for data generation (in a class extending FabricDataGenerator.Pack):
        // @Override
        // public void configure(RegistryWrapper.WrapperLookup registries, FabricDataGenerator.Pack.Entries entries) {
        //     // ... other setup ...
        //     ModConfiguredFeatures.bootstrap(entries.get(RegistryKeys.CONFIGURED_FEATURE));
        //     // ... continue with other data generation registrations ...
        // }
        //
        // You generally don't call bootstrap directly like `ModConfiguredFeatures.bootstrap(...)`
        // in your main mod's `onInitialize` method for world generation features.
        // Instead, Fabric's data generation or world generation API provides the `Registerable` contexts.
    }
}