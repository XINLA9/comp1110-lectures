package comp1110.lectures.J04;

import java.util.Random;

public class Booleans {
    public static void main(String[] args) {
        boolean a = true;
        System.out.println("a is " + a);
        boolean b = false;
        System.out.println("b is " + b);
        String c = "TruE";
        System.out.println("c is " + Boolean.parseBoolean(c));

        Random rand = new Random(0);
        Boolean k = rand.nextBoolean();
        Boolean h = rand.nextBoolean();
        System.out.println(k);
        System.out.println(h);

        System.out.println("and: " + (a && b));
        System.out.println("or: " + (a || b));
        System.out.println("not: " + !a);
    }
}
