package net.lks.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.lks.legendaryserver.block.ModBlocks;
import net.lks.legendaryserver.item.ModItems;
import net.lks.legendaryserver.util.ModTags;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_CORE_BLOCK)
                        .pattern("AAA")
                        .pattern("AAA")
                        .pattern("AAA")
                        .input('A', ModItems.LIGHT_CORE)
                        .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                        .offerTo(exporter);

        ;

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LIGHT_SWORD)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" B ")
                .input('A', Items.PRISMARINE_SHARD)
                .input('B', Items.END_ROD)
                .input('X', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LIGHT_CORE)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" A ")
                .input('A', Items.GLOWSTONE_DUST)
                .input('X', Items.NETHER_STAR)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARKNESS_SWORD)
                .pattern(" A ")
                .pattern("CXC")
                .pattern(" B ")
                .input('A', Items.WITHER_SKELETON_SKULL)
                .input('B', Items.BLAZE_ROD)
                .input('X', ModItems.DARK_CORE)
                .input('C', Items.AMETHYST_SHARD)
                .criterion(hasItem(ModItems.DARK_CORE), conditionsFromItem(ModItems.DARK_CORE))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DARK_CORE)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" A ")
                .input('A', Items.TINTED_GLASS)
                .input('X', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

    }
}
