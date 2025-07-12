package net.lksls.legendaryserver.world.biome;

import net.lksls.legendaryserver.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule GREEN_TERRACOTTA = makeStateRule(Blocks.GREEN_TERRACOTTA);
    private static final MaterialRules.MaterialRule SCULK = makeStateRule(Blocks.SCULK);
    private static final MaterialRules.MaterialRule RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final MaterialRules.MaterialRule DARKROOT_SOIL = makeStateRule(ModBlocks.DARKROOT_SOIL);
    private static final MaterialRules.MaterialRule BLUE_TERRACOTTA = makeStateRule(Blocks.BLUE_TERRACOTTA);
    private static final MaterialRules.MaterialRule MIDNIGHT_GRASS = makeStateRule(ModBlocks.MIDNIGHT_GRASS);

    private static final MaterialRules.MaterialRule OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final MaterialRules.MaterialRule END_STONE = makeStateRule(Blocks.END_STONE);

    private static final MaterialRules.MaterialRule GLOWSTONE = makeStateRule(Blocks.GLOWSTONE);
    private static final MaterialRules.MaterialRule NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final MaterialRules.MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);


    public static MaterialRules.MaterialRule makeNocturneValeRules() {
        // These are the rules for your biome's blocks.
        // Default to green terracotta
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(ModBiomes.NOCTURNE_VALE),
                        MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MIDNIGHT_GRASS))
                ), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, DARKROOT_SOIL), SCULK),
                // Default to green terracotta
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, GREEN_TERRACOTTA)

        );
    }

    public static MaterialRules.MaterialRule makeLuminaraDepthsRules() {
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(5)), BEDROCK),
                MaterialRules.condition(MaterialRules.not(MaterialRules.verticalGradient("bedrock_roof", YOffset.belowTop(5), YOffset.getTop())), BEDROCK),

                // Then apply biome-specific rules
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.LUMINARA_DEPTHS),
                        MaterialRules.sequence(
                                // Obsidian on the undersides of ceilings
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, OBSIDIAN),
                                // Obsidian on the undersides of floors (though less common in Nether caves)
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, GLOWSTONE),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, OBSIDIAN),
                                // Default to glowstone if not under a ceiling or floor
                                GLOWSTONE))
        );
    }

    public static MaterialRules.MaterialRule makeAbyssOfLamentRules() {
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(ModBiomes.ABYSS_OF_LAMENT), OBSIDIAN),
                // Default to end stone
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, END_STONE)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
