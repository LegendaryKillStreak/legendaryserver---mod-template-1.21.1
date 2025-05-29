package net.lks.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.lks.legendaryserver.block.ModBlocks;
import net.lks.legendaryserver.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIGHT_CORE_BLOCK);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LIGHT_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_CORE, Models.GENERATED);

        itemModelGenerator.register(ModItems.DUNGEON_KEY_SHADOWY_CREATURES, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUNGEON_KEY_ICY_CAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUNGEON_KEY_DEADLY_HEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUNGEON_ICON_SHADOWY_CREATURES, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUNGEON_ICON_ICY_CAVES, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUNGEON_ICON_DEADLY_HEAT, Models.GENERATED);





        itemModelGenerator.register(ModItems.COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_FIVE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_TEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_TWENTY, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_150, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_1000, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_5000, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_500, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_250, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_200, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_2000, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_750, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_FIFTY, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_100, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_10000, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_100000, Models.GENERATED);
        itemModelGenerator.register(ModItems.COIN_15000, Models.GENERATED);






        itemModelGenerator.register(ModItems.DARKNESS_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DARKNESS_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DARKNESS_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DARKNESS_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DARKNESS_AXE, Models.HANDHELD);



        itemModelGenerator.register(ModItems.LIGHT_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LIGHT_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LIGHT_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LIGHT_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LIGHT_SWORD, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_CORE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_CORE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_CORE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LIGHT_CORE_BOOTS));



        

    }
}
