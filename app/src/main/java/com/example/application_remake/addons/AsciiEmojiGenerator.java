package com.example.application_remake.addons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsciiEmojiGenerator {
    private static final List<String> asciiEmojis = new ArrayList<>();
    private static final Random random = new Random();

    // Inicjalizacja listy emotek w bloku statycznym
    static {
        asciiEmojis.add("(＾▽＾)");      // Happy face
        asciiEmojis.add("(╯°□°)╯︵ ┻━┻"); // Table flip
        asciiEmojis.add("(⌐■_■)");      // Cool sunglasses
        asciiEmojis.add("(╥﹏╥)");      // Crying face
        asciiEmojis.add("(✿◕‿◕)");      // Cute smile
        asciiEmojis.add("¯\\_(ツ)_/¯");   // Shrug
        asciiEmojis.add("(ノಠ益ಠ)ノ彡┻━┻"); // Angry table flip
        asciiEmojis.add("ʕ•ᴥ•ʔ");       // Bear face
        asciiEmojis.add("ಠ_ಠ");         // Disapproval
        asciiEmojis.add("(♥‿♥)");       // Love eyes
    }

    // Publiczna metoda do losowego wyboru emotki
    public static String getRandomEmoji() {
        int randomIndex = random.nextInt(asciiEmojis.size());
        return asciiEmojis.get(randomIndex);
    }

    // Publiczna metoda do uzyskania pełnej listy emotek
    public static List<String> getAllEmojis() {
        return new ArrayList<>(asciiEmojis); // Zwraca kopię listy, aby uniknąć jej modyfikacji
    }
}
