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
    public static final RegistryKey<PlacedFeature> MIDNIGHTWOOD_PLACED_KEY = registerKey("midnightwood_placed");
    public static final RegistryKey<PlacedFeature> LOST_GRASS_PLACED_KEY = registerKey("lost_grass_placed");
    public static final RegistryKey<PlacedFeature> ENDLESS_VIOLET_PLACED_KEY = registerKey("endless_violet_placed");

//why are you in my code!!! also you do NOT wanna know the number of errors i fixxed with this mod
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeaturesRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, NICKEL_ORE_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NICKEL_ORE_KEY),
                ModOrePlacement.modifiersWithCount(7,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-60), YOffset.fixed(20)))
                );
        register(context, TITANIUM_ORE_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TITANIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(9,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-60), YOffset.fixed(64)))



        );
        register(context, MIDNIGHTWOOD_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MIDNIGHTWOOD_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(4, 0.1f, 2), ModBlocks.MIDNIGHTWOOD_SAPLING));

        register(context, LOST_GRASS_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LOST_GRASS_KEY),
                RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, ENDLESS_VIOLET_PLACED_KEY, configuredFeaturesRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ENDLESS_VIOLET_KEY),
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

