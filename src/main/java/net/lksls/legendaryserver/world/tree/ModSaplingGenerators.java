package net.lksls.legendaryserver.world.tree;


import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator MIDNIGHTWOOD = new SaplingGenerator(LegendaryServerMod.MOD_ID + ":midnightwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.MIDNIGHTWOOD_KEY), Optional.empty());
}