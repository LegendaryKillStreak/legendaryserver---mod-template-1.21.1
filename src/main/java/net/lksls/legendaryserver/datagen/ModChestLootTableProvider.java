package net.lksls.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.util.ModLootTables;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.data.server.loottable.LootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;
import net.minecraft.component.type.ItemEnchantmentsComponent;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;




import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent; // Ensure this is imported
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetComponentsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;


import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ModChestLootTableProvider extends SimpleFabricLootTableProvider {

    private final CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture;

    public ModChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
        this.registryLookupFuture = registryLookup;
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        RegistryWrapper.WrapperLookup registryLookup = registryLookupFuture.join();

        LootTable.Builder forgottenGraveLootTable = LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(7.0f))
                        .with(ItemEntry.builder(Items.ENDER_PEARL)
                                .weight(10)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 8.0f))))
                        .with(ItemEntry.builder(Items.BONE)
                                .weight(20)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 8.0f))))
                        .with(ItemEntry.builder(Items.GOLDEN_APPLE)
                                .weight(7)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                        .with(ItemEntry.builder(Items.BOOK)
                                .apply(EnchantWithLevelsLootFunction.builder(registryLookup, ConstantLootNumberProvider.create(30.0F)))))
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(3))
                        .with(ItemEntry.builder(ModItems.BETA_RADIATING_XENTHRITE).weight(3))
                        .with(ItemEntry.builder(Items.FIREWORK_ROCKET)
                                .weight(7)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(20.0f, 40.0f))))
                );

        lootTableBiConsumer.accept(ModLootTables.FORGOTTEN_GRAVE, forgottenGraveLootTable);
    }
}