package net.lksls.legendaryserver.block.entity.custom;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ChemicalTntEntity extends TntEntity {

    public ChemicalTntEntity(EntityType<? extends TntEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient && this.getFuse() <= 0) {
            explodeChemical();
            this.discard();
        }
    }


    private void explodeChemical() {
        World world = this.getWorld();

        // Bigger explosion
        world.createExplosion(
                this,
                this.getX(),
                this.getY(),
                this.getZ(),
                6.0f, // stronger than normal TNT
                World.ExplosionSourceType.TNT
        );

        // Chemical burn radius
        double radius = 8.0;

        for (LivingEntity entity : world.getEntitiesByClass(
                LivingEntity.class,
                new Box(
                        this.getX() - radius, this.getY() - radius, this.getZ() - radius,
                        this.getX() + radius, this.getY() + radius, this.getZ() + radius
                ),
                e -> true
        )) {
            entity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.POISON, // swap later for custom "chemical burn"
                    200,                  // 10 seconds
                    1                     // level 2
            ));
        }
    }
}
