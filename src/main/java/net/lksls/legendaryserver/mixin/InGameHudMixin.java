package net.lksls.legendaryserver.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lksls.legendaryserver.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.u;
import static com.ibm.icu.text.PluralRules.Operand.v;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {



    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At("TAIL"))
    private void renderCustomOverlays(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        PlayerEntity player = client.player;
        if (player == null) return;

        if (player.hasStatusEffect(ModEffects.RADIATION)) {
            Identifier overlay = Identifier.of("lksls", "textures/misc/radiated.png");

            int w = client.getWindow().getScaledWidth();
            int h = client.getWindow().getScaledHeight();

            // blending and color settings, if i forget alpha btw, it all looks poopy ah bullshit. DO NEVER FORGET ALPHA CHANNEL
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            context.setShaderColor(1f, 1f, 1f, 1f);

            // Texture Size
            context.drawTexture(
                    overlay,
                    0, 0,                 // screen position
                    w, h,                 // draw size on screen
                    0, 0,                 // UV start
                    256, 256,             // UV region size
                    256, 256              // actual texture size
            );



            RenderSystem.disableBlend();
        }
    }
}

