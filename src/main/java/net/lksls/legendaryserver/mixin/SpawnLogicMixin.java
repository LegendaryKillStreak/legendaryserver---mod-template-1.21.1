package net.lksls.legendaryserver.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class SpawnLogicMixin {

    @Inject(method = "findRespawnPosition", at = @At("HEAD"), cancellable = true)
    private static void onFindRespawn(ServerWorld world, BlockPos pos, float angle, boolean forced, boolean alive, CallbackInfoReturnable<BlockPos> cir) {

        BlockPos worldSpawn = world.getSpawnPos();
        BlockPos best = null;
        double bestScore = -999999;

        int radius = 32;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = 5; y < world.getTopY(); y++) {

                    BlockPos check = new BlockPos(worldSpawn.getX() + x, y, worldSpawn.getZ() + z);

                    if (!isSafe(world, check)) continue;

                    double score = scorePos(check);

                    if (score > bestScore) {
                        bestScore = score;
                        best = check;
                    }
                }
            }
        }

        if (best != null) {
            cir.setReturnValue(best);
        }
    }

    private static boolean isSafe(World world, BlockPos pos) {
        BlockState feet = world.getBlockState(pos);
        BlockState head = world.getBlockState(pos.up());
        BlockState below = world.getBlockState(pos.down());

        // Kein Wasser/Lava
        if (feet.isOf(Blocks.WATER) || feet.isOf(Blocks.LAVA)) return false;
        if (below.isOf(Blocks.WATER) || below.isOf(Blocks.LAVA)) return false;

        // Kopf frei
        if (!head.getCollisionShape(world, pos.up()).isEmpty()) return false;

        // Muss auf solidem Block stehen
        if (!below.isSolidBlock(world, pos.down())) return false;

        return true;
    }

    private static double scorePos(BlockPos pos) {
        int y = pos.getY();

        // Y-Level 40–70 bevorzugen
        if (y >= 40 && y <= 70) {
            return 1000 - Math.abs(55 - y); // Peak bei 55
        }

        return 10;
    }
} //falls ich das schaffe bin ich ein absuluter G
