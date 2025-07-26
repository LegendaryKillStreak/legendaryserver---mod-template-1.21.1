package net.lksls.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.predicate.item.EnchantmentPredicate; // Corrected package

import net.minecraft.predicate.item.ItemPredicate; // Likely also in this package
import net.minecraft.predicate.item.ItemSubPredicateTypes; // Likely also in this package



import java.util.Arrays;
import java.util.List;
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
        addDrop(ModBlocks.DIM_STONE);
        addDrop(ModBlocks.DIM_ROSE_QUARTZ_BLOCK);
        addDrop(ModBlocks.ALMOST_DIM_STONE);

        addDrop(ModBlocks.NICKEL_ORE, oreDrops(ModBlocks.NICKEL_ORE, ModItems.RAW_NICKEL));
        addDrop(ModBlocks.NICKEL_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.NICKEL_DEEPSLATE_ORE, ModItems.RAW_NICKEL, 3, 7));

        addDrop(ModBlocks.TITANIUM_ORE, oreDrops(ModBlocks.TITANIUM_ORE, ModItems.RAW_TITANIUM));
        addDrop(ModBlocks.TITANIUM_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.TITANIUM_DEEPSLATE_ORE, ModItems.RAW_TITANIUM, 1, 1));

///So, this is broken. 1.21.1 fabric. yes, enchantment/enchantments is the issue. yes we tried a few things and now there is arrays :<
///******************************************************
// Get the enchantment registry wrapper
        RegistryWrapper.Impl<Enchantment> enchantmentRegistry = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

// Get the RegistryEntry for Silk Touch
        RegistryEntry<Enchantment> silkTouchEntry = enchantmentRegistry.getOrThrow(Enchantments.SILK_TOUCH);

// Loot table for MYSTERIOUS_END_ORE
        addDrop(ModBlocks.MYSTERIOUS_END_ORE, BlockLootTableGenerator -> LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(ModBlocks.MYSTERIOUS_END_ORE))
                        .conditionally(MatchToolLootCondition.builder(
                                ItemPredicate.Builder.create()
                                        .subPredicate(
                                                ItemSubPredicateTypes.ENCHANTMENTS, // Correct constant location
                                                // FINAL CORRECTION: Use the static factory method from the OUTER EnchantmentsPredicate class
                                                EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(silkTouchEntry, NumberRange.IntRange.atLeast(1))))
                                        )
                        ))
                )
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(MatchToolLootCondition.builder(
                                ItemPredicate.Builder.create()
                                        .subPredicate(
                                                ItemSubPredicateTypes.ENCHANTMENTS, // Correct constant location
                                                // FINAL CORRECTION: Use the static factory method from the OUTER EnchantmentsPredicate class
                                                EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(silkTouchEntry, NumberRange.IntRange.exactly(0))))
                                        )
                        ).invert()) // Invert the condition: applies when Silk Touch is NOT present or level 0
                        .with(ItemEntry.builder(ModItems.BETA_RADIATING_XENTHRITE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
                                .conditionally(RandomChanceLootCondition.builder(1.0f))
                        )
                        .with(ItemEntry.builder(ModItems.GAMMA_RADIATING_XENTHRITE)
                                .conditionally(RandomChanceLootCondition.builder(0.05f))
                        )
                )
        );
///***********************************************************************************************************


        addDrop(ModBlocks.MIDNIGHTWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_MIDNIGHTWOOD_LOG);
        addDrop(ModBlocks.STRIPPED_MIDNIGHTWOOD_WOOD);
        addDrop(ModBlocks.MIDNIGHTWOOD_PLANKS);
        addDrop(ModBlocks.MIDNIGHTWOOD_SAPLING);
        addDrop(ModBlocks.DARKROOT_SOIL);

        addDrop(ModBlocks.LOST_GRASS);
        addDrop(ModBlocks.POTTED_LOST_GRASS, pottedPlantDrops(ModBlocks.LOST_GRASS));
        addDrop(ModBlocks.MIDNIGHT_GRASS, (block -> this.drops(block, ModBlocks.DARKROOT_SOIL)));
// Drop MIDNIGHT_GRASS if using shovels, because its a grass block <3 and the SILKTOUCH_OR_SHEARS was the first and only method i could think of, for some reason it also doesn't recognize SHEARS as a itemtag
        addDrop(ModBlocks.EXCITING_DIM_STONE, (block -> this.drops(block, ModItems.DIM_ROSE_QUARTZ)));


//random person from the discord: I THINK THIS SHOULD WORK??????
//L: fixxed now

        addDrop(ModBlocks.STRANGE_DARKROOT_SOIL, oreDrops(ModBlocks.STRANGE_DARKROOT_SOIL, ModItems.IMPURE_NOCTYRIAN));
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
