package net.lksls.legendaryserver.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    private RotatingCubeMapRenderer customRenderer = null;

    private static final String[] SETS = {
                "set1",
                "set2",
                "set3",
                "set4",
                "set5",
                "set6",
                "set7",
                "set8",
                "set9",
                "set10",
                "set11",
                "set12",
                "set13",
                "set14",
                "set15",
                "set16",
                "set17",
                "set18",
                "set19",
                "set20",
                "set21",
                "set22",
                "set23"
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
}
