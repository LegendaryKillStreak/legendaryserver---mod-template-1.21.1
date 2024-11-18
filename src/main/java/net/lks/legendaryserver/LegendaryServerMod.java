package net.lks.legendaryserver;

import net.fabricmc.api.ModInitializer;

import net.lks.legendaryserver.item.ModItemGroups;
import net.lks.legendaryserver.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegendaryServerMod implements ModInitializer {
	public static final String MOD_ID = "lksls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();



	}
}