package net.lksls.legendaryserver.mixin;
import net.minecraft.entity.projectile.ArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public class ArrowVelocityClampMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void clampArrowVelocity(CallbackInfo ci) {
        ArrowEntity self = (ArrowEntity)(Object)this;

        double max = 100.0; //safe railgun speed, trial and error
        var v = self.getVelocity();
        double speedSq = v.lengthSquared();

        if (speedSq > max * max) {
            double scale = max / Math.sqrt(speedSq);
            self.setVelocity(v.x * scale, v.y * scale, v.z * scale);
        }
    }
}
