package net.lksls.legendaryserver.item.custom;

import net.lksls.legendaryserver.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class GammaRadiatingXenthrite extends Item {
    // The constructor must accept an Item.Settings object
    public GammaRadiatingXenthrite(Item.Settings settings) { // <--- CORRECTED PARAMETER TYPE
        super(settings); // Pass the Item.Settings object to the parent constructor
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()) {
            return;
        }

        if (entity instanceof ServerPlayerEntity player) {
            // Get the current radiation effect instance on the player, if any
            StatusEffectInstance currentRadiation = player.getStatusEffect(ModEffects.RADIATION);

            // Define the desired parameters for the radiation effect
            int desiredDuration = 8000; // Your intended duration (6 minutes 40 seconds)
            int desiredAmplifier = 4;   // Level V

            // Only apply or reapply the effect if:
            // 1. The player DOES NOT have the radiation effect currently.
            // 2. The player has the effect, BUT its amplifier is lower than desired.
            // 3. The player has the effect with the desired amplifier, BUT its remaining duration is too short.
            //    (e.g., less than 60 ticks, which is 3 seconds, to prevent constant re-application)
            int minimumDurationToReapply = 6000; // Reapply if less than 3 seconds remain on the current effect

            if (currentRadiation == null ||
                    currentRadiation.getAmplifier() < desiredAmplifier ||
                    currentRadiation.getDuration() < minimumDurationToReapply)
            {
                StatusEffectInstance newRadiationEffect = new StatusEffectInstance(
                        ModEffects.RADIATION,
                        desiredDuration,
                        desiredAmplifier,
                        true,     // Ambient
                        false,    // Show Particles
                        true      // Show Icon
                );

                player.addStatusEffect(newRadiationEffect);

                // Optional: Log only when the effect is actually applied/refreshed
                // LegendaryServerMod.LOGGER.debug("Applied/Refreshed Radiation on " + player.getName().getString() + " to Level " + (desiredAmplifier + 1));
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected); // Call super method
    }
}

