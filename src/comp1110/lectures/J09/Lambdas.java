package comp1110.lectures.J09;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class Lambdas {
    public static void printMultiplesOf(int[] a, int divisor) {
        for (var v : a) {
            if (v % divisor == 0) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
    }

    public static void printSquared(int[] a) {
        for (var v : a) {
            for (int i = 0; i <= v; i++) {
                if (i * i == v) {
                    System.out.print(v + " ");
                }
            }
        }
        System.out.println();
    }

    public static void printWhen(int[] a, IntPredicate pred) {
        for (var v : a) {
            if (pred.test(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
    }

    interface DoubleFromInts {
        double convert(int a, int b);
    }

    public static void captureCall(DoubleFromInts lambda) {
        System.out.println(lambda.convert(6, 10));
    }

    public static void main(String[] args) {
        var a = new int[50];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        printMultiplesOf(a, 2);
        printMultiplesOf(a, 3);
        printSquared(a);
        printWhen(a, x -> x % 2 == 0);
        printWhen(a, x -> x % 3 == 0);
        printWhen(a, x -> {
            for (int i = 0; i <= x; i++) {
                if (i * i == x) {
                    return true;
                }
            }
            return false;
        });
        IntPredicate pred = x -> true;
        DoubleFromInts myLambda = (x, y) -> (double) x + y;
        System.out.println(myLambda.convert(5, 6));

        var c = new int[2];
        DoubleFromInts myCapture = (x, y) -> {
            c[0] = x;
            c[1] = y;
            return (double) x + y;
        };
        System.out.println(Arrays.toString(c));
        System.out.println(myCapture.convert(5, 6));
        System.out.println(Arrays.toString(c));
        captureCall(myCapture);
        System.out.println(Arrays.toString(c));
    }
}
