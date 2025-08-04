package net.lksls.legendaryserver.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.lksls.legendaryserver.world.ModPlacedFeatures;
import net.lksls.legendaryserver.world.biome.ModBiomes;
import net.minecraft.world.gen.GenerationStep;

public class ModFlowerGeneration {
    public static void generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.NOCTURNE_VALE),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LOST_GRASS_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.NOCTURNE_VALE),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ENDLESS_VIOLET_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.ABYSS_OF_LAMENT),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAMENT_SPINDLE_PLACED_KEY);
    }
}
