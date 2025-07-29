package net.lksls.legendaryserver.world.biome;

import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.world.ModOrePlacement;
import net.lksls.legendaryserver.world.biome.region.OverworldRegion;
import net.lksls.legendaryserver.world.gen.ModOreGeneration;
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
    private static final MaterialRules.MaterialRule DIM_STONE = makeStateRule(ModBlocks.DIM_STONE);
    private static final MaterialRules.MaterialRule ALMOST_DIM_STONE = makeStateRule(ModBlocks.ALMOST_DIM_STONE);
    private static final MaterialRules.MaterialRule EXCITING_DIM_STONE = makeStateRule(ModBlocks.EXCITING_DIM_STONE);
    private static final MaterialRules.MaterialRule STRANGE_DARKROOT_SOIL = makeStateRule(ModBlocks.STRANGE_DARKROOT_SOIL);
    private static final MaterialRules.MaterialRule BLUE_TERRACOTTA = makeStateRule(Blocks.BLUE_TERRACOTTA);
    private static final MaterialRules.MaterialRule MIDNIGHT_GRASS = makeStateRule(ModBlocks.MIDNIGHT_GRASS);

    private static final MaterialRules.MaterialRule ALTERNATIVE_END_STONE = makeStateRule(ModBlocks.ALTERNATIVE_END_STONE);

    private static final MaterialRules.MaterialRule OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final MaterialRules.MaterialRule END_STONE = makeStateRule(Blocks.END_STONE);
    private static final MaterialRules.MaterialRule MYSTERIOUS_END_ORE = makeStateRule(ModBlocks.MYSTERIOUS_END_ORE);

    private static final MaterialRules.MaterialRule GLOWSTONE = makeStateRule(Blocks.GLOWSTONE);
    private static final MaterialRules.MaterialRule NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final MaterialRules.MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);


    public static MaterialRules.MaterialRule makeNocturneValeRules() {
        // These are the rules for your biome's blocks.
        return MaterialRules.sequence(
                // Rule for Midnight Grass:
                // Apply Midnight Grass ONLY to the very top surface layer
                MaterialRules.condition(MaterialRules.biome(ModBiomes.NOCTURNE_VALE),
                        MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MIDNIGHT_GRASS)) // Changed from STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH
                ),


                // Rule for Darkroot Soil and Sculk below the surface
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, DARKROOT_SOIL), SCULK),
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, SCULK), GREEN_TERRACOTTA),

                // Default rule for the deepest layers (if not covered by above rules)
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, GREEN_TERRACOTTA)

        );
    }

    public static MaterialRules.MaterialRule makeLuminaraDepthsRules() {
        return MaterialRules.sequence(
                // Bedrock floor: Places bedrock from the bottom up to 5 blocks above
                MaterialRules.condition(MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(5)), BEDROCK),
                // Bedrock roof: Places bedrock everywhere except the top 5 blocks of the world
                MaterialRules.condition(MaterialRules.not(MaterialRules.verticalGradient("bedrock_roof", YOffset.belowTop(5), YOffset.getTop())), BEDROCK),

                // Then apply biome-specific rules
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.LUMINARA_DEPTHS), // Apply these rules only in the Luminara Depths biome
                        MaterialRules.sequence(
                                // Obsidian on the undersides of ceilings
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, OBSIDIAN),
                                // Mix of DIM_STONE and ALMOST_DIM_STONE on the undersides of floors
                                // Since direct random/noise methods are unavailable, this creates a striped pattern.
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                                        MaterialRules.sequence( // Use a sequence to alternate blocks
                                                // Place DIM_STONE on "even" Y-levels (or specific small vertical bands)
                                                // This creates thin horizontal stripes. Adjust the YOffset.fixed values
                                                // and the interval (e.g., 2, 4, etc.) to change stripe thickness.
                                                // For a more "mixed" look, you might need to experiment with very small intervals
                                                // or combine multiple such conditions.
                                                MaterialRules.condition(MaterialRules.verticalGradient("dim_stone_stripe", YOffset.fixed(0), YOffset.fixed(1)), DIM_STONE),
                                                // Place ALMOST_DIM_STONE on "odd" Y-levels (or the bands not covered by DIM_STONE)
                                                DIM_STONE // This will be placed if the DIM_STONE stripe condition is false
                                        )
                                ),
                                // Obsidian on floors within a specific depth range
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, OBSIDIAN),
                                // Default to ALMOST_DIM_STONE if not under a ceiling or floor in this biome
                                ALMOST_DIM_STONE
                        )
                )
        );
    }

    public static MaterialRules.MaterialRule makeAbyssOfLamentRules() {
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(ModBiomes.ABYSS_OF_LAMENT), ALTERNATIVE_END_STONE),
                // Default to end stone
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, END_STONE)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
