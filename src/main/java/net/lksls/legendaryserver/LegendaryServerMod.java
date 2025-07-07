package net.lksls.legendaryserver;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
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



	}
	@Override
	public void onTerraBlenderInitialized() {
		ModBiomes.registerBiomes();

		// Register our surface rules
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModMaterialRules.makeEnlightenedPlainsRules());
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MOD_ID, ModMaterialRules.makeLuminaraDepthsRules());
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.END, MOD_ID, ModMaterialRules.makeAbyssOfLamentRules());
	}


}