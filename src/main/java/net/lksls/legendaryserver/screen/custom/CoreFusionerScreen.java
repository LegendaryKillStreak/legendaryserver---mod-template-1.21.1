package net.lksls.legendaryserver.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CoreFusionerScreen extends HandledScreen<CoreFusionerScreenHandler> {

    public static final Identifier GUI_TEXTURE =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/core_fusioner/core_fusioner_gui.png");

    public static final Identifier ARROW_TEXTURE =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/core_fusioner/arrow_progress.png");

    public CoreFusionerScreen(CoreFusionerScreenHandler handler,
                              PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        titleX = 10;
        playerInventoryTitleY = 1000; // hide player inventory label
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        // Base GUI
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Progress arrow
        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            int progress = handler.getScaledProgress();

            RenderSystem.setShaderTexture(0, ARROW_TEXTURE);

            context.drawTexture(
                    ARROW_TEXTURE,
                    x + 79, y + 37,   // GUI position
                    0, 0,             // UV
                    progress, 16,     // width = progress
                    24, 16            // full texture size
            );

            RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}

