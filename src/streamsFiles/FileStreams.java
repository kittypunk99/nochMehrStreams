package streamsFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileStreams {
    private static final Map<Character, String> TEXT_TO_MORSE = new HashMap<>();
    private static final Map<String, Character> MORSE_TO_TEXT = new HashMap<>();

    static {
        String[] morse = {"",".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".-.-", "---.", "..--", ".--.-", "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};
        char[] text = " abcdefghijklmnopqrstuvwxyzäöüß0123456789".toCharArray();

        for (int i = 0; i < text.length; i++) {
            TEXT_TO_MORSE.put(text[i], morse[i]);
            MORSE_TO_TEXT.put(morse[i], text[i]);
        }
    }

    public static List<Path> biggestFilesA(Path dir, int n) throws IOException {
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Not a directory: " + dir);
        }
        if (n < 0) {
            throw new IllegalArgumentException("Invalid n: " + n);
        }
        try (var stream = Files.list(dir)) {
            return stream.filter(Files::isRegularFile).sorted(Comparator.comparingLong(p -> -p.toFile().length())).limit(n).collect(Collectors.toList());
        }
    }

    public static List<Path> biggestFilesB(Path dir, int n) throws IOException {
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Not a directory: " + dir);
        }
        if (n < 0) {
            throw new IllegalArgumentException("Invalid n: " + n);
        }
        try (Stream<Path> stream = Files.walk(dir)) {
            return stream.filter(Files::isRegularFile).sorted(Comparator.comparingLong(p -> -p.toFile().length())).limit(n).collect(Collectors.toList());
        }
    }

    public static List<Path> olderThan(Path dir, Path reference) throws IOException {
        BasicFileAttributes refAttrs = Files.readAttributes(reference, BasicFileAttributes.class);
        long refTime = refAttrs.creationTime().toMillis();

        try (var stream = Files.list(dir)) {
            if (Files.isDirectory(reference)) {
                return stream.filter(Files::isDirectory).filter(p -> {
                    try {
                        return Files.readAttributes(p, BasicFileAttributes.class).creationTime().toMillis() < refTime;
                    } catch (IOException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            } else {
                return stream.filter(Files::isRegularFile).filter(p -> {
                    try {
                        return Files.readAttributes(p, BasicFileAttributes.class).creationTime().toMillis() < refTime;
                    } catch (IOException e) {
                        return false;
                    }
                }).collect(Collectors.toList());
            }
        }
    }

    public static String toMorseCode(String text) {
        StringBuilder morseCode = new StringBuilder();
        for (char c : text.toLowerCase().toCharArray()) {
            if (TEXT_TO_MORSE.containsKey(c)) {
                morseCode.append('/').append(TEXT_TO_MORSE.get(c));
            }
        }
        return morseCode.toString();
    }

    public static String morseToText(String morse) {
        StringBuilder text = new StringBuilder();
        String[] morseChars = morse.split("/");
        for (String morseChar : morseChars) {
            if (MORSE_TO_TEXT.containsKey(morseChar)) {
                text.append(MORSE_TO_TEXT.get(morseChar));
            }
        }
        return text.toString();
    }
}
