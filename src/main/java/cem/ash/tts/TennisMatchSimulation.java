package cem.ash.tts;

import cem.ash.tts.objects.MatchSummary;
import cem.ash.tts.objects.Player;
import cem.ash.tts.observer.MatchSimulation;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


public class TennisMatchSimulation implements MatchSimulation {
    private Player player1;
    private Player player2;
    public TennisMatchSimulation() {
    }

    @Override
    public void executeMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public int[] playSet() {
        int player1SetWon = 0;
        int player2SetWon = 0;
        while (true) {
            boolean isPlayer1WonTheGame = playGame();
            if (isPlayer1WonTheGame){
                player1SetWon++;
            } else {
                player2SetWon++;
            }
            if ((player1SetWon >= 6 || player2SetWon >= 6)  && Math.abs(player1SetWon - player2SetWon) >= 2) {
                return new int[]{player1SetWon, player2SetWon};
            }
        }
    }

    private boolean playGame(){
        int player1PerformanceValue = player1.getPerformanceValue();
        int player2PerformanceValue = player2.getPerformanceValue();
        int player1Point = 0;
        int player2Point = 0;

        Random random = new Random();
        while (true) {
            int player1Chance = random.nextInt(player1PerformanceValue + player2PerformanceValue);
            if (player1Chance >= player2PerformanceValue) {
                player1Point++;
            } else {
                player2Point++;
            }
            if ((player1Point >= 4 || player2Point >= 4)  && Math.abs(player1Point - player2Point) >= 2) {
                return player1Point > player2Point;
            }
        }
    }

    public MatchSummary determineWinner() {
        final MatchSummary matchSummary =  new MatchSummary();
        do {
            matchSummary.setSetScores(playSet());
        } while (matchSummary.getSetScores().size() < 3);
        String winner = matchSummary.getSetScores().stream().map(score -> {
            String winnerName = player1.getName();
            if (score[0] < score[1])
                winnerName = player2.getName();
            return winnerName;
        }).collect(Collectors.groupingBy(e -> e, Collectors.summarizingInt(v -> 1)))
                .entrySet().stream().limit(1)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining());
        matchSummary.setWinner(winner);
        return matchSummary;
    }
}
