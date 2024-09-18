package tests;

import streamsFiles.FileStreams;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileStreamsTests {
    public static void main(String[] args) {
        Path dir = Paths.get("src/streamsFiles");
        int n = 3;
        try {
            List<Path> biggestFilesA = FileStreams.biggestFilesA(dir, n);
            List<Path> biggestFilesB = FileStreams.biggestFilesB(Paths.get("src"), n);
            List<Path> olderFiles = FileStreams.olderThan(Paths.get("C:\\Users\\LinusFreistetter\\OneDrive\\Documents\\LINUS\\htl\\2\\itsi\\ue6"), Paths.get("C:\\Users\\LinusFreistetter\\OneDrive\\Documents\\LINUS\\htl\\2\\itsi\\ue6\\#_FREISTETTER_caesar.txt.out"));
            System.out.println("biggestFilesA:");
            biggestFilesA.forEach(System.out::println);
            System.out.println("biggestFilesB:");
            biggestFilesB.forEach(System.out::println);
            System.out.println("olderFiles:");
            olderFiles.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        String text = "Älle lieben Java!";
        String morse = FileStreams.toMorseCode(text);
        System.out.println("Text: " + text);
        System.out.println("Morse: " + morse);
        System.out.println("Text: " + FileStreams.morseToText(morse));
    }

}
