package net.lksls.legendaryserver.mixin;

import net.minecraft.sound.MusicSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MusicType.class)
public class MusicTypeMixin {

    @Shadow @Mutable
    public static MusicSound MENU;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void replaceMenuMusic(CallbackInfo ci) {

        Random random = new Random();

        // Pick a random track: 0 = Rubedo, 1 = Nether Wastes, 2 = End
        int choice = random.nextInt(3);

        MusicSound selected;

        switch (choice) {
            case 0 -> selected = new MusicSound(
                    SoundEvents.MUSIC_NETHER_BASALT_DELTAS, // Rubedo
                    20, 600, true
            );
            case 1 -> selected = new MusicSound(
                    SoundEvents.MUSIC_NETHER_NETHER_WASTES, // Nether Wastes
                    20, 600, true
            );
            default -> selected = new MusicSound(
                    SoundEvents.MUSIC_END, // End music
                    20, 600, true
            );
        }

        MENU = selected;
    }
}
