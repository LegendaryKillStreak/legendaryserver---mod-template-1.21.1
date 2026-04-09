package net.lksls.legendaryserver.rarity;

import net.minecraft.util.Formatting;

public enum ModRarity {

    MYTHIC(Formatting.RED),                     // static
    EXOTIC(Formatting.AQUA, Formatting.GREEN), // animated but NO glow
    SACRED(Formatting.DARK_BLUE),               // static but WILL glow
    SHINY_SACRED(Formatting.GOLD, Formatting.YELLOW), // animated gold glow
    ASTRAL,
    CORRUPTED(Formatting.DARK_PURPLE, Formatting.BLACK); // flicker

    public final Formatting[] colors;

    ModRarity(Formatting... colors) {
        this.colors = colors;
    }

    public Formatting getColorForTick(long tick) {
        if (colors.length == 0) return Formatting.WHITE;

        // CORRUPTED: chaotic flicker
        if (this == ModRarity.CORRUPTED) {
            // change every 2 ticks, but bias toward DARK_PURPLE
            boolean fastFlip = (tick % 4) < 1; // 1 tick out of 4 is "glitch"
            if (fastFlip) {
                return colors[1]; // BLACK
            } else {
                return colors[0]; // DARK_PURPLE
            }
        }



        // Default animation
        int index = (int)((tick / 10) % colors.length);
        return colors[index];
    }
}
