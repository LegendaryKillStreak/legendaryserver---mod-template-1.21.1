package net.lksls.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.tag.ModBlockTags;
import net.lksls.legendaryserver.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static net.lksls.legendaryserver.util.ModTags.Blocks.NEEDS_LIGHT_TOOL;

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
                .add(ModBlocks.NICKEL_DEEPSLATE_ORE)
                .add(ModBlocks.RAW_TITANIUM_BLOCK)
                .add(ModBlocks.TITANIUM_ORE)
                .add(ModBlocks.TITANIUM_BLOCK)
                .add(ModBlocks.ALMOST_DIM_STONE)
                .add(ModBlocks.DIM_STONE)
                .add(ModBlocks.EXCITING_DIM_STONE)
                .add(ModBlocks.DIM_ROSE_QUARTZ_BLOCK)
                .add(ModBlocks.MYSTERIOUS_END_ORE)
                .add(ModBlocks.TITANIUM_DEEPSLATE_ORE);


        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LIGHT_CORE_BLOCK)
                .add(ModBlocks.DARK_CORE_BLOCK)
                .add(ModBlocks.NICKEL_BLOCK)
                .add(ModBlocks.NICKEL_ORE)
                .add(ModBlocks.NICKEL_DEEPSLATE_ORE)
                .add(ModBlocks.RAW_NICKEL_BLOCK)
                .add(ModBlocks.TITANIUM_ORE)
                .add(ModBlocks.TITANIUM_BLOCK)
                .add(ModBlocks.EXCITING_DIM_STONE)
                .add(ModBlocks.TITANIUM_DEEPSLATE_ORE)
                .add(ModBlocks.RAW_TITANIUM_BLOCK);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.STRANGE_DARKROOT_SOIL);









        getOrCreateTagBuilder(NEEDS_LIGHT_TOOL)
                .addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_DARK_TOOL)
                .addOptionalTag(BlockTags.NEEDS_DIAMOND_TOOL);


        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MIDNIGHTWOOD_LOG, ModBlocks.MIDNIGHTWOOD_WOOD, ModBlocks.STRIPPED_MIDNIGHTWOOD_LOG, ModBlocks.STRIPPED_MIDNIGHTWOOD_WOOD);

        getOrCreateTagBuilder(BlockTags.REPLACEABLE)
                .add(ModBlocks.DARKROOT_SOIL)
                .add(ModBlocks.DIM_STONE);
        getOrCreateTagBuilder(ModBlockTags.DARKROOT_SOIL_REPLACEABLES)
                .add(ModBlocks.DARKROOT_SOIL);
        getOrCreateTagBuilder(ModBlockTags.DIM_STONE_REPLACABLES)
                .add(ModBlocks.DIM_STONE);// Add the actual blocks that this tag should contain




    }

    public static final TagKey<Block> DARKROOT_SOIL_REPLACEABLES =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(LegendaryServerMod.MOD_ID, "darkroot_soil_replaceables"));

    // Add more tags here if needed


}
