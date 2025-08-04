package net.lksls.legendaryserver.world;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.ModBlocks;
import net.minecraft.command.argument.BlockPredicateArgumentType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;


import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> NICKEL_ORE_PLACED_KEY = registerKey("nickel_ore_placed");
    public static final RegistryKey<PlacedFeature> TITANIUM_ORE_PLACED_KEY = registerKey("titanium_ore_placed");
    public static final RegistryKey<PlacedFeature> STRANGE_DARKROOTSOIL_PLACED_KEY = registerKey("strange_darkrootsoil_placed");
    public static final RegistryKey<PlacedFeature> MIDNIGHTWOOD_PLACED_KEY = registerKey("midnightwood_placed");
    public static final RegistryKey<PlacedFeature> LOST_GRASS_PLACED_KEY = registerKey("lost_grass_placed");
    public static final RegistryKey<PlacedFeature> LAMENT_SPINDLE_PLACED_KEY = registerKey("lament_spindle_placed");
    public static final RegistryKey<PlacedFeature> ENDLESS_VIOLET_PLACED_KEY = registerKey("endless_violet_placed");
    public static final RegistryKey<PlacedFeature> EXCITING_DIM_STONE_PLACED_KEY = registerKey("exciting_dim_stone_placed");
    public static final RegistryKey<PlacedFeature> MYTHICAL_END_ORE_PLACED_KEY = registerKey("mythical_end_ore_placed");
    public static final RegistryKey<PlacedFeature> TORCHFLOWER_PLACED_KEY = registerKey("torchflower_placed");

    // NEW: Define the RegistryKey for the PlacedFeature version of the bone meal feature
    public static final RegistryKey<PlacedFeature> MIDNIGHT_GRASS_BONEMEAL_PLACED_KEY = registerKey("midnight_grass_bonemeal_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeaturesRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // NEW: Register the PlacedFeature for Midnight Grass bone meal.
        // It references the ConfiguredFeature from ModConfiguredFeatures and uses an empty list of placement modifiers,
        // as the RandomPatchFeatureConfig itself handles the spread.
        register(context, MIDNIGHT_GRASS_BONEMEAL_PLACED_KEY,
                configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MIDNIGHT_GRASS_BONEMEAL),
                List.of() // No additional placement modifiers needed here, as RandomPatchFeatureConfig handles spread
        );

        register(context, NICKEL_ORE_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NICKEL_ORE_KEY),
                ModOrePlacement.modifiersWithCount(7,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-60), YOffset.fixed(20)))
        );
        register(context, TITANIUM_ORE_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TITANIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(9,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-60), YOffset.fixed(64)))
        );

        register(context, EXCITING_DIM_STONE_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.EXCITING_DIM_STONE_KEY),
                ModOrePlacement.modifiersWithCount(9,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(0), YOffset.fixed(125)))
        );

        register(context, MYTHICAL_END_ORE_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MYTHICAL_END_ORE_KEY),
                ModOrePlacement.modifiersWithCount(2,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(80)))

        );
        // Option 2: Use uniform height from min_build_height to max_build_height
        register(context, STRANGE_DARKROOTSOIL_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.STRANGE_DARKROOTSOIL_KEY),
                ModOrePlacement.modifiersWithCount(30,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(30), YOffset.fixed(170))) // Covers height
        );
        register(context, MIDNIGHTWOOD_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MIDNIGHTWOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(4, 0.1f, 2), ModBlocks.MIDNIGHTWOOD_SAPLING));

        register(context, LOST_GRASS_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LOST_GRASS_KEY),
                RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, ENDLESS_VIOLET_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ENDLESS_VIOLET_KEY),
                RarityFilterPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, TORCHFLOWER_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TORCHFLOWER_KEY),
                RarityFilterPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(LegendaryServerMod.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}

