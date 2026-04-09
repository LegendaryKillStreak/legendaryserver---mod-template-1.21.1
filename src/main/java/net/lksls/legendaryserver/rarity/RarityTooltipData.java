package net.lksls.legendaryserver.rarity;


import net.minecraft.item.tooltip.TooltipData;

public record RarityTooltipData(String text, ModRarity rarity) implements TooltipData {}
