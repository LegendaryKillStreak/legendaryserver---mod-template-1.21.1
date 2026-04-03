package net.lksls.legendaryserver.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RefineryScreen extends HandledScreen<RefineryScreenHandler> {
    public static final Identifier GUI_TEXTURE =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/refinery/refinery_gui.png");
    public static final Identifier ARROW_TEXTURE =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/refinery/arrow_progress.png");
    public static final Identifier REFINERY_DEBRIS_TEXTURE =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/item/refinery_filter_debris_icon.png");
    public static final Identifier REFINERY_BAR =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/refinery/refinery_bar.png");
    public static final Identifier REFINERY_ENERGY_BAR =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/refinery/refinery_energy_bar.png");

    public RefineryScreen(RefineryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        titleX = 27;

        playerInventoryTitleY = 1000;

    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f,1f,1f,1f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) /2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(context, x, y);
        renderProgressDebris(context, x, y);
        renderLiquidBar(context, x, y);
        renderEnergyBar(context, x, y);


    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 105, y + 34, 0, 0,
                    handler.getScaledArrowProgress(), 16, 24,16);
        }
    }

    private void renderProgressDebris(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(REFINERY_DEBRIS_TEXTURE, x + 59, y + 36, 0, 0,
                    handler.getScaledDebrisProgress(), 21, 21, 21);
        }
    }

    private void renderLiquidBar(DrawContext context, int x, int y) {
        int height = getLiquidBarHeight();
        if (height <= 0) return;

        RenderSystem.setShaderTexture(0, REFINERY_BAR);

        int barX = x + 7;                     // GUI position (change if needed)
        int barY = y + 6 + (52 - height);     // drain top → bottom

        context.drawTexture(
                REFINERY_BAR,
                barX, barY,
                0, 52 - height,                // UV inside the bar texture
                18, height,                    // draw size
                18, 52                         // full texture size
        );
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

    }

    private void renderEnergyBar(DrawContext context, int x, int y) {


        int height = getEnergyBarHeight();
        if (height <= 0) return;

        // Bind the energy bar texture
        RenderSystem.setShaderTexture(0, REFINERY_ENERGY_BAR);

        // GUI position — YOU will adjust these
        int barX = x + 154;
        int barY = y + 8 + (50 - height);

        // Draw the bar
        context.drawTexture(
                REFINERY_ENERGY_BAR,
                barX, barY,          // GUI position
                0, 50 - height,      // UV inside the 12×50 texture
                12, height,          // draw only the visible portion
                12, 50               // full texture size
        );
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

    }






    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
    private int getLiquidBarHeight() {
        int liquid = handler.getDelegate().get(2);
        int maxLiquid = handler.getDelegate().get(3);
        if (maxLiquid == 0) return 0;
        return (int)((liquid / (float)maxLiquid) * 52);
    }


    private int getEnergyBarHeight() {
        int energy = handler.getDelegate().get(4);
        int maxEnergy = handler.getDelegate().get(5);

        if (maxEnergy == 0) return 0;

        return (int)((energy / (float)maxEnergy) * 50);
    }

}
