package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains some unit tests. They by no means ensure that all the requirements are implemented
 * correctly.
 */
public class HighScoreListTest {
    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    private HighScoreList highScores;
    private Player nearlyHeadlessNick;
    private Player dumbledore;

    @Before
    public void setup() {
        // Here you should select your implementation to be tested.
//        highScores = new DummyHighScores();
//        highScores = new InsertionSortHighScores();
//          highScores = new BucketSortHighScores();
        highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);

    }

    @Test
    public void noPlayerNoHighScore() {
        assertTrue("There are high-score while there should be no high-scores!", highScores.getHighScores(1).isEmpty());
    }

    @Test
    public void whenNoHighScoreIsAskedForNonShouldBeGiven() {
        highScores.add(dumbledore);

        assertEquals(0, highScores.getHighScores(0).size());
    }

    @Test
    public void noMoreHighScoresCanBeGivenThenPresent() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(10).size());
    }

    @Test
    public void keepAllHighScores() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(2).size());
    }

    @Test
    public void singlePlayerHasHighScore() {
        highScores.add(dumbledore);

        assertEquals(dumbledore, highScores.getHighScores(1).get(0));
    }

    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
        highScores.add(harry);

        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

    // Extra unit tests go here

    @Test
    public void makeSortedListOfTenPersons() {
        Player eerste = new Player("Eerste", "Plaats", 10);
        Player tweede = new Player("tweede", "Plaats", 9);
        Player derde = new Player("derde", "Plaats", 8);
        Player vierde = new Player("vierde", "Plaats", 7);
        Player vijfde = new Player("vijfde", "Plaats", 6);
        Player zesde = new Player("zesde", "Plaats", 5);
        Player zevende = new Player("zevende", "Plaats", 4);
        Player achtste = new Player("achtste", "Plaats", 3);
        Player negende = new Player("negende", "Plaats", 2);
        Player tiende = new Player("tiende", "Plaats", 1);

        highScores.add(zesde);
        highScores.add(achtste);
        highScores.add(tweede);
        highScores.add(zevende);
        highScores.add(vierde);
        highScores.add(derde);
        highScores.add(tiende);
        highScores.add(eerste);
        highScores.add(vijfde);
        highScores.add(negende);

        List<Long> expected = Arrays.asList(
                eerste.getHighScore(),
                tweede.getHighScore(),
                derde.getHighScore(),
                vierde.getHighScore(),
                vijfde.getHighScore(),
                zesde.getHighScore(),
                zevende.getHighScore(),
                achtste.getHighScore(),
                negende.getHighScore(),
                tiende.getHighScore());

        List<Player> temp = highScores.getHighScores(10);
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            result.add(temp.get(i).getHighScore());
        }
        assertEquals(expected, result);
    }

    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }
}