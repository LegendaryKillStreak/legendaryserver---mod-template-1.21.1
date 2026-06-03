package net.lksls.legendaryserver.mixin;

import net.lksls.legendaryserver.sound.ModSounds;
import net.lksls.legendaryserver.ui.ModMusicState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.Registries;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(MinecraftClient.class)
public class MusicTypeMixin {

    private static final Random R = new Random();

    // The currently chosen menu track
    private MusicSound lksls$currentMenuTrack = null;

    @Inject(method = "getMusicType", at = @At("HEAD"), cancellable = true)
    private void lksls$overrideMenuMusic(CallbackInfoReturnable<MusicSound> cir) {

        MinecraftClient client = (MinecraftClient)(Object)this;

        // If we are IN A WORLD → do NOT override music
        if (client.world != null) {
            lksls$currentMenuTrack = null;
            return;
        }

        // We are in ANY menu.
        // If Minecraft is asking for a music type AND we have no cached track,
        // that means the previous track ended → pick a new one.
        if (lksls$currentMenuTrack == null) {

            int choice = R.nextInt(7);
            MusicSound selected;
            String id;

            switch (choice) {
                case 0 -> {
                    selected = new MusicSound(SoundEvents.MUSIC_NETHER_BASALT_DELTAS, 20, 50, true);
                    id = "vanilla_rubedo";
                }
                case 1 -> {
                    selected = new MusicSound(SoundEvents.MUSIC_NETHER_NETHER_WASTES, 20, 50, true);
                    id = "vanilla_nether_wastes";
                }
                case 2 -> {
                    selected = new MusicSound(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY, 20, 50, true);
                    id = "vanilla_soul_sand";
                }
                case 3 -> {
                    selected = new MusicSound(SoundEvents.MUSIC_END, 20, 50, true);
                    id = "vanilla_end";
                }
                case 4 -> {
                    selected = new MusicSound(
                            Registries.SOUND_EVENT.getEntry(ModSounds.ARIA_MATH_CINEMATIC_REMIX_BY_CLEUR_KEY).orElseThrow(),
                            20, 50, true
                    );
                    id = "aria_math_remix_by_cleur";
                }
                case 5 -> {
                    selected = new MusicSound(
                            Registries.SOUND_EVENT.getEntry(ModSounds.LIVING_MICE_EPIC_REMIX_BY_ANGERER_KEY).orElseThrow(),
                            20, 50, true
                    );
                    id = "living_mice_epic_hybrid_orchestral_remix_by_angerer_on_youtube";
                }
                case 6 -> {
                    selected = new MusicSound(
                            Registries.SOUND_EVENT.getEntry(ModSounds.SWEDEN_REMIX_BY_MEDIAMOTIFS_KEY).orElseThrow(),
                            20, 50, true
                    );
                    id = "sweden_scary_remix_by_mediamotifs_on_youtube";
                }
                // case 7 -> {
//     selected = new MusicSound(
//             Registries.SOUND_EVENT.getEntry(ModSounds.HALLOWEEN_I_KEY).orElseThrow(),
//             20, 50, true
//     );
//     id = "halloween_i_by_gareth_coker_ownedby_mojang";
// }
// case 8 -> {
//     selected = new MusicSound(
//             Registries.SOUND_EVENT.getEntry(ModSounds.HALLOWEEN_II_KEY).orElseThrow(),
//             20, 50, true
//     );
//     id = "halloween_ii_by_gareth_coker_ownedby_mojang";
// }
// case 9 -> {
//     selected = new MusicSound(
//             Registries.SOUND_EVENT.getEntry(ModSounds.HALLOWEEN_III_KEY).orElseThrow(),
//             20, 50, true
//     );
//     id = "halloween_iii_by_gareth_coker_ownedby_mojang";
// }
// case 10 -> {
//     selected = new MusicSound(
//             Registries.SOUND_EVENT.getEntry(ModSounds.HALLOWEEN_MENU_KEY).orElseThrow(),
//             20, 50, true
//     );
//     id = "halloween_menu_by_gareth_coker_ownedby_mojang";
// }
// case 11 -> {
//     selected = new MusicSound(
//             Registries.SOUND_EVENT.getEntry(ModSounds.HALLOWEEN_THE_END_KEY).orElseThrow(),
//             20, 50, true
//     );
//     id = "halloween_the_end_by_gareth_coker_ownedby_mojang";
// }
// case 12 -> {
//     selected = new MusicSound(
//             Registries.SOUND_EVENT.getEntry(ModSounds.HALLOWEEN_NETHER_II_KEY).orElseThrow(),
//             20, 50, true
//     );
//     id = "halloween_nether_ii_by_gareth_coker_ownedby_mojang";
// }
// case 13 -> {
//     selected = new MusicSound(
//             Registries.SOUND_EVENT.getEntry(ModSounds.HALLOWEEN_NETHER_I_KEY).orElseThrow(),
//             20, 50, true
//     );
//     id = "halloween_nether_i_by_gareth_coker_ownedby_mojang";
// }

                default -> {
                    selected = new MusicSound(SoundEvents.MUSIC_END, 20, 50, true);
                    id = "vanilla_end";
                }
            }

            lksls$currentMenuTrack = selected;
            ModMusicState.CURRENT_SONG_ID = id;
        }

        // Always return the cached track until it ends
        cir.setReturnValue(lksls$currentMenuTrack);
    }
}

