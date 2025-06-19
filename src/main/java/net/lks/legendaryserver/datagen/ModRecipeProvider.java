package net.lks.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.lks.legendaryserver.LegendaryServerMod;
import net.lks.legendaryserver.block.ModBlocks;
import net.lks.legendaryserver.item.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_AXE)
                        .pattern("AC ")
                        .pattern("AB ")
                        .pattern(" B ")
                        .input('A', Items.PRISMARINE_SHARD)
                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DARKNESS_AXE)
                        .pattern("AC ")
                        .pattern("AB ")
                        .pattern(" B ")
                        .input('A', Items.TINTED_GLASS)
                        .input('B', Items.BLAZE_ROD)
                        .input('C', ModItems.DARK_CORE)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DARKNESS_PICKAXE)
                        .pattern("ACA")
                        .pattern(" B ")
                        .pattern(" B ")
                        .input('A', Items.TINTED_GLASS)
                        .input('B', Items.BLAZE_ROD)
                        .input('C', ModItems.DARK_CORE)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_PICKAXE)
                        .pattern("ACA")
                        .pattern(" B ")
                        .pattern(" B ")
                        .input('A', Items.PRISMARINE_SHARD)
                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_SHOVEL)
                        .pattern(" C ")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DARKNESS_SHOVEL)
                        .pattern(" C ")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.BLAZE_ROD)
                        .input('C', ModItems.DARK_CORE)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DARKNESS_HOE)
                        .pattern("AC ")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.BLAZE_ROD)
                        .input('C', ModItems.DARK_CORE)
                        .input('A', Items.TINTED_GLASS)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_HOE)
                        .pattern("AC ")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)
                        .input('A', Items.PRISMARINE_SHARD)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COIN)
                        .pattern(" C ")
                        .pattern("CCC")
                        .pattern(" C ")


                         .input('C', Items.DIAMOND)
                         .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COIN_FIVE)
                        .pattern(" C ")
                        .pattern("CCC")
                        .pattern(" C ")


                         .input('C', ModItems.COIN)
                         .criterion(hasItem(ModItems.COIN), conditionsFromItem(ModItems.COIN_FIVE))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COIN_TWENTY)
                        .pattern(" C ")
                        .pattern("C C")
                        .pattern(" C ")


                         .input('C', ModItems.COIN_FIVE)
                         .criterion(hasItem(ModItems.COIN_FIVE), conditionsFromItem(ModItems.COIN_TWENTY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COIN_TEN);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.LIGHT_CORE, RecipeCategory.MISC, ModBlocks.LIGHT_CORE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.DARK_CORE, RecipeCategory.MISC, ModBlocks.DARK_CORE_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LIGHT_CORE)
                .pattern("BAB")
                .pattern("AXA")
                .pattern("BAB")
                .input('A', Items.GLOWSTONE_DUST)
                .input('X', Items.NETHER_STAR)
                .input('B', Items.PRISMARINE_CRYSTALS)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter, "light_core_shaped");


        offerSmithingTrimRecipe(exporter, ModItems.SOUL_CORE_SMITHING_TEMPLATE, Identifier.of(LegendaryServerMod.MOD_ID, "soul_core"));


















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
        ;












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

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LIGHT_CORE_CHESTPLATE)
                .pattern("X X")
                .pattern("AXA")
                .pattern("XAX")
                .input('A', Items.DIAMOND)
                .input('X', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LIGHT_CORE_HELMET)
                .pattern("AXA")
                .pattern("X X")
                .pattern("   ")
                .input('A', Items.DIAMOND)
                .input('X', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LIGHT_CORE_LEGGINGS)
                .pattern("AXA")
                .pattern("A A")
                .pattern("X X")
                .input('A', Items.DIAMOND)
                .input('X', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LIGHT_CORE_BOOTS)
                .pattern("X X")
                .pattern("A A")
                .pattern("   ")
                .input('A', Items.DIAMOND)
                .input('X', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_CORE_CHESTPLATE)
                .pattern("X X")
                .pattern("AXA")
                .pattern("XAX")
                .input('A', Items.NETHERITE_INGOT)
                .input('X', ModItems.DARK_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_CORE_HELMET)
                .pattern("AXA")
                .pattern("X X")
                .pattern("   ")
                .input('A', Items.NETHERITE_INGOT)
                .input('X', ModItems.DARK_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_CORE_LEGGINGS)
                .pattern("AXA")
                .pattern("A A")
                .pattern("X X")
                .input('A', Items.NETHERITE_INGOT)
                .input('X', ModItems.DARK_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.DARK_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_CORE_BOOTS)
                .pattern("X X")
                .pattern("A A")
                .pattern("   ")
                .input('A', Items.NETHERITE_INGOT)
                .input('X', ModItems.DARK_CORE)
                .criterion(hasItem(ModItems.DARK_CORE), conditionsFromItem(ModItems.DARK_CORE_BOOTS))
                .offerTo(exporter);







        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DARK_CORE)
                .pattern("BAB")
                .pattern("AXA")
                .pattern("BAB")
                .input('A', Items.TINTED_GLASS)
                .input('X', ModItems.LIGHT_CORE)
                .input('B', ModItems.DARK_SOUL_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter, "dark_core_shaped");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DARK_SOUL_CORE)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" A ")
                .input('A', Items.NETHERITE_INGOT)
                .input('X', Items.SOUL_SAND)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);









    }
}
