package net.lksls.legendaryserver.tag;

import net.lksls.legendaryserver.LegendaryServerMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> DARKROOT_SOIL_REPLACEABLES =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(LegendaryServerMod.MOD_ID, "darkroot_soil_replaceables"));
    public static final TagKey<Block> DIM_STONE_REPLACABLES =
            TagKey.of(RegistryKeys.BLOCK, Identifier.of(LegendaryServerMod.MOD_ID, "dim_stone_replaceables"));

    // Add more tags here if needed
}
