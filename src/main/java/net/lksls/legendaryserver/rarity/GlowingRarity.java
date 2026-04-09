package net.lksls.legendaryserver.rarity;

public enum GlowingRarity {
    NONE(false),
    WEAK(true),
    STRONG(true);

    public final boolean glows;

    GlowingRarity(boolean glows) {
        this.glows = glows;
    }
}
