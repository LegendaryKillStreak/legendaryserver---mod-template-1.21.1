package net.lksls.legendaryserver.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.lksls.legendaryserver.world.ModPlacedFeatures;
import net.lksls.legendaryserver.world.biome.ModBiomes;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DEEP_DARK, BiomeKeys.DARK_FOREST, BiomeKeys.LUSH_CAVES), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.NICKEL_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.JUNGLE, BiomeKeys.TAIGA, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.SAVANNA, BiomeKeys.SNOWY_PLAINS, BiomeKeys.DARK_FOREST, BiomeKeys.LUSH_CAVES), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.TITANIUM_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.NOCTURNE_VALE), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.STRANGE_DARKROOTSOIL_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.LUMINARA_DEPTHS), GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.EXCITING_DIM_STONE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.MYTHICAL_END_ORE_PLACED_KEY);

    }

}
