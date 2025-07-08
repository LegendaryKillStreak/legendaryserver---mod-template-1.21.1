package net.lksls.legendaryserver.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.lksls.legendaryserver.world.ModPlacedFeatures;
import net.lksls.legendaryserver.world.biome.ModBiomes;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.NOCTURNE_VALE),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MIDNIGHTWOOD_PLACED_KEY);
    }
}