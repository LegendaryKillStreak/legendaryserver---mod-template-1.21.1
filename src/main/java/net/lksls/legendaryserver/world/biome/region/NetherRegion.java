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

import java.util.function.Consumer;

public class NetherRegion extends Region {
    public NetherRegion(Identifier name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addBiome(mapper,
                MultiNoiseUtil.ParameterRange.of(-0.4F, 0.4F), // temperature (wide)
                MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F), // humidity (rare)
                MultiNoiseUtil.ParameterRange.of(-0.8F, 0.8F), // continentalness (VERY wide)
                MultiNoiseUtil.ParameterRange.of(-0.6F, 0.7F), // erosion (VERY wide)
                MultiNoiseUtil.ParameterRange.of(-0.02F, 0.02F), // weirdness (tight = rare)
                MultiNoiseUtil.ParameterRange.of(-0.3F, 0.3F), // depth (medium)
                0.0F,
                ModBiomes.LUMINARA_DEPTHS
        );
    }

}