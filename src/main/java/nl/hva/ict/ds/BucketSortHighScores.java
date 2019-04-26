/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class BucketSortHighScores implements HighScoreList {

    private final int DIVIDER = 10000;
    private List<Player> players = new ArrayList<>();
    private List<List<Player>> buckets = new ArrayList<>();

    public BucketSortHighScores() {
        // Make the buckets
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    public void add(Player player) {

        // Fill the buckets with players
        long bucketPlace = player.getHighScore() / DIVIDER;
        buckets.get((int) bucketPlace).add(player);

        // Sort the player
        Collections.sort(buckets.get((int) bucketPlace));

        players.clear();

        if(bucketPlace == 10) {
            --bucketPlace;
        }

        for (int i = buckets.size() - 1; i >= 0; i--) {
            if (buckets.get(i) == null) {
                continue;
            }
            players.addAll(buckets.get(i));
        }
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
//        ArrayList<Integer> bucket = new ArrayList<>();
//        Bucket[] buckets = new Bucket[numberOfHighScores];
//        for(int i = 0; i < numberOfHighScores; i++){
//            buckets[i] = new Bucket();
//        }
//        
//        for(int player)
//    
//
//
//

return players.subList(0, Math.min(numberOfHighScores, players.size()));

//        return insertion.getHighScores(1000);
    
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

class Bucket {
    ArrayList<Integer> bucket = new ArrayList<>();
}
