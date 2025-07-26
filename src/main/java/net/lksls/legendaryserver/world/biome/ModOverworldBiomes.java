package net.lksls.legendaryserver.world.biome;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.lksls.legendaryserver.sound.ModSounds;
import net.lksls.legendaryserver.world.ModPlacedFeatures;
import net.lksls.legendaryserver.world.gen.ModTreeGeneration;
import net.minecraft.client.sound.AmbientSoundLoops;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.*;

public class ModOverworldBiomes {
    private static void addFeature(GenerationSettings.LookupBackedBuilder builder, GenerationStep.Feature step, RegistryKey<PlacedFeature> feature) {
        builder.feature(step, feature);
        ModTreeGeneration.generateTrees();
    }

    public static Biome nocturneVale(RegistryEntryLookup<PlacedFeature> placedFeatureGetter, RegistryEntryLookup<ConfiguredCarver<?>> carverGetter, RegistryEntryLookup<SoundEvent> soundEventGetter) {
        // Mob spawns
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder, 30);
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.ARMADILLO, 20, 2, 3));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CAMEL, 10, 1, 2));

        // Biome features
        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(placedFeatureGetter, carverGetter);
        DefaultBiomeFeatures.addLandCarvers(biomeBuilder);
        DefaultBiomeFeatures.addMineables(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addClayDisk(biomeBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        addFeature(biomeBuilder, GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        addFeature(biomeBuilder, GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP);
        addFeature(biomeBuilder, GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MIDNIGHTWOOD_PLACED_KEY);
        addFeature(biomeBuilder, GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LOST_GRASS_PLACED_KEY);
        addFeature(biomeBuilder, GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ENDLESS_VIOLET_PLACED_KEY);
        addFeature(biomeBuilder, GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.TORCHFLOWER_PLACED_KEY);
        // Get the RegistryEntry for your custom sound event using the provided lookup
        RegistryEntry<SoundEvent> darkAmbienceEntry = soundEventGetter.getOrThrow(ModSounds.AMBIENT_SOUND_LOOPS_REGISTRY_KEY);









        // Custom Placed Feature


        return new Biome.Builder()
                .precipitation(false).temperature(4.0F).downfall(0.0F)
                .effects((new BiomeEffects.Builder()).waterColor(0x280041).waterFogColor(0x280041).fogColor(0x280041)
                        .skyColor(getSkyColor(2.0F)).particleConfig(new BiomeParticleConfig(ParticleTypes.GLOW, 0.00725f))
                        .loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .fogColor(0x280041).grassColor(0x492151)
                        .foliageColor(0x6A0DAD) // Example: A deep purple color (Hex: #6A0DAD)
                        .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 90000, 8, 2.0D))
                        .music(MusicType.createIngameMusic(darkAmbienceEntry)).build())
                .spawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static int getSkyColor(float temperature) {
        float f = temperature;
        f /= 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
}
