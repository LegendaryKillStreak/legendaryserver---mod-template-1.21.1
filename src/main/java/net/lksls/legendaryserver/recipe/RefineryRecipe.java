package net.lksls.legendaryserver.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record RefineryRecipe(Ingredient inputItem, ItemStack output,int count, int processingTime) implements Recipe<RefineryRecipeInput> {
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return Recipe.super.getIngredients();
    }

    @Override
    public boolean matches(RefineryRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }
        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(RefineryRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.REFINERY_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.REFINERY_TYPE;
    }

    public static class Serializer implements RecipeSerializer<RefineryRecipe> {

        public static final MapCodec<RefineryRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(RefineryRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(RefineryRecipe::output),
                Codec.INT.fieldOf("count").forGetter(RefineryRecipe::count),
                Codec.INT.fieldOf("processingTime").forGetter(RefineryRecipe::processingTime)
        ).apply(inst, RefineryRecipe::new));


        public static final PacketCodec<RegistryByteBuf, RefineryRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, RefineryRecipe::inputItem,
                        ItemStack.PACKET_CODEC, RefineryRecipe::output,
                        PacketCodecs.INTEGER, RefineryRecipe::count,
                        PacketCodecs.INTEGER, RefineryRecipe::processingTime,
                        RefineryRecipe::new
                );




        @Override
        public MapCodec<RefineryRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, RefineryRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
