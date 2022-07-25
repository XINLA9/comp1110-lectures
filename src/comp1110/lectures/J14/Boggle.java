package comp1110.lectures.J14;

import comp1110.lectures.Playground.BogglePlay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Boggle {
    static final String[] DICE = { "AJBBOO","AFFPSK","ANEAGE","APSHCO","QNUMHI","ZNHRLN","TDSTYI","TTWOOA","TLRYET","TUMIOC","EDVLRY","EDRLXI","EEGNHW","EIOTSS","ERHTWV","EENUSI" };

    static final int ROW_SIZE = 4;

    static final List<Set<Integer>> neighbours;  // The neighbours for each dice

    /**
     * Relationship between "dice position" and "row, column coordinates"
     * j/c  0  1  2  3
     * i/r  __________
     * 0   |0  1  2  3
     * 1   |4  5  6  7
     * 2   |8  9  10 11
     * 3   |12 13 14 15
     */
    static {
        neighbours = new ArrayList<Set<Integer>>();
        for (var i = 0; i < ROW_SIZE; i++) { // rows
            for (var j = 0; j < ROW_SIZE; j++) { // cols
                var neigh = new HashSet<Integer>();
                // Consider possible neighbours of i, j
                for (var r = i - 1; r <= i + 1; r++) { // rows
                    if (r < 0 || r > 3) { // off the grid
                        continue;
                    }
                    for (var c = j - 1; c <= j + 1; c++) { // cols
                        if (c < 0 || c > 3) { // off the grid
                            continue;
                        }
                        if (i == r && j == c) { // not neighbour of self
                            continue;
                        }
                        var diceIndex = r * 4 + c;
                        neigh.add(diceIndex);
                    }
                }
                neighbours.add(neigh);
            }
        }
    }

    final Set<String> dictionary; // dictionary of valid words
    char[] instance; // game instance

    Boggle() {
        dictionary = loadDict("assets/dictionary.txt");
        instance = shake();
    }

    /**
     * Solve the game instance by finding all possible valid words.
     */
    public void solve() {
        // Generate every combination of letters (following the game rules).
        // Check if each combination is in dictionary.
        var words = new HashSet<String>();
        var visited = new HashSet<Integer>();
        for (var start = 0; start < instance.length; start++) {
            var word = "" + instance[start];
            visited.add(start);
            findWords(words, visited, word, start);
            visited.remove(start);
        }
        System.out.println(words.size());
        System.out.println(words);
    }

    /**
     * Recursively search for words.
     * @param words found so far
     * @param visited the positions visited so far on this path
     * @param word the letters so far on this path
     * @param pos position that was just visited, whose neighbours require exploring
     */
    void findWords(Set<String> words, Set<Integer> visited, String word, int pos) {
        for (var p : neighbours.get(pos)) {
            if (visited.contains(p)) {
                continue;
            }
            // New word
            var newWord = word + instance[p];
            if (newWord.length() >= 3 && dictionary.contains(newWord)) {
                words.add(newWord);
            }
            visited.add(p);
            findWords(words, visited, newWord, p);
            visited.remove(p);
        }
    }

    /**
     * Solve the game instance by finding all possible valid words.
     *
     * This is an alternative implementation to `solve`, which I think is cleaner / easier to interpret.
     * Instead of "visiting" the position before a call to the recursive algorithm, we visit it as part of the
     * recurvise call. This avoids some code duplication, for-loop nesting and is more intuitive.
     *
     */
    public void solveAlternative() {
        // Generate every combination of letters (following the game rules).
        // Check if each combination is in dictionary.
        var words = new HashSet<String>();
        var visited = new HashSet<Integer>();
        for (var start = 0; start < instance.length; start++) {
            visitPosition(words, visited, "", start);
        }
        System.out.println(words.size());
        System.out.println(words);
    }

    /**
     * Recursively search for words.
     * @param words found so far
     * @param visited the positions visited so far on this path
     * @param word the letters so far on this path
     * @param pos position to visit next
     */
    void visitPosition(Set<String> words, Set<Integer> visited, String word, int pos) {
        var newWord = word + instance[pos];
        if (newWord.length() >= 3 && dictionary.contains(newWord)) {
            words.add(newWord);
        }
        visited.add(pos); // Flag this position as visited while considering all child paths
        // Recursing to all unvisited neighbours
        for (var p : neighbours.get(pos)) {
            if (!visited.contains(p)) {
                visitPosition(words, visited, newWord, p);
            }
        }
        visited.remove(pos);
    }

    public static void main(String[] args) {
        var boggle = new Boggle();
        boggle.solve();
        // Alternative implementation (clearer)
        //boggle.solveAlternative();
    }

    /**
     * Load in dictionary from file.
     * @param filename
     * @return
     */
    private static Set<String> loadDict(String filename) {
        // Could use List / ArrayList instead of Set / HashSet for dictionary.
        // However, given we will be doing many calls to `contains()`, we pick HashSet of its constant time lookup
        // performance.
        var dict = new HashSet<String>();
        try {
            var scan = new Scanner(new File(filename));
            while (scan.hasNext()) {
                var word = scan.next();
                dict.add(word.toUpperCase());
            }
            System.out.println("Found " + dict.size() + " words in our dictionary");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return dict;
    }

    /**
     * "Shakes" the dice returning a new random instance.
     * @return
     */
    private static char[] shake() {
        var rand = new Random();
        var taken = new boolean[DICE.length];

        var inst = new char[DICE.length];
        StringBuilder game = new StringBuilder();
        for (int i = 0; i < DICE.length; i++) {
            var d = rand.nextInt(DICE.length);
            // Instead of this nasty hack, if we used a List type we could have used: Collections.shuffle(list)
            while (taken[d]) {
                d = rand.nextInt(DICE.length);
            }
            taken[d] = true;
            var die = DICE[d];
            var face = rand.nextInt(6);
            game.append(die.charAt(face));
            inst[i] = die.charAt(face);
            game.append(" ");
            if ((i + 1) % ROW_SIZE == 0) {
                game.append("\n");
            }
        }

        System.out.println(game);
        return inst;
    }

    @Test
    void testDict() {
        Assertions.assertTrue(dictionary.contains("AARDVARK"));
        Assertions.assertFalse(dictionary.contains("NOTAWORD"));
    }

    @Test
    void testNeighbours() {
        Assertions.assertTrue(neighbours.get(0).contains(1));
        Assertions.assertTrue(neighbours.get(0).contains(4));
        Assertions.assertTrue(neighbours.get(0).contains(5));
        Assertions.assertEquals(3, neighbours.get(0).size());

        Assertions.assertTrue(neighbours.get(5).contains(0));
        Assertions.assertTrue(neighbours.get(5).contains(1));
        Assertions.assertTrue(neighbours.get(5).contains(2));
        Assertions.assertTrue(neighbours.get(5).contains(4));
        Assertions.assertTrue(neighbours.get(5).contains(6));
        Assertions.assertTrue(neighbours.get(5).contains(8));
        Assertions.assertTrue(neighbours.get(5).contains(9));
        Assertions.assertTrue(neighbours.get(5).contains(10));
        Assertions.assertEquals(8, neighbours.get(5).size());
    }
}
