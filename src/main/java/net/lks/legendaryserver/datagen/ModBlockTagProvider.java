package net.lks.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.lks.legendaryserver.block.ModBlocks;
import net.lks.legendaryserver.util.ModTags;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

import static net.lks.legendaryserver.util.ModTags.Blocks.NEEDS_LIGHT_TOOL;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.LIGHT_CORE_BLOCK)
                .add(ModBlocks.DARK_CORE_BLOCK)
                .add(ModBlocks.RAW_NICKEL_BLOCK)
                .add(ModBlocks.NICKEL_BLOCK)
                .add(ModBlocks.NICKEL_ORE)
                .add(ModBlocks.NICKEL_DEEPSLATE_ORE);


        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LIGHT_CORE_BLOCK)
                .add(ModBlocks.DARK_CORE_BLOCK)
                .add(ModBlocks.NICKEL_BLOCK)
                .add(ModBlocks.NICKEL_ORE)
                .add(ModBlocks.NICKEL_DEEPSLATE_ORE)
                .add(ModBlocks.RAW_NICKEL_BLOCK);




        getOrCreateTagBuilder(NEEDS_LIGHT_TOOL)
                .addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_DARK_TOOL)
                .addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL);
    }


}
