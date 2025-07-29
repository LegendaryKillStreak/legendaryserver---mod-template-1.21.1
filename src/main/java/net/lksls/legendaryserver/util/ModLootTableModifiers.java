package net.lksls.legendaryserver.util;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.lksls.legendaryserver.datagen.ModItemTagProvider;
import net.lksls.legendaryserver.enchantment.ModEnchantments;
import net.lksls.legendaryserver.item.ModItems;
import net.minecraft.block.LeavesBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class  ModLootTableModifiers {
    private static final Identifier GHAST_ID
            = Identifier.of("minecraft","entities/ghast");
    private static final Identifier WARDEN_ID
            = Identifier.of("minecraft","entities/warden");




    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source,registry) -> {
            if (LootTables.ANCIENT_CITY_CHEST.equals(key))
            {




                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.01f))
                        .with(ItemEntry.builder(ModItems.COIN_TEN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f ,2.0f)).build());



                tableBuilder.pool(poolBuilder.build());

            }

            if (LootTables.ANCIENT_CITY_CHEST.equals(key))
            {




                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(ModItems.COIN))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f ,2.0f)).build());



                tableBuilder.pool(poolBuilder.build());

            }




            if (LootTables.ANCIENT_CITY_CHEST.equals(key))
            {




                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(ModItems.ECHO_DUST))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f ,20.0f)).build());



                tableBuilder.pool(poolBuilder.build());

            }
            if (LootTables.ANCIENT_CITY_CHEST.equals(key))
            {




                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(ModItems.DUNGEON_KEY_SHADOWY_CREATURES))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f ,1.0f)).build());



                tableBuilder.pool(poolBuilder.build());

            }

            if (GHAST_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(ModItems.TEARS_MUSIC_DISC))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());



                tableBuilder.pool(poolBuilder.build());


            }
            if (WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1.0f))
                        .with(ItemEntry.builder(ModItems.ECHO_DUST))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 32.0f)).build());


                tableBuilder.pool(poolBuilder.build());

            }

            if (WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(ModItems.DARK_SOUL_CORE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());


                tableBuilder.pool(poolBuilder.build());
            }



            if (LootTables.NETHER_BRIDGE_CHEST.equals(key))
            {




                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.03f))
                        .with(ItemEntry.builder(ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f ,2.0f)).build());



                tableBuilder.pool(poolBuilder.build());

            }

            if (LootTables.DESERT_PYRAMID_CHEST.equals(key))
            {




                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.33f))
                        .with(ItemEntry.builder(ModItems.DUNGEON_KEY_DEADLY_HEAT))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());



                tableBuilder.pool(poolBuilder.build());

            }







        });




    }
}
