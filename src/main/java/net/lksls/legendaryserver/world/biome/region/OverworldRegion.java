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

public class OverworldRegion extends Region {
    public OverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        // Overlap Vanilla's parameters with our own for our COLD_BLUE biome.
        // The parameters for this biome are chosen arbitrarily.
        new ParameterPointListBuilder()
            .temperature(Temperature.span(Temperature.NEUTRAL, Temperature.UNFROZEN))
            .humidity(Humidity.span(Humidity.ARID, Humidity.WET))
            .continentalness(Continentalness.FAR_INLAND)
            .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
            .depth(Depth.SURFACE)
            .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING)
            .build().forEach(point -> builder.add(point, ModBiomes.NOCTURNE_VALE));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}