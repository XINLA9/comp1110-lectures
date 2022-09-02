package comp1110.J11;

import java.util.Random;

public class Boggle {
    static final String[] DICE = { "AJBBOO","AFFPSK","ANEAGE","APSHCO","QNUMHI","ZNHRLN","TDSTYI","TTWOOA","TLRYET","TUMIOC","EDVLRY","EDRLXI","EEGNHW","EIOTSS","ERHTWV","EENUSI" };
    static final int ROW_SIZE =4;

    public static void main(String[] args) {

        var rand = new Random();
        StringBuilder game = new StringBuilder();
//        for(int i = 0; i < DICE.length; i++){
//            var die = DICE[i];
//            var face = rand.nextInt(6);
//            game += die.charAt(face);
//            if( (i+1) % ROW_SIZE == 0){
//                game += "\n";
//            }
//        }
//        System.out.println(game);

//        for(int i = 0; i < DICE.length; i++){
//            var d = rand.nextInt(DICE.length);
//            var die = DICE[d];
//            var face = rand.nextInt(6);
//            game += die.charAt(face);
//            if( (i+1) % ROW_SIZE == 0){
//                game += "\n";
//            }
//        }
//        System.out.println(game);
        var taken = new boolean[DICE.length];

        for(int i = 0; i < DICE.length; i++){
            int d;
            do {
                d = rand.nextInt(DICE.length);
            }while(taken[d]);
            taken[d] = true;
            var die = DICE[d];
            var face = rand.nextInt(6);
            game.append(die.charAt(face));
            if( (i+1) % ROW_SIZE == 0){
                game.append("\n");
            }
        }
        System.out.println(game);
    }
}
