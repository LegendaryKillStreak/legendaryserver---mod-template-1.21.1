package net.lksls.legendaryserver;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.lksls.legendaryserver.block.ModBlocks;
import net.lksls.legendaryserver.block.entity.ModBlockEntities;
import net.lksls.legendaryserver.item.ModItems;
import net.lksls.legendaryserver.rarity.ModRarity;
import net.lksls.legendaryserver.rarity.RarityManager;
import net.lksls.legendaryserver.rarity.RarityTooltipData;
import net.lksls.legendaryserver.rarity.RarityTooltipRenderer;
import net.lksls.legendaryserver.screen.ModScreenHandlers;
import net.lksls.legendaryserver.screen.custom.CoreFusionerScreen;
import net.lksls.legendaryserver.screen.custom.CustomSpawnerScreen;
import net.lksls.legendaryserver.screen.custom.RefineryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.RenderLayer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
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




        //ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
        //    ModRarity rarity = RarityManager.getRarityForItem(stack.getItem());
        //    if (rarity == null) return;


        //});
















        HandledScreens.register(ModScreenHandlers.REFINERY_SCREEN_HANDLER, RefineryScreen::new);

        HandledScreens.register(ModScreenHandlers.CORE_FUSIONER_SCREEN_HANDLER, CoreFusionerScreen::new);

        HandledScreens.register(ModScreenHandlers.CUSTOM_SPAWNER_SCREEN_HANDLER, CustomSpawnerScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CUSTOM_SPAWNER_BLOCK, RenderLayer.getCutout());
        EntityRendererRegistry.register(
                ModBlockEntities.CHEMICAL_TNT_ENTITY,
                PrimedTntEntityRenderer::new        );






    }



}
