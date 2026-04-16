package net.lksls.legendaryserver.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class MixinServerPlayerEntity {

    @Overwrite
    public BlockPos getWorldSpawnPos(ServerWorld world, BlockPos basePos) {
        int radius = 256;
        Random random = world.getRandom();

        // -------------------------------
        // RING DISTRIBUTION (Option B)
        // -------------------------------
        double angle = random.nextDouble() * Math.PI * 2;
        double distance = 50 + random.nextDouble() * (radius - 50);

        int x = basePos.getX() + (int)(Math.cos(angle) * distance);
        int z = basePos.getZ() + (int)(Math.sin(angle) * distance);

        // -------------------------------
        // PHASE 1: Prefer Y = 70 → 40
        // -------------------------------
        for (int y = 70; y >= 40; y--) {
            BlockPos pos = new BlockPos(x, y, z);

            if (world.isAir(pos) &&
                    world.getBlockState(pos.down()).isSolidBlock(world, pos.down())) {
                return pos;
            }
        }

        // -------------------------------
        // PHASE 2: Fallback to full downward scan
        // -------------------------------
        BlockPos.Mutable pos = new BlockPos.Mutable(x, world.getTopY(), z);

        while (pos.getY() > world.getBottomY()) {
            if (world.isAir(pos) &&
                    world.getBlockState(pos.down()).isSolidBlock(world, pos.down())) {
                return pos.toImmutable();
            }
            pos.move(0, -1, 0);
        }

        // Fallback to vanilla spawn if nothing found
        return basePos;
    }
}

