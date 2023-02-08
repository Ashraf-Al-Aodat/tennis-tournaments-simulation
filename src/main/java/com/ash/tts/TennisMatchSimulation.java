package com.ash.tts;

import com.ash.tts.objects.MatchSummary;
import com.ash.tts.objects.Player;
import com.ash.tts.communication.MatchSimulation;

import java.util.Random;
import java.util.stream.Collectors;


public class TennisMatchSimulation implements MatchSimulation {
    private Player player1;
    private Player player2;
    private MatchSummary matchSummary;
    public TennisMatchSimulation() {
        this.matchSummary = new MatchSummary();
    }

    @Override
    public Player executeMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        determineWinner();
        return matchSummary.getWinner();
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

    public void determineWinner() {
        matchSummary = new MatchSummary();
        do {
            matchSummary.setSetScores(playSet());
        } while (matchSummary.getSetScores().size() < 3);
        Player player = matchSummary.getSetScores().stream().map(score -> {
            Player winner = player1;
            if (score[0] < score[1])
                winner = player2;
            return winner;
        }).collect(Collectors.groupingBy(Player::getName, Collectors.summarizingInt(v -> 1)))
                .entrySet().stream().limit(1)
                .map(entry -> {
                    if (entry.getKey().equals(player1.getName()))
                        return player1;
                    return player2;
                })
                .collect(Collectors.toList()).get(0);
        matchSummary.setWinner(player);
    }

    public MatchSummary getMatchSummary() {
        return matchSummary;
    }
}
