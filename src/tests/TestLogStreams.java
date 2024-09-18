package tests;

import logfiles.LogStreams;

import java.io.IOException;
import java.nio.file.Path;

public class TestLogStreams {
    public static void main(String[] args) throws IOException {
        System.out.println("zeichenStatistik:");
        System.out.println(LogStreams.zeichenStatistik("Hallo Welt!"));
        System.out.println();
        System.out.println("winnersOfToursLessThan3500km:");
        System.out.println(LogStreams.winnersOfToursLessThan3500km());
        System.out.println("winnersOfToursGreaterThan3500km:");
        System.out.println(LogStreams.winnersOfToursGreaterThan3500km());
        System.out.println("winnerObjectsOfToursLessThan3500kmLimit2:");
        System.out.println(LogStreams.winnerObjectsOfToursLessThan3500kmLimit2());
        System.out.println("firstTwoWinnersOfToursLessThan3500km:");
        System.out.println(LogStreams.firstTwoWinnersOfToursLessThan3500km());
        System.out.println("distinctTDFWinners:");
        System.out.println(LogStreams.distinctTDFWinners());
        System.out.println("numberOfDistinctWinners:");
        System.out.println(LogStreams.numberOfDistinctWinners());
        System.out.println("skipEveryOtherTDFWinner:");
        System.out.println(LogStreams.skipEveryOtherTDFWinner());
        System.out.println("mapWinnerYearNamesToList:");
        System.out.println(LogStreams.mapWinnerYearNamesToList());
        System.out.println("wigginsWins:");
        System.out.println(LogStreams.wigginsWins());
        System.out.println("winnerYear2014:");
        System.out.println(LogStreams.winnerYear2014());
        System.out.println("totalDistance:");
        System.out.println(LogStreams.totalDistance());
        System.out.println("shortestDistance:");
        System.out.println(LogStreams.shortestDistance());
        System.out.println("longestDistance:");
        System.out.println(LogStreams.longestDistance());
        System.out.println("fastestTDFWinner:");
        System.out.println(LogStreams.fastestTDFWinner());
        System.out.println("fastestTDFAveSpeed:");
        System.out.println(LogStreams.fastestTDFAveSpeed());
        System.out.println("allTDFWinnerTeams:");
        System.out.println(LogStreams.allTDFWinnersTeams());
        System.out.println("winnersByNationality:");
        System.out.println(LogStreams.winnersByNationality());
        System.out.println("winnersByNationalityCount:");
        System.out.println(LogStreams.winsByNationalityCounting());
        System.out.println();
        LogStreams.printLogStatistik(Path.of("access_log/access.log"));
    }
}
