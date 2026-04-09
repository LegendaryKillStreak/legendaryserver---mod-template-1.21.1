package net.lksls.legendaryserver.rarity;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class RarityTooltipRenderer implements TooltipComponent {


    private static final Identifier END_PORTAL =
            Identifier.of("minecraft", "textures/block/end_portal.png");
    private final RarityTooltipData data;

    public RarityTooltipRenderer(RarityTooltipData data) {
        this.data = data;
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return textRenderer.getWidth(data.text());
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {

        long tick = System.currentTimeMillis() / 30;

        ModRarity rarity = data.rarity();

        switch (rarity) {

            case SACRED -> drawSacred(textRenderer, x, y, context, tick);

            case CORRUPTED -> drawCorrupted(textRenderer, x, y, context, tick);

            case EXOTIC -> drawExotic(textRenderer, x, y, context, tick);

            case ASTRAL -> drawAstral(textRenderer, x, y, context, tick);

            case MYTHIC -> drawStatic(textRenderer, x, y, context, 0xFFAA0000); // red

            default -> drawStatic(textRenderer, x, y, context, 0xFFFFFFFF);
        }
    }

    // ---------------------------------------------------------
    //  SACRED — GOLD BASE + DIAGONAL SHINE
    // ---------------------------------------------------------
    private void drawSacred(TextRenderer tr, int x, int y, DrawContext ctx, long tick) {

        // base gold text
        int baseGold = 0xFFFED15;
        ctx.drawText(tr, data.text(), x, y, baseGold, false);

        int width = tr.getWidth(data.text());
        int bandWidth = 12;

        int cycle = width + bandWidth;
        int offset = (int)(tick % cycle);
        int bandX = x + offset - bandWidth;

        int highlight = 0x80FFFFFF; // 50% white

        // diagonal shine (slanted band)
        for (int i = 0; i < bandWidth; i++) {
            int sx = bandX + i;
            int syTop = y - 1 + (i / 3);
            int syBottom = y + 9 + (i / 3);
            ctx.fill(sx, syTop, sx + 1, syBottom, highlight);
        }
    }

    // ---------------------------------------------------------
    //  CORRUPTED — PURPLE/BLACK GLITCH
    // ---------------------------------------------------------
    private void drawCorrupted(TextRenderer tr, int x, int y, DrawContext ctx, long tick) {

        boolean glitch = (tick % 4) < 1;
        int color = glitch ? 0xFF000000 : 0xFF4B0082; // black / indigo

        ctx.drawText(tr, data.text(), x, y, color, false);
    }

    // ---------------------------------------------------------
    //  EXOTIC — SMOOTH FADE BETWEEN TWO COLORS
    // ---------------------------------------------------------
    private void drawExotic(TextRenderer tr, int x, int y, DrawContext ctx, long tick) {

        // fade factor 0..1
        float t = (float)((tick % 60) / 60.0);

        int c1 = 0xFF00FFFF; // aqua
        int c2 = 0xFF00FF00; // green

        int r = (int)( ((c1 >> 16) & 0xFF) * (1 - t) + ((c2 >> 16) & 0xFF) * t );
        int g = (int)( ((c1 >> 8) & 0xFF) * (1 - t) + ((c2 >> 8) & 0xFF) * t );
        int b = (int)( (c1 & 0xFF) * (1 - t) + (c2 & 0xFF) * t );

        int color = 0xFF000000 | (r << 16) | (g << 8) | b;

        ctx.drawText(tr, data.text(), x, y, color, false);
    }

    // ---------------------------------------------------------
    //  STATIC COLOR (MYTHIC, DEFAULT)
    // ---------------------------------------------------------
    private void drawStatic(TextRenderer tr, int x, int y, DrawContext ctx, int color) {
        ctx.drawText(tr, data.text(), x, y, color, false);
    }

    private void drawAstral(TextRenderer tr, int x, int y, DrawContext ctx, long tick) {

        String text = data.text();
        int width = tr.getWidth(text);
        int height = 10;

        // 1. Draw the end portal texture as the text fill
        // ------------------------------------------------




        // Bind texture
        ctx.drawTexture(
                END_PORTAL,
                x, y,
                0, 0,
                width, height,
                width, height
        );

        // 2. Draw the text mask (so the texture only appears where letters are)
        // ------------------------------------------------
        // Render text in full white but with alpha mask mode
        ctx.enableScissor(x, y, x + width, y + height);
        ctx.drawText(tr, text, x, y, 0xFFFFFFFF, false);
        ctx.disableScissor();

        // 3. White glint sweep (diagonal)
        // ------------------------------------------------
        int bandWidth = 14;
        int cycle = width + bandWidth;
        int offset = (int)(tick % cycle);
        int bandX = x + offset - bandWidth;

        int glintColor = 0x80FFFFFF; // 50% white

        for (int i = 0; i < bandWidth; i++) {
            int sx = bandX + i;
            int syTop = y - 2 + (i / 3);
            int syBottom = y + 12 + (i / 3);
            ctx.fill(sx, syTop, sx + 1, syBottom, glintColor);
        }
    }

}

