package net.lksls.legendaryserver.ui;

import java.util.Map;

public class MusicCredits {
    public static final Map<String, CreditInfo> CREDITS = Map.of(
            "aria_math_remix_by_cleur", new CreditInfo(
                    "Aria Math Cinematic Remix by Cleur",
                    "https://youtube.com/@Cleur"
            ),
            "living_mice_epic_hybrid_orchestral_remix_by_angerer_on_youtube", new CreditInfo(
                    "Living Mice Epic Hybrid Remix by Angerer",
                    "https://youtube.com/@Angerer"
            ),
            "sweden_scary_remix_by_mediamotifs_on_youtube", new CreditInfo(
                    "Sweden Scary Remix by MediaMotifs",
                    "https://youtube.com/@MediaMotifs"
            )
    );

    public record CreditInfo(String text, String url) {}
}
