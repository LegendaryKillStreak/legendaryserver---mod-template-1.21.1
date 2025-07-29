package net.lksls.legendaryserver;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.damagesource.ModDamageSources;
import net.lksls.legendaryserver.damagetype.ModDamageTypes;
import net.lksls.legendaryserver.effect.ModEffects;
import net.lksls.legendaryserver.enchantment.ModEnchantmentEffects;
import net.lksls.legendaryserver.item.ModItemGroups;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.sound.ModSounds;
import net.lksls.legendaryserver.util.DrillUsageEvent;
import net.lksls.legendaryserver.util.ModLootTableModifiers;
import net.lksls.legendaryserver.world.biome.ModBiomes;
import net.lksls.legendaryserver.world.biome.ModMaterialRules;
import net.lksls.legendaryserver.world.gen.ModWorldGeneration;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

import java.util.function.Supplier;

public class LegendaryServerMod implements ModInitializer, TerraBlenderApi {
	public static final String MOD_ID = "lksls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static ModDamageSources SERVER_DAMAGE_SOURCES;




	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
		ModWorldGeneration.generateModWorldGen();
		ModDamageTypes.registerModDamageTypes();
		ModEffects.registerEffects();

		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			// When the server starts, get its RegistryManager (which is a WrapperLookup)
			RegistryWrapper.WrapperLookup lookup = server.getRegistryManager();
			// Create your ModDamageSources instance using this lookup.
			SERVER_DAMAGE_SOURCES = new ModDamageSources(lookup);
			System.out.println("lksls: ModDamageSources initialized on server start!");
		});

		// 3. Register a callback for when the server stops.
		// This is good practice to clean up or ensure no stale references.
		ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
			SERVER_DAMAGE_SOURCES = null; // Clear the instance when the server stops
			System.out.println("lksls: ModDamageSources cleared on server stop.");
		});


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
	public static final Identifier FORGOTTEN_GRAVE_LOOT_TABLE_ID = Identifier.of(MOD_ID, "chests/forgotten_grave");

	public static RegistryEntry<DamageType> RADIATION_DAMAGE_TYPE_ENTRY;
	public static DamageSource GENERIC_RADIATION_SOURCE; // If you want a static source without attacker/pos

	public static ModDamageSources getDamageSources(World world) {
		// Essential check: DamageSource creation and registry lookup should only happen on the logical server.
		if (world.isClient()) {
			throw new IllegalStateException("Attempted to access ModDamageSources on the client side! This is a server-side only operation.");
		}
		if (SERVER_DAMAGE_SOURCES == null) {
			// This case should ideally not happen if getDamageSources is called correctly (after server start).
			// But it's a good safeguard.
			throw new IllegalStateException("ModDamageSources has not been initialized. Has the server started yet?");
		}
		return SERVER_DAMAGE_SOURCES;
	}


		@Override
		public void onTerraBlenderInitialized () {
			ModBiomes.registerBiomes();

			// Register our surface rules
			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModMaterialRules.makeNocturneValeRules());
			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MOD_ID, ModMaterialRules.makeLuminaraDepthsRules());
			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.END, MOD_ID, ModMaterialRules.makeAbyssOfLamentRules());
		}


	}
