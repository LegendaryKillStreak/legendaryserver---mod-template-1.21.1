package net.lksls.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.datafixer.mapping.WoodRecipeMapping;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        //
        // NICKEL
        //

        // RAW NICKEL → INGOT
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModItems.RAW_NICKEL),
                        RecipeCategory.MISC,
                        ModItems.NICKEL_INGOT,
                        0.25f,
                        200
                )
                .criterion("has_raw_nickel", conditionsFromItem(ModItems.RAW_NICKEL))
                .group("nickel_ingot")
                .offerTo(exporter, Identifier.of("lksls", "nickel_ingot_from_raw_nickel"));

        // NICKEL ORE → INGOT
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModBlocks.NICKEL_ORE),
                        RecipeCategory.MISC,
                        ModItems.NICKEL_INGOT,
                        0.25f,
                        200
                )
                .criterion("has_nickel_ore", conditionsFromItem(ModBlocks.NICKEL_ORE))
                .group("nickel_ingot")
                .offerTo(exporter, Identifier.of("lksls", "nickel_ingot_from_nickel_ore"));

        // DEEPSLATE NICKEL ORE → INGOT
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModBlocks.NICKEL_DEEPSLATE_ORE),
                        RecipeCategory.MISC,
                        ModItems.NICKEL_INGOT,
                        0.25f,
                        200
                )
                .criterion("has_deepslate_nickel_ore", conditionsFromItem(ModBlocks.NICKEL_DEEPSLATE_ORE))
                .group("nickel_ingot")
                .offerTo(exporter, Identifier.of("lksls", "nickel_ingot_from_deepslate_nickel_ore"));

        //
        // ZINC
        //

        // ZINC ITEM → ZINC INGOT
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModItems.ZINC),
                        RecipeCategory.MISC,
                        ModItems.ZINC_INGOT,
                        0.50f,
                        300
                )
                .criterion("has_zinc", conditionsFromItem(ModItems.ZINC))
                .group("zinc_ingot")
                .offerTo(exporter, Identifier.of("lksls", "zinc_ingot_from_zinc"));

        //
        // SPHALERITE → ZINC
        //

        // CRUSHED SPHALERITE → ZINC
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModItems.CRUSHED_SPHALERITE),
                        RecipeCategory.MISC,
                        ModItems.ZINC,
                        0.50f,
                        300
                )
                .criterion("has_crushed_sphalerite", conditionsFromItem(ModItems.CRUSHED_SPHALERITE))
                .group("zinc")
                .offerTo(exporter, Identifier.of("lksls", "zinc_from_crushed_sphalerite"));

        // NETHER SPHALERITE ORE → ZINC
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModBlocks.NETHER_SPHALERITE_ORE),
                        RecipeCategory.MISC,
                        ModItems.ZINC,
                        0.50f,
                        300
                )
                .criterion("has_nether_sphalerite_ore", conditionsFromItem(ModBlocks.NETHER_SPHALERITE_ORE))
                .group("zinc")
                .offerTo(exporter, Identifier.of("lksls", "zinc_from_nether_sphalerite_ore"));

        //
        // TITANIUM
        //

        // RAW TITANIUM → INGOT
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModItems.RAW_TITANIUM),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_INGOT,
                        0.4f,
                        600
                )
                .criterion("has_raw_titanium", conditionsFromItem(ModItems.RAW_TITANIUM))
                .group("titanium_ingot")
                .offerTo(exporter, Identifier.of("lksls", "titanium_ingot_from_raw_titanium"));

        // TITANIUM ORE → INGOT
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModBlocks.TITANIUM_ORE),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_INGOT,
                        0.4f,
                        600
                )
                .criterion("has_titanium_ore", conditionsFromItem(ModBlocks.TITANIUM_ORE))
                .group("titanium_ingot")
                .offerTo(exporter, Identifier.of("lksls", "titanium_ingot_from_titanium_ore"));

        // DEEPSLATE TITANIUM ORE → INGOT
        CookingRecipeJsonBuilder
                .createSmelting(
                        Ingredient.ofItems(ModBlocks.TITANIUM_DEEPSLATE_ORE),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_INGOT,
                        0.4f,
                        600
                )
                .criterion("has_deepslate_titanium_ore", conditionsFromItem(ModBlocks.TITANIUM_DEEPSLATE_ORE))
                .group("titanium_ingot")
                .offerTo(exporter, Identifier.of("lksls", "titanium_ingot_from_deepslate_titanium_ore"));

        //
// BLASTING RECIPES
//

// NICKEL

