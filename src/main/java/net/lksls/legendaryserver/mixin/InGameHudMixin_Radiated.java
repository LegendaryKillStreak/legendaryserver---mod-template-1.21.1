package net.lksls.legendaryserver.mixin;

import net.lksls.legendaryserver.effect.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin_Radiated {

    @Shadow @Final private MinecraftClient client;

    private static final Identifier CONTAINER =
            Identifier.of("minecraft", "hud/heart/container");
    private static final Identifier CONTAINER_BLINK =
            Identifier.of("minecraft", "hud/heart/container_blinking");

    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void renderRadiatedHearts(
            DrawContext context,
            PlayerEntity player,
            int x, int y,
            int lines,
            int regenIndex,
            float maxHealth,
            int lastHealth,
            int health,
            int absorption,
            boolean blinking,
            CallbackInfo ci
    ) {
        if (!player.hasStatusEffect(ModEffects.RADIATION)) {
            return;
        }

        ci.cancel();
        drawRadiatedHearts(context, player, x, y, blinking, regenIndex);
    }

    private void drawRadiatedHearts(
            DrawContext context,
            PlayerEntity player,
            int x, int y,
            boolean blinking,
            int regenIndex
    ) {
        Identifier full      = Identifier.of("lksls", "textures/gui/hearts/radiated_full.png");
        Identifier half      = Identifier.of("lksls", "textures/gui/hearts/radiated_half.png");
        Identifier fullBlink = Identifier.of("lksls", "textures/gui/hearts/radiated_full_blinking.png");
        Identifier halfBlink = Identifier.of("lksls", "textures/gui/hearts/radiated_half_blinking.png");

        float hp = player.getHealth();
        int fullHearts = (int)(hp / 2f);
        boolean hasHalf = (hp % 2f) >= 1f;

        for (int i = 0; i < 10; i++) {
            int px = x + i * 8;

            boolean isRegenHeart = (i == regenIndex);
            boolean isDamageBlink = blinking; // damage flash applies to ALL slots

            // 1) Container blinking logic (regen OR damage)
            boolean containerShouldBlink = isRegenHeart || isDamageBlink;

            context.drawGuiTexture(
                    containerShouldBlink ? CONTAINER_BLINK : CONTAINER,
                    px, y,
                    9, 9
            );

            // 2) Foreground blinking logic (damage OR regen)
            boolean heartShouldBlink = isRegenHeart || isDamageBlink;

            // Full heart
            if (i < fullHearts) {
                context.drawTexture(
                        heartShouldBlink ? fullBlink : full,
                        px, y,
                        0, 0,
                        9, 9,
                        9, 9
                );
            }
            // Half heart
            else if (i == fullHearts && hasHalf) {
                context.drawTexture(
                        heartShouldBlink ? halfBlink : half,
                        px, y,
                        0, 0,
                        9, 9,
                        9, 9
                );
            }
        }
    }
}



