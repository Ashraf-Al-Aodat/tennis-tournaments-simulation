package cem.ash.tts;

import cem.ash.tts.objects.MatchSummary;
import cem.ash.tts.objects.Player;
import cem.ash.tts.observer.MatchSimulation;

import java.util.ArrayList;

public class TennisTournamentManager implements MatchSimulation {
    private final TennisMatchSimulation matchSimulation;
    private final ArrayList<MatchSummary> summaryArray;

    public TennisTournamentManager(final TennisMatchSimulation tennisMatchSimulation) {
        this.matchSimulation = tennisMatchSimulation;
        this.summaryArray = new ArrayList<>();
    }

    public void executeMatch(final Player player1, final Player player2) {
        matchSimulation.executeMatch(player1, player2);
        summaryArray.add(matchSimulation.determineWinner());
    }

    public void printTournamentWinner() {
        summaryArray.forEach(System.out::println);
    }
}
