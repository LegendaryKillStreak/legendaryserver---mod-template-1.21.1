package net.lksls.legendaryserver.recipe;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {

    public static final RecipeSerializer<RefineryRecipe> REFINERY_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(LegendaryServerMod.MOD_ID, "refining"), new RefineryRecipe.Serializer());

    public static final RecipeType<RefineryRecipe> REFINERY_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(LegendaryServerMod.MOD_ID,"refining"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "refining";
                }
            }
    );

    public static void registerRecipes() {
        LegendaryServerMod.LOGGER.info("Registering Custom Recipes for " + LegendaryServerMod.MOD_ID);
    }
}
