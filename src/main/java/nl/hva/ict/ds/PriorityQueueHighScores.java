package nl.hva.ict.ds;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class PriorityQueueHighScores implements HighScoreList {

    private List<Player> players = new ArrayList<>();
    private List<Player> temp = new ArrayList<>();
    private List<Player> heap = new ArrayList<>();
    int currentSize = 0;

    public PriorityQueueHighScores() {
        heap.add(null);

    }

    @Override
    public void add(Player player) {
        heap.add(player);
        swim(++currentSize);
        temp.clear();
        temp.addAll(heap);
        players.clear();

        for (int i = 0; i < currentSize; i++) {
            players.add(temp.get(1));
            temp.set(1, temp.get(temp.size()-1));
            temp.remove(currentSize-i);
            if (temp.size() > 2) {
                sink();
            }
        }

        System.out.println("Player List" + Arrays.toString(players.toArray()));
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        List<Player> highScores = new ArrayList<>();

        // Return nothing when it's empty
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
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        return null;
    }

    private void swim(int index) {
        int parent = index / 2;
        Player bottom = heap.get(index);

        while (index > 1 && heap.get(parent).getHighScore() < bottom.getHighScore()) {
            heap.set(index, heap.get(parent));
            index = parent;
            parent /= 2;
        }
        heap.set(index, bottom);
    }

    private void sink() {
        int index = 1;
        int largerChild;
        Player tempPlayerIndexOne = temp.get(index);
        // save root
        Player top = temp.get(index);

        // while node has at least one child
        while (index < temp.size() / 2.0) {

            int leftChild = 2 * index;
            int rightChild = 2 * index + 1;

            // Set the larger child of the parent
            if (rightChild < temp.size() && temp.get(leftChild).getHighScore() < temp.get(rightChild).getHighScore())
                largerChild = rightChild;
            else {
                largerChild = leftChild;
            }

            // Stops the loop when the parent is larger the the child
            if (top.getHighScore() >= temp.get(largerChild).getHighScore()) {
                break;
            }


            temp.set(index, temp.get(largerChild));
            index = largerChild;
            temp.set(index, tempPlayerIndexOne);
        }
    }

}
