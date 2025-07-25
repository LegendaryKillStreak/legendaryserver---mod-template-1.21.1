package net.lksls.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;


import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {


    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.LIGHT_CORE_BLOCK);
        addDrop(ModBlocks.DARK_CORE_BLOCK);
        addDrop(ModBlocks.RAW_NICKEL_BLOCK);
        addDrop(ModBlocks.NICKEL_BLOCK);
        addDrop(ModBlocks.NICKEL_ORE, oreDrops(ModBlocks.NICKEL_ORE, ModItems.RAW_NICKEL));
        addDrop(ModBlocks.NICKEL_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.NICKEL_DEEPSLATE_ORE, ModItems.RAW_NICKEL, 3, 7));

        addDrop(ModBlocks.TITANIUM_ORE, oreDrops(ModBlocks.TITANIUM_ORE, ModItems.RAW_TITANIUM));
        addDrop(ModBlocks.TITANIUM_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.TITANIUM_DEEPSLATE_ORE, ModItems.RAW_TITANIUM, 1, 1));

        addDrop(ModBlocks.MIDNIGHTWOOD_LOG);
        addDrop(ModBlocks.MIDNIGHTWOOD_WOOD);
        addDrop(ModBlocks.STRIPPED_MIDNIGHTWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_MIDNIGHTWOOD_WOOD);
        addDrop(ModBlocks.MIDNIGHTWOOD_PLANKS);
        addDrop(ModBlocks.MIDNIGHTWOOD_SAPLING);
        addDrop(ModBlocks.DARKROOT_SOIL);

        addDrop(ModBlocks.LOST_GRASS);
        addDrop(ModBlocks.POTTED_LOST_GRASS, pottedPlantDrops(ModBlocks.LOST_GRASS));
        addDrop(ModBlocks.MIDNIGHT_GRASS, (ModBlocks.DARKROOT_SOIL));
        addDrop(ModBlocks.MIDNIGHT_GRASS, dropsWithSilkTouch(ModBlocks.MIDNIGHT_GRASS));
        addDrop(ModBlocks.ENDLESS_VIOLET);
        addDrop(ModBlocks.POTTED_ENDLESS_VIOLET, pottedPlantDrops(ModBlocks.ENDLESS_VIOLET));

        addDrop(ModBlocks.MIDNIGHTWOOD_LEAVES, leavesDrops(ModBlocks.MIDNIGHTWOOD_LEAVES, ModBlocks.MIDNIGHTWOOD_SAPLING, 0.0625f));





    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }



}

