package comp1110.J14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Boggle {
    static final String[] DICE = { "AJBBOO","AFFPSK","ANEAGE","APSHCO","QNUMHI","ZNHRLN","TDSTYI","TTWOOA","TLRYET","TUMIOC","EDVLRY","EDRLXI","EEGNHW","EIOTSS","ERHTWV","EENUSI" };
    static final int ROW_SIZE =4;

    char[] gameInstance;

    static final Set<String> dict = loadDict("resources/words/dictionary.txt");

    static final List<Set<Integer>> neighbours;
    /**
     * j    0  1  2  3
     * i ______________
     * 0 |   0  1  2  3
     * 1 |   4  5  6  7
     * 2 |   8  9 10  11
     * 3 |  12 13 14  15
     */
    static {
        neighbours = new ArrayList<Set<Integer>>();
        for(int i = 0; i < ROW_SIZE; i++){
            for(int j = 0; j < ROW_SIZE; j++){
                var pos = i*ROW_SIZE+j;
                neighbours.add(new HashSet<Integer>());
                for( var r = Math.max(0,i-1); r <= Math.min(i+1,ROW_SIZE-1); ++r){
                    for( var c = Math.max(0,j-1); c <= Math.min(j+1, ROW_SIZE-1); ++c){
                        if( r == i && c == j)  continue;
                        // if (r >= ROW_SIZE || c >= ROW_SIZE) continue;
                        // if (r < 0 || c < 0) continue;
                        var neighPos = r *ROW_SIZE + c;
                        neighbours.get(pos).add(neighPos);
                    }
                }
                System.out.println("Die "+pos+" has neighbours");
                //System.out.print(neighbours.get(pos));
                neighbours.get(pos).forEach(n-> System.out.print(n+" "));
                System.out.println();
            }
        }

    }

    public Boggle() {
        shuffle();
    }

    public static void main(String[] args) {
        var boggle = new Boggle();
        boggle.solve(true);
        boggle.solve(false);
    }

    public void solve(boolean resetWord){
        // 1. generate possible words
        // 2. check the words follow the game rules
        // 3. check against an English dictionary
        for(var start = 0; start < gameInstance.length; start++) {
            var words = new ArrayList<String>();
            var word = Character.toString(gameInstance[start]);
            var visited = new HashSet<Integer>();
            visited.add(start);
            findWords(words, visited, word, start, resetWord);
            //visited.remove(start);
            System.out.println("Starting from pos " + start);
            System.out.println(words);
        }
    }

    void findWords(ArrayList<String> words, HashSet<Integer> visited, String word, int pos, boolean resetWord){

        var wordState = word;
        for( var n : neighbours.get(pos)){
            if(visited.contains(n)) continue;
            word = wordState;
            word += Character.toString(gameInstance[n]);
            if (dict.contains(word)){
                words.add(word);
                if(resetWord){
                    word = "";
                }
            }
            visited.add(n);
            findWords(words, visited, word, n, resetWord);
            visited.remove(n);


        }

    }

    public static Set<String> loadDict (String filename){

        var dict = new HashSet<String>();
        try {
            var scan = new Scanner(new File(filename));
            while(scan.hasNext()){
                var word = scan.next();
                dict.add(word.toUpperCase());
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Loaded "+dict.size()+
                          " word in the reference dictionary");
        return dict;

    }

    private void shuffle() {
        var rand = new Random();
        StringBuilder game = new StringBuilder();
        var taken = new boolean[DICE.length];
        gameInstance = new char[DICE.length];

        for(int i = 0; i < DICE.length; i++){
            int d;
            do {
                d = rand.nextInt(DICE.length);
            }while(taken[d]);
            taken[d] = true;
            var die = DICE[d];
            var face = rand.nextInt(6);
            game.append(die.charAt(face)+" ");
            gameInstance[i] = die.charAt(face);
            if( (i+1) % ROW_SIZE == 0){
                game.append("\n");
            }
        }
        System.out.println(game);
    }

    @Test
    public void testDict(){
        Assertions.assertTrue(dict.contains("aahing"));
    }
    
    @Test
    public void testNeigh(){
        Assertions.assertTrue(neighbours.get(0).contains(1));
        Assertions.assertTrue(neighbours.get(0).contains(4));
        Assertions.assertTrue(neighbours.get(0).contains(5));
    }


}

