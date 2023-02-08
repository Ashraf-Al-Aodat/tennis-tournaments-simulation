package com.ash.tts;

import com.ash.tts.objects.MatchSummary;
import com.ash.tts.objects.Player;
import com.ash.tts.communication.MatchSimulation;

import java.util.ArrayList;

public class TennisTournamentManager implements MatchSimulation {
    private final TennisMatchSimulation matchSimulation;
    private final ArrayList<MatchSummary> summaryArray;

    public TennisTournamentManager(final TennisMatchSimulation tennisMatchSimulation) {
        this.matchSimulation = tennisMatchSimulation;
        this.summaryArray = new ArrayList<>();
    }

    public Player executeMatch(final Player player1, final Player player2) {
        Player winner = matchSimulation.executeMatch(player1, player2);
        summaryArray.add(matchSimulation.getMatchSummary());
        return winner;
    }

    public void printTournamentWinner() {
        System.out.println("----------------------------");
        for (int i = 0; i < summaryArray.size();i++){
            System.out.printf("Match # %d%n", i);
            System.out.println(summaryArray.get(i).toString());
            System.out.println("----------------------------");
        }
    }
}
