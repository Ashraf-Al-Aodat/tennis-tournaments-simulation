package com.ash.tts;

import com.ash.tts.objects.Player;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class TtsApplication {
    public static void main(String[] args) {
        final TennisTournamentManager tournamentManager = new TennisTournamentManager(new TennisMatchSimulation());
        Deque<Player> players;
        try {
            players = getPlayerData().stream()
                    .limit(16)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        do {
            final Player player1 = players.pop();
            final Player player2 = players.pop();
            Player winner = tournamentManager.executeMatch(player1, player2);
            players.addLast(winner);
        } while (!(players.size() == 1));
        tournamentManager.printTournamentWinner();
    }

    // Retrieve player data from a source file
    private static List<Player> getPlayerData() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = new String(Files.readAllBytes(Paths.get("src/test/resources/players.json")));
        List<Player> players = objectMapper.readValue(jsonString, new TypeReference<List<Player>>() {});
        Collections.shuffle(players);
        return players;
    }
}
