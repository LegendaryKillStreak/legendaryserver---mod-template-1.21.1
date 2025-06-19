package net.lks.legendaryserver.sound;

import net.lks.legendaryserver.LegendaryServerMod;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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



    public static final SoundEvent DAVY_JONES_THEME = registerSoundEvent("davy_jones_theme");
    public static final RegistryKey<JukeboxSong> DAVY_JONES_THEME_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(LegendaryServerMod.MOD_ID, "davy_jones_theme"));

    public static final SoundEvent TEARS = registerSoundEvent("tears");
    public static final RegistryKey<JukeboxSong> TEARS_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(LegendaryServerMod.MOD_ID, "tears"));

    public static final SoundEvent PAIN_RESONANCE = registerSoundEvent("pain_resonance");
    public static final RegistryKey<JukeboxSong> PAIN_RESONANCE_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(LegendaryServerMod.MOD_ID, "pain_resonance"));
}
