package comp1110.lectures.J04;

import java.util.Random;

public class Integers {
    public static void main(String[] args) {
        int w = 122;
        System.out.println("w: " + w);
        int x = 0b110000;
        System.out.println("x: " + x);
        String s = "23";
        int z = Integer.parseInt(s);
        System.out.println("z: " + z);
        Random r = new Random();
        int y = r.nextInt();
        System.out.println("random: " + y);
        y = r.nextInt(6);
        System.out.println("random: " + y);

        System.out.println("add: " + 3 + 5);
        System.out.println("add: " + (3 + 5));
        System.out.println("sub: " + (3 - 5));
        System.out.println("mul: " + (3 * 5));
        System.out.println("div: " + (3 / 5));
        System.out.println("rem: " + (3 % 5));
        System.out.println("rem: " + (-3 % 5));
        System.out.println("bit shift: " + (0b1000 >> 0));
        System.out.println("bit shift: " + (0b1000 >> 2));
        System.out.println("bit shift: " + (8 >> 0));
        System.out.println("bit shift: " + (8 >> 2));
        System.out.println("bit shift: " + (12345566 >> 2));
        System.out.println("bit shift: " + (12345566 >>> 2));
        System.out.println("bit shift: " + (-12345566 >> 2));
        System.out.println("bit shift: " + (-12345566 >>> 2));
        System.out.println("bit shift: " + (12345566 << 2));
        System.out.println("bit shift: " + (-12345566 << 2));
        System.out.println("and: " + (12345566 & 5));
        System.out.println("and: " + (12345567 & 5));
        System.out.println("and: " + (12345562 & 5));

        System.out.println("int: " + 5);
        System.out.println("convert to double: " + (double) 5);

        System.out.println("max: " + Integer.MAX_VALUE);
        System.out.println("min: " + Integer.MIN_VALUE);

        int sum = x + 5;
        System.out.println("add: " + sum);
        sum = -x  +  5;   // unary -
        System.out.println("add: " + sum);
        sum = +x  +  5;   // unary +
        System.out.println("add: " + sum);
        sum++;
        System.out.println("unary addition: " + sum);
        sum--;
        System.out.println("unary subtraction: " + sum);
    }
}
