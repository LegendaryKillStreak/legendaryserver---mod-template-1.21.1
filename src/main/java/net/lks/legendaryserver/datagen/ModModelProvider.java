package net.lks.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.lks.legendaryserver.block.ModBlocks;
import net.lks.legendaryserver.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
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


        itemModelGenerator.register(ModItems.LIGHT_SWORD, Models.HANDHELD);

        itemModelGenerator.register(Item.fromBlock(ModBlocks.LIGHT_CORE_BLOCK), Models.CUBE_ALL);

        

    }
}
