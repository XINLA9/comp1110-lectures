package comp1110.lectures.J04;

import java.util.Random;

public class Doubles {
    public static void main(String[] args) {
        double x = 3.1;
        System.out.println("x: " + x);
        double y = 1.23456e-7;
        System.out.println("y: " + y);
        String s = "3.123";
        double z = Double.parseDouble(s);
        System.out.println("z: " + z);
        Random r = new Random();
        double w = r.nextDouble();
        System.out.println(w);


        System.out.println("add: " + 3.0 + 5.2);   // string concatenation
        System.out.println("add: " + (3.0 + 5.2));
        System.out.println("sub: " + (3.0 - 5.2));
        System.out.println("mul: " + (3.0 * 5.2));
        System.out.println("div: " + (3.0 / 5.2));
        System.out.println("pow: " + Math.pow(7.0, 2.0));

        System.out.println("convert to int (floor): " + ((int) (5.2)));
        System.out.println("convert to int (floor): " + ((int) (5.9)));
        System.out.println("convert to int (round): " + ((int) (Math.round(5.2))));
        System.out.println("convert to int (round): " + ((int) (Math.round(5.9))));
        System.out.println("convert to int (ceil): " + ((int) (Math.ceil(5.2))));
        System.out.println("convert to int (ceil): " + ((int) (Math.ceil(5.9))));

        System.out.println("format: " + String.format("%.2f", 3.124));
        System.out.println("max: " + Double.MAX_VALUE);
        System.out.println("min: " + Double.MIN_VALUE);
        System.out.println("min: " + Double.SIZE);
    }
}