// RAW NICKEL → INGOT (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModItems.RAW_NICKEL),
                        RecipeCategory.MISC,
                        ModItems.NICKEL_INGOT,
                        0.25f,
                        100
                )
                .criterion("has_raw_nickel", conditionsFromItem(ModItems.RAW_NICKEL))
                .group("nickel_ingot")
                .offerTo(exporter, Identifier.of("lksls", "nickel_ingot_from_raw_nickel_blasting"));

// NICKEL ORE → INGOT (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModBlocks.NICKEL_ORE),
                        RecipeCategory.MISC,
                        ModItems.NICKEL_INGOT,
                        0.25f,
                        100
                )
                .criterion("has_nickel_ore", conditionsFromItem(ModBlocks.NICKEL_ORE))
                .group("nickel_ingot")
                .offerTo(exporter, Identifier.of("lksls", "nickel_ingot_from_nickel_ore_blasting"));

// DEEPSLATE NICKEL ORE → INGOT (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModBlocks.NICKEL_DEEPSLATE_ORE),
                        RecipeCategory.MISC,
                        ModItems.NICKEL_INGOT,
                        0.25f,
                        100
                )
                .criterion("has_deepslate_nickel_ore", conditionsFromItem(ModBlocks.NICKEL_DEEPSLATE_ORE))
                .group("nickel_ingot")
                .offerTo(exporter, Identifier.of("lksls", "nickel_ingot_from_deepslate_nickel_ore_blasting"));


// ZINC

// ZINC ITEM → ZINC INGOT (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModItems.ZINC),
                        RecipeCategory.MISC,
                        ModItems.ZINC_INGOT,
                        0.50f,
                        150
                )
                .criterion("has_zinc", conditionsFromItem(ModItems.ZINC))
                .group("zinc_ingot")
                .offerTo(exporter, Identifier.of("lksls", "zinc_ingot_from_zinc_blasting"));


// SPHALERITE → ZINC

// CRUSHED SPHALERITE → ZINC (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModItems.CRUSHED_SPHALERITE),
                        RecipeCategory.MISC,
                        ModItems.ZINC,
                        0.50f,
                        150
                )
                .criterion("has_crushed_sphalerite", conditionsFromItem(ModItems.CRUSHED_SPHALERITE))
                .group("zinc")
                .offerTo(exporter, Identifier.of("lksls", "zinc_from_crushed_sphalerite_blasting"));

// NETHER SPHALERITE ORE → ZINC (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModBlocks.NETHER_SPHALERITE_ORE),
                        RecipeCategory.MISC,
                        ModItems.ZINC,
                        0.50f,
                        150
                )
                .criterion("has_nether_sphalerite_ore", conditionsFromItem(ModBlocks.NETHER_SPHALERITE_ORE))
                .group("zinc")
                .offerTo(exporter, Identifier.of("lksls", "zinc_from_nether_sphalerite_ore_blasting"));


// TITANIUM

// RAW TITANIUM → INGOT (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModItems.RAW_TITANIUM),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_INGOT,
                        0.4f,
                        300
                )
                .criterion("has_raw_titanium", conditionsFromItem(ModItems.RAW_TITANIUM))
                .group("titanium_ingot")
                .offerTo(exporter, Identifier.of("lksls", "titanium_ingot_from_raw_titanium_blasting"));

// TITANIUM ORE → INGOT (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModBlocks.TITANIUM_ORE),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_INGOT,
                        0.4f,
                        300
                )
                .criterion("has_titanium_ore", conditionsFromItem(ModBlocks.TITANIUM_ORE))
                .group("titanium_ingot")
                .offerTo(exporter, Identifier.of("lksls", "titanium_ingot_from_titanium_ore_blasting"));

