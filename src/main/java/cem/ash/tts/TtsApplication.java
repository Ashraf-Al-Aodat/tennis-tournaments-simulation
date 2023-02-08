package cem.ash.tts;

import cem.ash.tts.objects.Player;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class TtsApplication {
    public static void main(String[] args) {
        final TennisTournamentManager tournamentManager = new TennisTournamentManager(new TennisMatchSimulation());
        Stack<Player> players = new Stack<>();
        try {
            players.addAll(
                    getPlayerData().stream()
                    .limit(16)
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int numberOfPlayers = players.size();
        int numberOfMatches = numberOfPlayers / 2;
        System.out.println("Number of players: " + numberOfPlayers + " Number of matches: " + numberOfMatches);
        do {
            final Player player1 = players.pop();
            final Player player2 = players.pop();
            tournamentManager.executeMatch(player1, player2);
        } while (!players.isEmpty());
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
