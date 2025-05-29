package net.lks.legendaryserver.sound;

import net.lks.legendaryserver.LegendaryServerMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(LegendaryServerMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    public static void registerSounds() {
        LegendaryServerMod.LOGGER.info("Registering Mod Sounds for "+ LegendaryServerMod.MOD_ID);
    }
}
