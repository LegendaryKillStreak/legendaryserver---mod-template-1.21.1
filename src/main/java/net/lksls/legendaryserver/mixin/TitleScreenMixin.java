package net.lksls.legendaryserver.mixin;

import net.lksls.legendaryserver.ui.ModMusicState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.URI;
import java.util.Random;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    private RotatingCubeMapRenderer customRenderer = null;

    private static final String[] SETS = {
            "set1","set2","set3","set4","set5","set6","set7","set8",
            "set9","set10","set11","set12","set13","set14","set15","set16",
            "set17","set18","set19","set20","set21","set22","set23"
    };

    @Inject(method = "renderPanoramaBackground", at = @At("HEAD"), cancellable = true)
    private void replacePanorama(DrawContext context, float delta, CallbackInfo ci) {

        if (customRenderer == null) {
            String chosen = SETS[new Random().nextInt(SETS.length)];

            Identifier id = Identifier.of(
                    "lksls",
                    "textures/panoramas/" + chosen + "/panorama"
            );

            CubeMapRenderer cube = new CubeMapRenderer(id);
            customRenderer = new RotatingCubeMapRenderer(cube);
        }

        TitleScreen self = (TitleScreen)(Object)this;

        customRenderer.render(
                context,
                self.width,
                self.height,
                1.0f,
                delta
        );

        ci.cancel();
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderDynamicCredits(DrawContext ctx, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.getWindow() == null || client.textRenderer == null) return;

            String id = ModMusicState.CURRENT_SONG_ID;
            if (id == null) return;

            String text = switch (id) {
                case "aria_math_remix_by_cleur" ->
                        "Aria Math Cinematic Remix by Cleur";
                case "living_mice_epic_hybrid_orchestral_remix_by_angerer_on_youtube" ->
                        "Living Mice Epic Hybrid Remix by Angerer";
                case "sweden_scary_remix_by_mediamotifs_on_youtube" ->
                        "Sweden Scary Remix by MediaMotifs";
                default -> null;
            };

            if (text == null) return;

            int x = 5;
            int y = client.getWindow().getScaledHeight() - 20; // <- use -20 consistently

            ctx.drawText(client.textRenderer, text, x, y, 0xFFFFFF, false);

            int width = client.textRenderer.getWidth(text);
            boolean hovering = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 10;

            if (hovering) {
                ctx.drawText(client.textRenderer, text, x, y, 0x00A2FF, false);
            }

        } catch (Throwable ignored) {
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void lksls$clickCredits(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (button != 0) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.getWindow() == null || client.textRenderer == null) return;

        String id = ModMusicState.CURRENT_SONG_ID;
        if (id == null) return;

        String text = switch (id) {
            case "aria_math_remix_by_cleur" ->
                    "Aria Math Cinematic Remix by Cléur";
            case "living_mice_epic_hybrid_orchestral_remix_by_angerer_on_youtube" ->
                    "Living Mice Epic Hybrid Remix by Angerer";
            case "sweden_scary_remix_by_mediamotifs_on_youtube" ->
                    "Sweden Scary Remix by MediaMotifs";
            default -> null;
        };

        String url = switch (id) {
            case "aria_math_remix_by_cleur" ->
                    "https://youtube.com/@chroniccleur";
            case "living_mice_epic_hybrid_orchestral_remix_by_angerer_on_youtube" ->
                    "https://youtube.com/@I_am_Angerer";
            case "sweden_scary_remix_by_mediamotifs_on_youtube" ->
                    "https://youtube.com/@MediaMotifs";
            default -> null;
        };

        if (text == null || url == null) return;

        int x = 5;
        int y = client.getWindow().getScaledHeight() - 20;
        int width = client.textRenderer.getWidth(text);

        boolean hovering = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 10;
        if (!hovering) return;

        try {
            Util.getOperatingSystem().open(new URI(url));
        } catch (Exception ignored) {}

        cir.setReturnValue(true);
    }




}
