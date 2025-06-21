package net.lks.legendaryserver.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.lks.legendaryserver.world.ModPlacedFeatures;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DEEP_DARK, BiomeKeys.DARK_FOREST, BiomeKeys.LUSH_CAVES), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.NICKEL_ORE_PLACED_KEY);
    }
}
