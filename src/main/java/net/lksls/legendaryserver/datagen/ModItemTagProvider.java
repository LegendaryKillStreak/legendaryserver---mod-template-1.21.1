package net.lksls.legendaryserver.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.util.ModTags;
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
                .add(ModItems.DARK_SOUL_CORE)
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
                .add(ModItems.COIN_100000)
                .add(ModItems.COIN_15000)
                .add(ModItems.COIN_20000)
                .add(ModItems.COIN_50000)
                .add(ModItems.COIN_75000)
                .add(ModItems.COIN_TWENTY)
                .add(ModItems.DUNGEON_KEY_SHADOWY_CREATURES)
                .add(ModItems.DUNGEON_KEY_ICY_CAVES)
                .add(ModItems.DUNGEON_KEY_DEADLY_HEAT)
                .add(ModItems.DUNGEON_ICON_SHADOWY_CREATURES)
                .add(ModItems.DUNGEON_ICON_ICY_CAVES)
                .add(ModItems.DUNGEON_ICON_DEADLY_HEAT)
                .add(ModItems.ECHO_DUST)
                .add(ModItems.RAW_NICKEL)
                .add(ModItems.NICKEL_INGOT)
                .add(ModItems.RAW_TITANIUM)
                .add(ModItems.TITANIUM_INGOT)
                .add(ModItems.TEARS_MUSIC_DISC)
                .add(ModItems.DRILL_ENGINE);



        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ECHO_DUST)
                .add(ModItems.NICKEL_INGOT)
                .add(ModItems.TITANIUM_INGOT);

        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.SOUL_CORE_ARMOR_SMITHING_TEMPLATE);






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


        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.LIGHT_CORE_HELMET)
                .add(ModItems.LIGHT_CORE_CHESTPLATE)
                .add(ModItems.LIGHT_CORE_LEGGINGS)
                .add(ModItems.LIGHT_CORE_BOOTS)
                .add(ModItems.DARK_CORE_HELMET)
                .add(ModItems.DARK_CORE_CHESTPLATE)
                .add(ModItems.DARK_CORE_LEGGINGS)
                .add(ModItems.DARK_CORE_BOOTS);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.LIGHT_CORE_CHESTPLATE)
                .add(ModItems.DARK_CORE_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.LIGHT_CORE_HELMET)
                .add(ModItems.DARK_CORE_HELMET);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.LIGHT_CORE_LEGGINGS)
                .add(ModItems.DARK_CORE_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.LIGHT_CORE_BOOTS)
                .add(ModItems.DARK_CORE_BOOTS);


        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.LIGHT_CORE_HELMET)
                .add(ModItems.LIGHT_CORE_CHESTPLATE)
                .add(ModItems.LIGHT_CORE_LEGGINGS)
                .add(ModItems.LIGHT_CORE_BOOTS)
                .add(ModItems.DARK_CORE_BOOTS)
                .add(ModItems.DARK_CORE_CHESTPLATE)
                .add(ModItems.DARK_CORE_HELMET)
                .add(ModItems.DARK_CORE_LEGGINGS);












    }
}
