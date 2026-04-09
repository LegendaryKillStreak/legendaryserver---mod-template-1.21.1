package net.lksls.legendaryserver.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.block.entity.custom.CustomSpawnerBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import java.util.logging.Logger;


public class CustomSpawnerScreen extends HandledScreen<CustomSpawnerScreenHandler> {

    private static final Identifier TEXTURE =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/custom_spawner/custom_spawner_gui.png");

    private static final Logger LOGGER = Logger.getLogger("legendaryserver.CustomSpawnerScreen");


    // Vanilla path for 1.21.1 (XP bar moved to custom hud folder)
    private static final Identifier XP_BAR_TEXTURE =
            Identifier.of(LegendaryServerMod.MOD_ID, "textures/gui/custom_spawner/experience_bar_progress.png");


    public CustomSpawnerScreen(CustomSpawnerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }



    @Override
    protected void init() {
        super.init();

        this.backgroundWidth = 256;
        this.backgroundHeight = 222;

        this.x = (this.width - this.backgroundWidth) / 2;
        this.y = (this.height - this.backgroundHeight) / 2;

        this.titleX = 10;
        this.playerInventoryTitleY = this.backgroundHeight - 96 + 5;


        // Client requests server to run the button action via the client's interactionManager
        // Client requests server to run the button action via the client's interactionManager
        addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("Release XP"),
                        button -> {
                            if (client != null) {
                                LOGGER.info(() -> "Release XP button clicked (client). Sending clickButton for syncId=" + handler.syncId);
                                client.interactionManager.clickButton(handler.syncId, 0);
                            }
                        }
                ).dimensions(x + 180, y + 60, 70, 20).build()
        );




    }





    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        context.drawTexture(TEXTURE, x, y, 0, 0, 256, 222, 256, 222);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw world background
        renderBackground(context, mouseX, mouseY, delta);

        // Let Minecraft handle background + slots in correct order
        super.render(context, mouseX, mouseY, delta);

        // Draw stack count text
        drawStackCount(context);

        //drawXpText(context);

        drawXpBar(context);

        // Tooltips
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void drawStackCount(DrawContext context) {
        int count = handler.getStackCount();

        String text = "Stacks: " + count;

        int textX = x + 60;
        int textY = y + 6;

        context.drawText(textRenderer, text, textX, textY, 0xFFFFFF, false);
    }

    //private void drawXpText(DrawContext context) {
       // long xp = handler.getXpRaw();

      //  int textX = x + 111;
       // int textY = y + 6;

     //   context.drawText(textRenderer, "XP: " + xp, textX, textY, 0x55FF55, false);
    //}


    private void drawXpBar(DrawContext context) {
        float fill = handler.getXpPercent();


        final int fullWidth = 160;
        final int barHeight = 4;
        final int barX = x + 8;
        final int barY = y + 15;

        int filledWidth = Math.round(fullWidth * fill);

        // Draw empty bar (from GUI background — already drawn)

        // Draw filled bar (your custom 160×4 texture)
        if (filledWidth > 0) {
            context.drawTexture(
                    XP_BAR_TEXTURE,
                    barX, barY,
                    0, 0,                 // <-- v = 0, NOT barHeight
                    filledWidth, barHeight,
                    fullWidth, barHeight  // <-- texture is only 4px tall
            );
        }
    }





}