// DEEPSLATE TITANIUM ORE → INGOT (BLASTING)
        CookingRecipeJsonBuilder
                .createBlasting(
                        Ingredient.ofItems(ModBlocks.TITANIUM_DEEPSLATE_ORE),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_INGOT,
                        0.4f,
                        300
                )
                .criterion("has_deepslate_titanium_ore", conditionsFromItem(ModBlocks.TITANIUM_DEEPSLATE_ORE))
                .group("titanium_ingot")
                .offerTo(exporter, Identifier.of("lksls", "titanium_ingot_from_deepslate_titanium_ore_blasting"));



        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.NICKEL_INGOT, RecipeCategory.DECORATIONS, ModBlocks.NICKEL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.TITANIUM_INGOT, RecipeCategory.DECORATIONS, ModBlocks.TITANIUM_BLOCK);



        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_NICKEL_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_NICKEL)
                .criterion(hasItem(ModItems.RAW_NICKEL), conditionsFromItem(ModItems.RAW_NICKEL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIM_ROSE_QUARTZ_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.DIM_ROSE_QUARTZ)
                .criterion(hasItem(ModItems.DIM_ROSE_QUARTZ), conditionsFromItem(ModItems.DIM_ROSE_QUARTZ))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SANDSTONE,4)
                .pattern("RRX")
                .pattern("RRX")
                .pattern("XXX")
                .input('R', Blocks.CUT_SANDSTONE)
                .input('X', Items.HONEYCOMB)
                .criterion(hasItem(Blocks.SAND), conditionsFromItem(Blocks.SAND))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_TITANIUM_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_TITANIUM)
                .criterion(hasItem(ModItems.RAW_TITANIUM), conditionsFromItem(ModItems.RAW_TITANIUM))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_NICKEL, 9)
                .input(ModBlocks.RAW_NICKEL_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_NICKEL_BLOCK), conditionsFromItem(ModBlocks.RAW_NICKEL_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_TITANIUM, 9)
                .input(ModBlocks.RAW_TITANIUM_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_TITANIUM_BLOCK), conditionsFromItem(ModBlocks.RAW_TITANIUM_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_AXE)
                        .pattern("CC ")
                        .pattern("CB ")
                        .pattern(" B ")

                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DARKNESS_AXE)
                        .pattern("CC ")
                        .pattern("CB ")
                        .pattern(" B ")
                        .input('B', Items.BLAZE_ROD)
                        .input('C', ModItems.DARK_CORE)
                .criterion(hasItem(ModItems.DARK_CORE), conditionsFromItem(ModItems.DARK_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DARKNESS_PICKAXE)
                        .pattern("CCC")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.BLAZE_ROD)
                        .input('C', ModItems.DARK_CORE)
                .criterion(hasItem(ModItems.DARK_CORE), conditionsFromItem(ModItems.DARK_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_PICKAXE)
                        .pattern("CCC")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_SHOVEL)
                        .pattern(" C ")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
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
                        .pattern("CC ")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.BLAZE_ROD)
                        .input('C', ModItems.DARK_CORE)

                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.LIGHT_HOE)
                        .pattern("CC ")
                        .pattern(" B ")
                        .pattern(" B ")

                        .input('B', Items.END_ROD)
                        .input('C', ModItems.LIGHT_CORE)

                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COIN_FIVE)
                        .pattern(" C ")
                        .pattern("CCC")
                        .pattern(" C ")


                         .input('C', ModItems.COIN)
                         .criterion(hasItem(ModItems.COIN), conditionsFromItem(ModItems.COIN))
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
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter, "light_core_shaped");


        offerSmithingTrimRecipe(exporter, ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE, Identifier.of(LegendaryServerMod.MOD_ID, "soul_core"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE, 2)
                .pattern("ABA")
                .pattern("AXA")
                .pattern("AAA")
                .input('A', Items.DIAMOND)
                .input('X', ModItems.DARK_SOUL_CORE)
                .input('B', ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE)
                .criterion(hasItem(ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE), conditionsFromItem(ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE))
                .offerTo(exporter, "soul_core_template_shaped");






















        ;
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LIGHT_SWORD)
                .pattern(" X ")
                .pattern(" X ")
                .pattern(" B ")
                .input('B', Items.END_ROD)
                .input('X', ModItems.LIGHT_CORE)
                .criterion(hasItem(ModItems.LIGHT_CORE), conditionsFromItem(ModItems.LIGHT_CORE))
                .offerTo(exporter);
        ;












        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARKNESS_SWORD)
                .pattern(" X ")
                .pattern(" X ")
                .pattern(" B ")
                .input('B', Items.BLAZE_ROD)
                .input('X', ModItems.DARK_CORE)
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

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.MIDNIGHTWOOD_PLANKS,4)
                .input(ModBlocks.MIDNIGHTWOOD_LOG)
                .criterion(hasItem(ModBlocks.MIDNIGHTWOOD_LOG), conditionsFromItem(ModBlocks.MIDNIGHTWOOD_LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.MIDNIGHTWOOD_WOOD, 3)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModBlocks.MIDNIGHTWOOD_LOG)
                .criterion(hasItem(ModBlocks.MIDNIGHTWOOD_LOG), conditionsFromItem(ModBlocks.MIDNIGHTWOOD_LOG))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.MIDNIGHTWOOD_PLANKS,4)
                .input(ModBlocks.MIDNIGHTWOOD_WOOD  )
                .criterion(hasItem(ModBlocks.MIDNIGHTWOOD_WOOD), conditionsFromItem(ModBlocks.MIDNIGHTWOOD_WOOD))
                .offerTo(exporter, "wood_to_planks_midnight");




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
                .criterion(hasItem(ModItems.DARK_SOUL_CORE), conditionsFromItem(ModItems.DARK_SOUL_CORE))
                .offerTo(exporter, "dark_core_shaped");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DARK_SOUL_CORE)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" A ")
                .input('A', Items.NETHERITE_INGOT)
                .input('X', Items.SOUL_SAND)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DRILL_ENGINE)
                .pattern("ABA")
                .pattern("AXA")
                .pattern("ADA")
                .input('A', ModItems.NICKEL_INGOT)
                .input('X', Items.RECOVERY_COMPASS)
                .input('B', Items.REDSTONE_TORCH)
                .input('D', Blocks.LODESTONE)
                .criterion(hasItem(ModItems.NICKEL_INGOT), conditionsFromItem(ModItems.NICKEL_INGOT))
                .offerTo(exporter);



        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DARK_SHARD)
                .pattern("DAB")
                .pattern("AXA")
                .pattern("BAD")
                .input('B', ModItems.REFINED_NOCTYRIAN)
                .input('X', ModItems.DARK_CORE)
                .input('A', ModItems.FUSED_SILICA_POWDER)
                .input('D', ModItems.REFINED_XENTHRITE)
                .criterion(hasItem(ModItems.FUSED_SILICA_POWDER), conditionsFromItem(ModItems.FUSED_SILICA_POWDER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CYLINDER_ENGINE)
                .pattern(" Q ")
                .pattern(" D ")
                .pattern("AQ ")
                .input('A', Items.HEAVY_CORE)
                .input('D', Items.PISTON)
                .input('Q', ModItems.DRILL_ENGINE)
                .criterion(hasItem(ModItems.DRILL_ENGINE), conditionsFromItem(ModItems.DRILL_ENGINE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NICKEL_ZINC_BATTERY)
                .pattern("ABA")
                .pattern("AXA")
                .pattern("AXA")
                .input('A', ModItems.NICKEL_INGOT)
                .input('X', ModItems.ZINC_INGOT)
                .input('B', Items.IRON_INGOT)
                .criterion(hasItem(ModItems.ZINC_INGOT), conditionsFromItem(ModItems.NICKEL_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.REFINERY)
                .pattern("ABF")
                .pattern("DXD")
                .pattern("BEB")
                .input('A', ModItems.WATER_CONTAINER)
                .input('X', Items.IRON_TRAPDOOR)
                .input('F', ModItems.COPPER_CHIP)
                .input('B', Items.POLISHED_DEEPSLATE)
                .input('E', ModItems.CYLINDER_ENGINE)
                .input('D', ModItems.ZINC_INGOT)
                .criterion(hasItem(ModItems.ZINC_INGOT), conditionsFromItem(ModItems.ZINC_INGOT))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ECHO_DUST)
                .pattern(" X")
                .pattern("X ")
                .input('X', Items.ECHO_SHARD)
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.XENTHRITE_ROD)
                .pattern("XAX")
                .pattern("YAY")
                .pattern("XAX")
                .input('X', ModItems.TITANIUM_INGOT)
                .input('A', ModItems.REFINED_XENTHRITE)
                .input('Y', Items.BLAZE_ROD)
                .criterion(hasItem(ModItems.TITANIUM_INGOT), conditionsFromItem(ModItems.TITANIUM_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FUSED_SILICA_POWDER)
                .pattern("YX")
                .pattern("XY")
                .input('X', Items.QUARTZ)
                .input('Y', Items.GLASS)
                .criterion(hasItem(Items.QUARTZ), conditionsFromItem(Items.QUARTZ))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COPPER_CHIP)
                .pattern("YXX")
                .pattern("XYX")
                .pattern("XYX")
                .input('X', ModItems.FUSED_SILICA_POWDER)
                .input('Y', Items.COPPER_INGOT)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WATER_CONTAINER)
                .pattern("XXX")
                .pattern("XYX")
                .pattern("XXX")
                .input('X', Items.GLASS)
                .input('Y', Items.WATER_BUCKET)
                .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DARKNESS_DRILL)
                .pattern(" X ")
                .pattern("XXX")
                .pattern("BAB")
                .input('A', ModItems.DRILL_ENGINE)
                .input('B', Items.PISTON)
                .input('X', ModItems.DARK_CORE)
                .criterion(hasItem(ModItems.DRILL_ENGINE), conditionsFromItem(ModItems.DRILL_ENGINE))
                .offerTo(exporter);











    }
}
