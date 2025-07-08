package net.lksls.legendaryserver.world.biome;


import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.world.biome.region.NetherRegion;
import net.lksls.legendaryserver.world.biome.region.OverworldRegion;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import terrablender.api.EndBiomeRegistry;
import terrablender.api.Regions;

public class ModBiomes {
    public static final RegistryKey<Biome> NOCTURNE_VALE = registerBiomeKey("nocturne_vale");
    public static final RegistryKey<Biome> LUMINARA_DEPTHS = registerBiomeKey("luminara_depths");
    public static final RegistryKey<Biome> ABYSS_OF_LAMENT = registerBiomeKey("abyss_of_lament");

    public static void registerBiomes() {
        Regions.register(new OverworldRegion(Identifier.of(LegendaryServerMod.MOD_ID, "lksls_overworld"), 1));
        Regions.register(new NetherRegion(Identifier.of(LegendaryServerMod.MOD_ID, "lksls_nether"), 3));

        EndBiomeRegistry.registerHighlandsBiome(ABYSS_OF_LAMENT, 20);
        EndBiomeRegistry.registerHighlandsBiome(BiomeKeys.END_HIGHLANDS, 30);
        EndBiomeRegistry.registerMidlandsBiome(BiomeKeys.END_MIDLANDS, 20);



    }

    public static void bootstrap(Registerable<Biome> context) {
        var carver = context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
        var placedFeatures = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        register(context, NOCTURNE_VALE, ModOverworldBiomes.nocturneVale(placedFeatures, carver));
        register(context, LUMINARA_DEPTHS, ModNetherBiomes.luminaraDepths(placedFeatures, carver));
        register(context, ABYSS_OF_LAMENT, ModEndBiomes.abyssOfLament(placedFeatures, carver));
    }


    private static void register(Registerable<Biome> context, RegistryKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static RegistryKey<Biome> registerBiomeKey(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Identifier.of(LegendaryServerMod.MOD_ID, name));
    }
}
