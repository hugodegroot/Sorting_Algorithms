/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.ict.ds;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class InsertionSortHighScores implements HighScoreList{
    
    public List<Player> players = new ArrayList<>();

    @Override
    public void add(Player player) {
        players.add(player);
        InsertionSortHighScores(players);
        for(int k = 0; k < players.size(); k++){
            System.out.println(players.get(k).getHighScore());
        }
        
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

        return highScores;    }

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

    public static void InsertionSortHighScores(List<Player> players){
        //loop door de lijst heen
        for (int i = 1; i < players.size(); i++) {
            Player playerToSort = players.get(i); //pakt de geselecteerde speler en maakt er een tijdelijk object van
            Integer j = i; //kopieert i
            while (j > 0 && players.get(j-1).getHighScore() < playerToSort.getHighScore()) { //loopt tot dat de highscore voor de geselecteerde speler groter is
                players.set(j, players.get(j-1)); //verandert de waarde naar degene daarvoor
                j--;
            }
            players.set(j, playerToSort); //zet de tijdelijke waarde weer op de goede plek
            
        }
    }

}
