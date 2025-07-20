package net.lksls.legendaryserver.world.biome.region;

import com.mojang.datafixers.util.Pair;

import net.lksls.legendaryserver.world.biome.ModBiomes;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

// Assuming ModBiomes is correctly imported
// import your.mod.package.ModBiomes;
// import your.mod.package.LegendaryServerMod; // For Identifier.of(LegendaryServerMod.MOD_ID, name)

public class OverworldRegion extends Region {
    public OverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        // Strategy for Rarer & Bigger:
        // 1. Keep wide 'span' ranges for primary size-defining parameters (Temperature, Humidity).
        // 2. Make other parameters (Continentalness, Erosion, Depth, Weirdness) more SPECIFIC
        //    (fewer options or narrower ranges) to reduce the number of places it can spawn.
        //    This makes it rarer overall.
        // 3. When those specific conditions are met, the wide spans for temp/humidity allow it to spread.
        new ParameterPointListBuilder()
                // Wide spans for Temperature and Humidity. These allow the biome to be large
                // by covering a variety of climates when it does generate.
                .temperature(Temperature.span(Temperature.NEUTRAL, Temperature.UNFROZEN))
                .humidity(Humidity.span(Humidity.DRY, Humidity.WET))

                // More specific Continentalness: Instead of 'any', choose a narrower range of inland types.
                // This reduces the overall frequency but allows it to span large landmasses within that range.
                .continentalness(Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))

                // Very specific Erosion: Limit to only one or two erosion types. This significantly cuts down
                // the number of possible locations, making it rarer.
                .erosion(Erosion.EROSION_0) // Or Erosion.EROSION_0, Erosion.EROSION_1 for a slightly less rare option

                // Very specific Depth: Typically SURFACE for surface biomes.
                .depth(Depth.SURFACE)

                // Very specific Weirdness: Limit to a single weirdness slice. This also reduces rarity.
                .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING) // Or another single weirdness value, like Weirdness.MID_SLICE_NORMAL_DESCENDING

                // Build all combinations of these parameters and add Nocturne Vale to them.
                .build().forEach(point -> builder.add(point, ModBiomes.NOCTURNE_VALE));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}