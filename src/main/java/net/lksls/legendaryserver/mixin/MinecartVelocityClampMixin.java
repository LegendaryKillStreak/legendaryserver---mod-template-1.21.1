package net.lksls.legendaryserver.mixin;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMinecartEntity.class)
public class MinecartVelocityClampMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void clampVelocity(CallbackInfo ci) {
        AbstractMinecartEntity self = (AbstractMinecartEntity)(Object)this;

        double max = 2.0; // safe max speed
        var v = self.getVelocity();
        double speedSq = v.lengthSquared();

        if (speedSq > max * max) {
            double scale = max / Math.sqrt(speedSq);
            self.setVelocity(v.x * scale, v.y * scale, v.z * scale);
        }
    }
}
