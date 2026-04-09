package net.lksls.legendaryserver.rarity;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.text.Text;

public class RarityTooltipComponent implements TooltipComponent {
    private final Text text;
    private final ModRarity rarity;

    public RarityTooltipComponent(Text text, ModRarity rarity) {
        this.text = text;
        this.rarity = rarity;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 0;
    }
}
