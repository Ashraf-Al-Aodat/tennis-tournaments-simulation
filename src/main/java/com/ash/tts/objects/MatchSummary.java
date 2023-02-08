package com.ash.tts.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MatchSummary {
    private String winner;
    private final List<int[]> setScores;

    public MatchSummary() {
        this.setScores = new ArrayList<>();
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<int[]> getSetScores() {
        return setScores;
    }

    public void setSetScores(int[]setScores) {
        this.setScores.add(setScores);
    }

    @Override
    public String toString() {
        List<String> scores = setScores.stream().map(Arrays::toString).collect(Collectors.toList());
        return String.format("The winner is %s with the result of the match %s", winner, scores);
    }
}
