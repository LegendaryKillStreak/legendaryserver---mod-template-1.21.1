package net.lksls.legendaryserver.rarity;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CustomRarity {
    public static final CustomRarity MYTHIC = new CustomRarity("Mythic", Formatting.RED);
    public static final CustomRarity DIVINE = new CustomRarity("Divine", Formatting.GOLD);
    public static final CustomRarity CORRUPTED = new CustomRarity("Corrupted", Formatting.DARK_RED);


    private final String name;
    private final Formatting color;

    public CustomRarity(String name, Formatting color) {
        this.name = name;
        this.color = color;
    }

    public Text formatName(Text itemName) {
        return itemName.copy().formatted(color);
    }

    public String getName() {
        return name;
    }

    public Formatting getColor() {
        return color;
    }

}
