package net.lksls.legendaryserver.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityPositionClampMixin {

    private static final double MAX_SAFE_COORD = 30_000_000; // World border limit

    @Inject(method = "tick", at = @At("HEAD"))
    private void clampPositionBeforeTick(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        World world = self.getWorld();

        // Only clamp on the server
        if (world.isClient()) return;

        double x = self.getX();
        double y = self.getY();
        double z = self.getZ();

        boolean changed = false;

        if (Math.abs(x) > MAX_SAFE_COORD) {
            x = Math.signum(x) * MAX_SAFE_COORD;
            changed = true;
        }
        if (Math.abs(z) > MAX_SAFE_COORD) {
            z = Math.signum(z) * MAX_SAFE_COORD;
            changed = true;
        }

        // Y rarely overflows, but clamp anyway
        if (y > 2048) {
            y = 2048;
            changed = true;
        } else if (y < -2048) {
            y = -2048;
            changed = true;
        }

        if (changed) {
            self.setPosition(x, y, z);
        }
    }
}
