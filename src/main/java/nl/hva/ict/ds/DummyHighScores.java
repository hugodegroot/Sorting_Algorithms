package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Just a rough sketch that prevents the JUnit tests to case compilation errors.
 * By no means this is a implementation that will work properly!
 */
public class DummyHighScores implements HighScoreList {
    public List<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        players.add(player);
        
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> highScores = new ArrayList<>();

        // Return nothing when you don't want anything
        if (numberOfHighScores == 0) {
            return highScores;
        }

        // Checks if the number of high scores is higher or lower then the lists size
        if (numberOfHighScores > players.size()) {
            numberOfHighScores = players.size();
        }

        // Loops through all players the user wants
        for (int i = 0; i < numberOfHighScores; i++) {
            highScores.add(players.get(i));
        }

        return highScores;
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) {
        List<Player> matchedPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getFirstName().equals(firstName)) {
                matchedPlayers.add(player);
            }
        }
        return matchedPlayers;
    }
}
