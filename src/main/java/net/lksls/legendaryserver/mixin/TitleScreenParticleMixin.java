package net.lksls.legendaryserver.mixin;

import net.lksls.legendaryserver.ui.UiParticle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Mixin(TitleScreen.class)
public class TitleScreenParticleMixin {

    @Unique private static final Random R = new Random();
    @Unique private static final List<UiParticle> P = new ArrayList<>();

    @Inject(method = "render", at = @At("HEAD"))
    private void renderParticles(DrawContext ctx, int mouseX, int mouseY, float delta, CallbackInfo ci) {

        MinecraftClient c = MinecraftClient.getInstance();
        int w = c.getWindow().getScaledWidth();
        int h = c.getWindow().getScaledHeight();

        // ---------------------------------------------------------
        // FIREFLIES (cyan, tiny, fast)
        // ---------------------------------------------------------
        if (R.nextFloat() < 0.15f) {
            int color = 0xAA66CCFF; // cyan
            P.add(new UiParticle(
                    R.nextFloat() * w,
                    R.nextFloat() * h,
                    (R.nextFloat() - 0.5f) * 1.0f,
                    (R.nextFloat() - 0.5f) * 1.0f,
                    200 + R.nextInt(200),
                    0,
                    1,
                    color
            ));
        }

        // ---------------------------------------------------------
        // CIRCLE AROUND MOUSE (always works)
        // ---------------------------------------------------------
        int ringCount = 0;     // number of particles in ring
        float radius = 4f;     // ring radius

        for (int i = 0; i < ringCount; i++) {
            double angle = (2 * Math.PI * i) / ringCount;

            float px = mouseX + (float)Math.cos(angle) * radius;
            float py = mouseY + (float)Math.sin(angle) * radius;

            int color = 0xAA66CCFF; // cyan

            P.add(new UiParticle(
                    px,
                    py,
                    (R.nextFloat() - 0.5f) * 0.3f,
                    (R.nextFloat() - 0.5f) * 0.3f,
                    20 + R.nextInt(20),
                    1,
                    2,
                    color
            ));
        }

        // ---------------------------------------------------------
        // UPDATE + DRAW
        // ---------------------------------------------------------
        Iterator<UiParticle> it = P.iterator();
        while (it.hasNext()) {
            UiParticle p = it.next();

            // simple drift
            p.x += p.vx;
            p.y += p.vy;

            p.life--;
            if (p.life <= 0) {
                it.remove();
                continue;
            }

            ctx.fill(
                    (int)p.x,
                    (int)p.y,
                    (int)p.x + p.size,
                    (int)p.y + p.size,
                    p.color
            );
        }
    }
}
