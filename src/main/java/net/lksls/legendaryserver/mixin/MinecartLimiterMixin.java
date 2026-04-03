package net.lksls.legendaryserver.mixin;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;
import java.util.List;

@Mixin(AbstractMinecartEntity.class)
public class MinecartLimiterMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        AbstractMinecartEntity self = (AbstractMinecartEntity)(Object)this;
        World world = self.getWorld();

        if (world.isClient()) return;

        // Check nearby minecarts
        Box box = self.getBoundingBox().expand(1, 0.75, 1);
        List<AbstractMinecartEntity> nearby = world.getEntitiesByClass(
                AbstractMinecartEntity.class,
                box,
                e -> true
        );

        // Limit to 6 minecarts of ANY type
        if (nearby.size() > 6) {

            // Remove the newest minecart (highest entity ID)
            AbstractMinecartEntity newest = nearby.stream()
                    .max(Comparator.comparingInt(AbstractMinecartEntity::getId))
                    .orElse(null);

            if (newest != null) {
                // Drop correct minecart item
                ItemStack drop = newest.getPickBlockStack();
                if (!drop.isEmpty()) {
                    newest.dropStack(drop);
                }

                newest.discard();
            }
        }
    }
}
