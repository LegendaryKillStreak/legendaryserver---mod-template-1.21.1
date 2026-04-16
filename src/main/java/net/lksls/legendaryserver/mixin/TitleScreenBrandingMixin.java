package net.lksls.legendaryserver.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(TitleScreen.class)
public abstract class TitleScreenBrandingMixin { }

 //   private static final Identifier CUSTOM_SUBTITLE =
 //           Identifier.of("lksls", "textures/gui/custom_subtitle.png");

 //   @Inject(method = "render", at = @At("TAIL"))
  //  private void lksls$drawSubtitle(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {

       // TitleScreen self = (TitleScreen)(Object)this;

     //   int width = 256;   // your PNG width
     //   int height = 32;   // your PNG height

      //  int x = self.width / 2 - width / 2;
      //  int y = 58; // move up until it sits exactly where you want

      //  context.drawTexture(
        //        CUSTOM_SUBTITLE,
          //      x, y,
            //    0f, 0f,
              //  width, height,
                //width, height
    //    );
  //  }
//}
