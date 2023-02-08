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
        int factor = 2;
        int tournamentCounter = (summaryArray.size()+1)/factor;
        System.out.println("----------------------------");
        for (int i = 0; i < summaryArray.size()-1;i++){
            if(i == tournamentCounter){
                factor+=2;
                tournamentCounter+= (summaryArray.size()+1)/factor;
                if (tournamentCounter+1 != summaryArray.size()){
                    System.out.println("Start New Round");
                }
            }
            System.out.printf("Match # %d%n", i+1);
            System.out.println(summaryArray.get(i).toString());
            System.out.println("----------------------------");
        }
        System.out.println("Last Match");
        System.out.println(summaryArray.get(summaryArray.size()-1).toString());
    }
}
