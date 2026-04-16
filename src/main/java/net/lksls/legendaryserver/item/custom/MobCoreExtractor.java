package net.lksls.legendaryserver.item.custom;

import net.lksls.legendaryserver.item.ModItems;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

// Client‑side charging animation
public class MobCoreExtractor extends SwordItem {

    private static final int COOLDOWN_TICKS = 40; // 2 seconds

    public MobCoreExtractor(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    // RIGHT‑CLICK extraction
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient()) {
            tryExtract(stack, user, entity);
        } else {
            playChargeAnimation(user);
        }
        return ActionResult.SUCCESS;
    }

    // LEFT‑CLICK extraction
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        // SAFETY: Players sollen NICHT betroffen sein
        if (target instanceof PlayerEntity) {
            return super.postHit(stack, target, attacker);
        }

        if (!attacker.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            tryExtract(stack, player, target);
        } else if (attacker.getWorld().isClient()) {
            playChargeAnimation(attacker);
        }

        return super.postHit(stack, target, attacker);
    }


    // Shared extraction logic
    private void tryExtract(ItemStack stack, PlayerEntity user, LivingEntity entity) {

        // Cooldown check
        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return;
        }

        // Apply cooldown
        user.getItemCooldownManager().set(this, COOLDOWN_TICKS);

        // SAFETY FIX: Never damage players
        if (entity instanceof PlayerEntity) {
            return;
        }

        // Determine core drop
        Item core = getCoreForEntity(entity);

        // Safe instant kill for mobs
        entity.damage(user.getDamageSources().magic(), 1000f);

        // Drop core if applicable
        if (core != null) {
            World world = entity.getWorld();
            world.spawnEntity(new ItemEntity(
                    world,
                    entity.getX(),
                    entity.getY() + 0.5,
                    entity.getZ(),
                    new ItemStack(core)
            ));
        }

        // Particles
        spawnExtractionParticles(entity);

        // Sound
        entity.getWorld().playSound(
                null,
                entity.getBlockPos(),
                SoundEvents.ENTITY_ENDERMAN_SCREAM,
                SoundCategory.PLAYERS,
                1.0f,
                1.2f
        );

        // Break extractor after one use
        stack.decrement(1);
    }

    private void spawnExtractionParticles(LivingEntity entity) {
        World world = entity.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 20; i++) {
                serverWorld.spawnParticles(
                        ParticleTypes.SOUL_FIRE_FLAME,
                        entity.getX(),
                        entity.getY() + entity.getHeight() / 2,
                        entity.getZ(),
                        1,
                        (world.random.nextDouble() - 0.5) * 0.3,
                        (world.random.nextDouble()) * 0.3,
                        (world.random.nextDouble() - 0.5) * 0.3,
                        0.01
                );
                serverWorld.spawnParticles(
                        ParticleTypes.SOUL,
                        entity.getX(),
                        entity.getY() + entity.getHeight() / 2,
                        entity.getZ(),
                        1,
                        (world.random.nextDouble() - 0.5) * 0.3,
                        (world.random.nextDouble()) * 0.3,
                        (world.random.nextDouble() - 0.5) * 0.3,
                        0.01
                );
            }
            serverWorld.spawnParticles(
                    ParticleTypes.SCULK_SOUL,
                    entity.getX(),
                    entity.getY() + entity.getHeight() / 2,
                    entity.getZ(),
                    1,
                    (world.random.nextDouble() - 0.5) * 0.3,
                    (world.random.nextDouble()) * 0.3,
                    (world.random.nextDouble() - 0.5) * 0.3,
                    0.01
            );
        }
    }

    // Client‑side charging animation
    private void playChargeAnimation(LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            player.swingHand(Hand.MAIN_HAND);
        }
    }

    private Item getCoreForEntity(LivingEntity entity) {
        if (entity instanceof CreeperEntity) return ModItems.CREEPER_CORE;
        if (entity instanceof EndermanEntity) return ModItems.ENDERMAN_CORE;
        if (entity instanceof SkeletonEntity) return ModItems.SKELETON_CORE;
        if (entity instanceof WitherSkeletonEntity) return ModItems.WITHER_SKELETON_CORE;
        if (entity instanceof ChickenEntity) return ModItems.CHICKEN_SPAWN_CORE;
        if (entity instanceof CowEntity) return ModItems.COW_CORE;
        return null;
    }
}
