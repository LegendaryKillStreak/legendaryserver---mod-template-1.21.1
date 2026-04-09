package net.lksls.legendaryserver;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.screen.ModScreenHandlers;
import net.lksls.legendaryserver.screen.custom.CustomSpawnerScreen;
import net.lksls.legendaryserver.screen.custom.RefineryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;



public class LkslsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MIDNIGHTWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LOST_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LOST_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ENDLESS_VIOLET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ENDLESS_VIOLET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAMENT_SPINDLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LAMENT_SPINDLE, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.REFINERY_SCREEN_HANDLER, RefineryScreen::new);





        HandledScreens.register(ModScreenHandlers.CUSTOM_SPAWNER_SCREEN_HANDLER, CustomSpawnerScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CUSTOM_SPAWNER_BLOCK, RenderLayer.getCutout());






    }



}
