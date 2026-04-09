package net.lksls.legendaryserver.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.lksls.legendaryserver.LegendaryServerMod;
import net.lksls.legendaryserver.screen.custom.CustomSpawnerScreenHandler;
import net.lksls.legendaryserver.screen.custom.RefineryScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<RefineryScreenHandler> REFINERY_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER,
                    Identifier.of(LegendaryServerMod.MOD_ID, "refinery_screen_handler"),
                    new ExtendedScreenHandlerType<>(RefineryScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<CustomSpawnerScreenHandler> CUSTOM_SPAWNER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER,
                    Identifier.of(LegendaryServerMod.MOD_ID, "custom_spawner"),
                    new ExtendedScreenHandlerType<>(CustomSpawnerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        LegendaryServerMod.LOGGER.info("Registering Screen Handlers for " + LegendaryServerMod.MOD_ID);
    }
}
