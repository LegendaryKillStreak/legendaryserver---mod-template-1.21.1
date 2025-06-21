package net.lks.legendaryserver;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.lks.legendaryserver.block.ModBlocks;
import net.lks.legendaryserver.item.ModItemGroups;
import net.lks.legendaryserver.item.ModItems;
import net.lks.legendaryserver.sound.ModSounds;
import net.lks.legendaryserver.util.DrillUsageEvent;
import net.lks.legendaryserver.util.ModLootTableModifiers;
import net.lks.legendaryserver.world.gen.ModWorldGeneration;
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

		PlayerBlockBreakEvents.BEFORE.register(new DrillUsageEvent());

		ModLootTableModifiers.modifyLootTables();



	}


}