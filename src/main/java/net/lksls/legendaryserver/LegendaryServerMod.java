package net.lksls.legendaryserver;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.enchantment.ModEnchantmentEffects;
import net.lksls.legendaryserver.item.ModItemGroups;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.sound.ModSounds;
import net.lksls.legendaryserver.util.DrillUsageEvent;
import net.lksls.legendaryserver.util.ModLootTableModifiers;
import net.lksls.legendaryserver.world.biome.ModBiomes;
import net.lksls.legendaryserver.world.biome.ModMaterialRules;
import net.lksls.legendaryserver.world.gen.ModWorldGeneration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class LegendaryServerMod implements ModInitializer, TerraBlenderApi {
	public static final String MOD_ID = "lksls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
		ModWorldGeneration.generateModWorldGen();







		ModEnchantmentEffects.registerEnchantmentEffects();

		PlayerBlockBreakEvents.BEFORE.register(new DrillUsageEvent());

		ModLootTableModifiers.modifyLootTables();

		registerStrippables();
		registerFlammables();
	}

		private static void registerStrippables() {
			StrippableBlockRegistry.register(ModBlocks.MIDNIGHTWOOD_LOG, ModBlocks.STRIPPED_MIDNIGHTWOOD_LOG);
			StrippableBlockRegistry.register(ModBlocks.MIDNIGHTWOOD_WOOD, ModBlocks.STRIPPED_MIDNIGHTWOOD_WOOD);
		}

		private static void registerFlammables() {
			FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MIDNIGHTWOOD_LOG, 5, 5);
			FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MIDNIGHTWOOD_WOOD, 5, 5);
			FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_MIDNIGHTWOOD_LOG, 5, 5);
			FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_MIDNIGHTWOOD_WOOD, 5, 5);

			FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MIDNIGHTWOOD_PLANKS, 5, 20);
			FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.MIDNIGHTWOOD_LEAVES, 30, 60);

		}




	@Override
	public void onTerraBlenderInitialized() {
		ModBiomes.registerBiomes();

		// Register our surface rules
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModMaterialRules.makeNocturneValeRules());
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MOD_ID, ModMaterialRules.makeLuminaraDepthsRules());
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.END, MOD_ID, ModMaterialRules.makeAbyssOfLamentRules());
	}


}