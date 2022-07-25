package comp1110.lectures.J04;

import java.util.Random;

public class Strings {
    public static void main(String[] args) {
        String x = "Hello";
        System.out.println(x);
        String y = "world";
        System.out.println(y);
        System.out.println(x + " " + y + "!");

        String z = x + " " + y + "!";
        System.out.println("Concatenation: " + z);  // + operator
        System.out.println("Length: " + z.length());  // length
        System.out.println("charAt: " + z.charAt(4));  // charAt
        Random r = new Random();
        System.out.println("random charAt: " + z.charAt(r.nextInt(z.length())));
        System.out.println("substring: " + z.substring(1, 4));
        System.out.println("toupper: " + z.toUpperCase());
        System.out.println("equals: " + x.equals("Hello"));
        System.out.println("equals: " + x.equals("hello"));

    }
}
