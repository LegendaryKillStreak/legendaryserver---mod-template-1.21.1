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
import net.lksls.legendaryserver.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegendaryServerMod implements ModInitializer {
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


}