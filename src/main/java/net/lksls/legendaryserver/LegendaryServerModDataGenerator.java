package net.lksls.legendaryserver;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.lksls.legendaryserver.datagen.*;
import net.lksls.legendaryserver.enchantment.ModEnchantments;
import net.lksls.legendaryserver.trim.ModTrimMaterials;
import net.lksls.legendaryserver.trim.ModTrimPatterns;
import net.lksls.legendaryserver.world.ModConfiguredFeatures;
import net.lksls.legendaryserver.world.ModPlacedFeatures;
import net.lksls.legendaryserver.world.biome.ModBiomes;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class LegendaryServerModDataGenerator implements DataGeneratorEntrypoint {
	public LegendaryServerModDataGenerator() {
	}


	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModItemTagProvider::new);;
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
		pack.addProvider(ModWorldGenerator::new);


	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, ModTrimPatterns::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
	}
}
