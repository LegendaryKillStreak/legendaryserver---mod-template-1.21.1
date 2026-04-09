package net.lksls.legendaryserver.rarity;

import net.minecraft.util.Formatting;

public enum ModRarity {
    MYTHIC(Formatting.RED),
    EXOTIC(Formatting.AQUA, Formatting.GREEN),
    SACRED(Formatting.DARK_BLUE);

    public final Formatting[] colors;

    ModRarity(Formatting... colors) {
        this.colors = colors;
    }
}