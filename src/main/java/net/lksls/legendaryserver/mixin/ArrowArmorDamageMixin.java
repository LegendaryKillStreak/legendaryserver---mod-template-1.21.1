package net.lksls.legendaryserver.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ArrowArmorDamageMixin {

    @Inject(method = "damage", at = @At("HEAD"))
    private void onArrowDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getSource() instanceof ArrowEntity arrow)) return;

        double speed = arrow.getVelocity().length();

        // Only apply armor damage if arrow is railgun-boosted
        if (speed < 20.0) return;

        // Scale armor damage based on velocity above 15
        double excess = speed - 15.0;
        int extraDurabilityLoss = (int)(excess * 12); // tweak multiplier here

        LivingEntity self = (LivingEntity)(Object)this;

        for (ItemStack armor : self.getArmorItems()) {
            if (!armor.isEmpty()) {
                armor.damage(extraDurabilityLoss, self, e -> {});
            }
        }
    }
}
