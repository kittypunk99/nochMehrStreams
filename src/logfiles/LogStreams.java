package logfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class LogStreams {
    public static Map<Character, Integer> zeichenStatistik(String s) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        return charCountMap;
    }

    public static List<String> winnersOfToursLessThan3500km() {
        return Winner.tdfWinners.stream().filter(w -> w.getLengthKm() < 3500).map(Winner::getName).collect(Collectors.toList());
    }

    public static List<String> winnersOfToursGreaterThan3500km() {
        return Winner.tdfWinners.stream().filter(w -> w.getLengthKm() > 3500).map(Winner::getName).collect(Collectors.toList());
    }

    public static List<Winner> winnerObjectsOfToursLessThan3500kmLimit2() {
        return Winner.tdfWinners.stream().filter(w -> w.getLengthKm() < 3500).limit(2).collect(Collectors.toList());
    }

    public static List<String> firstTwoWinnersOfToursLessThan3500km() {
        return Winner.tdfWinners.stream().filter(w -> w.getLengthKm() < 3500).limit(2).map(Winner::getName).collect(Collectors.toList());
    }

    public static List<String> distinctTDFWinners() {
        return Winner.tdfWinners.stream().map(Winner::getName).distinct().collect(Collectors.toList());
    }

    public static long numberOfDistinctWinners() {
        return Winner.tdfWinners.stream().map(Winner::getName).distinct().count();
    }

    public static List<Winner> skipEveryOtherTDFWinner() {
        return Winner.tdfWinners.stream().skip(2).collect(Collectors.toList());
    }

    public static List<String> mapWinnerYearNamesToList() {
        return Winner.tdfWinners.stream().map(w -> w.getYear() + " - " + w.getName()).collect(Collectors.toList());
    }

    public static int wigginsWins() {
        return Winner.tdfWinners.stream().filter(w -> w.getName().equals("Bradley Wiggins")).map(Winner::getYear).findFirst().orElse(-1);
    }

    public static String winnerYear2014() {
        return Winner.tdfWinners.stream().filter(w -> w.getYear() == 2014).map(Winner::getName).findFirst().orElse("Not Found");
    }

    public static int totalDistance() {
        return Winner.tdfWinners.stream().mapToInt(Winner::getLengthKm).sum();
    }

    public static int shortestDistance() {
        return Winner.tdfWinners.stream().mapToInt(Winner::getLengthKm).min().orElse(-1);
    }

    public static int longestDistance() {
        return Winner.tdfWinners.stream().mapToInt(Winner::getLengthKm).max().orElse(-1);
    }

    public static Winner fastestTDFWinner() {
        return Winner.tdfWinners.stream().max(Comparator.comparingDouble(Winner::getAveSpeed)).orElse(null);
    }

    public static double fastestTDFAveSpeed() {
        return Winner.tdfWinners.stream().mapToDouble(Winner::getAveSpeed).max().orElse(-1);
    }

    public static String allTDFWinnersTeams() {
        return Winner.tdfWinners.stream().map(Winner::getTeam).collect(Collectors.joining(", "));
    }

    public static Map<String, List<Winner>> winnersByNationality() {
        return Winner.tdfWinners.stream().collect(Collectors.groupingBy(Winner::getNationality));
    }

    public static Map<String, Long> winsByNationalityCounting() {
        return Winner.tdfWinners.stream().collect(Collectors.groupingBy(Winner::getNationality, Collectors.counting()));
    }

    public static void printLogStatistik(Path logFilePath) throws IOException {
        List<String> lines = Files.readAllLines(logFilePath);

        System.out.println("Most Common IP: " + getMostCommonIp(lines));
        System.out.println("Most Common Page: " + getMostCommonPage(lines));
        System.out.println("Total Downloaded Bytes: " + countDownloadBytes(lines));
    }

    private static String getMostCommonIp(List<String> lines) {
        return lines.stream().map(line -> line.split(" ")[0]).collect(Collectors.groupingBy(ip -> ip, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No IP found");
    }

    private static String getMostCommonPage(List<String> lines) {
        return lines.stream().map(line -> line.split(" ")[6]).collect(Collectors.groupingBy(page -> page, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No Page found");
    }

    private static long countDownloadBytes(List<String> lines) {
        return lines.stream().map(line -> line.split(" ")[9]).filter(bytes -> !bytes.equals("-")).mapToLong(Long::parseLong).sum();
    }
}
