package net.lks.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.lks.legendaryserver.item.ModItems;
import net.lks.legendaryserver.util.ModTags;
import net.minecraft.data.server.tag.ItemTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.LIGHT_CORE)
                .add(ModItems.DARK_CORE);


        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.COIN)
                .add(ModItems.COIN_TEN)
                .add(ModItems.COIN_FIVE)
                .add(ModItems.COIN_FIFTY)
                .add(ModItems.COIN_100)
                .add(ModItems.COIN_150)
                .add(ModItems.COIN_200)
                .add(ModItems.COIN_250)
                .add(ModItems.COIN_500)
                .add(ModItems.COIN_750)
                .add(ModItems.COIN_1000)
                .add(ModItems.COIN_2000)
                .add(ModItems.COIN_5000)
                .add(ModItems.COIN_10000)
                .add(ModItems.COIN_15000)
                .add(ModItems.COIN_TWENTY);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.LIGHT_PICKAXE)
                .add(ModItems.DARKNESS_PICKAXE);







        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.DARKNESS_HOE)
                .add(ModItems.LIGHT_HOE);


        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.LIGHT_SHOVEL)
                .add(ModItems.DARKNESS_SHOVEL);







        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.DARKNESS_AXE)
                .add(ModItems.LIGHT_AXE);





        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.LIGHT_SWORD)





                .add(ModItems.DARKNESS_SWORD);





    }
}
