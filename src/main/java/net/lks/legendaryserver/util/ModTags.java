package net.lks.legendaryserver.util;

import net.lks.legendaryserver.LegendaryServerMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import javax.swing.text.html.HTML;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
                    return TagKey.of(RegistryKeys.ITEM, Identifier.of(LegendaryServerMod.MOD_ID, name));
        }

    }


    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(LegendaryServerMod.MOD_ID, name));
        }
        public static final TagKey<Block> NEEDS_LIGHT_TOOL = createTag("needs_light_tool");
        public static final TagKey<Block> INCORRECT_FOR_LIGHT_TOOL = createTag("incorrect_for_light_tool");

        public static final TagKey<Block> NEEDS_DARK_TOOL = createTag("needs_dark_tool");
        public static final TagKey<Block> INCORRECT_FOR_DARK_TOOL = createTag("incorrect_for_dark_tool");

    }






}
