package comp1110.lectures.J01;

import java.util.Scanner;

public class IOTest {
    public static void main(String[] args) {
        int a = 5;
        System.out.println("Hello there, my favourite number is " + a);
        System.out.println("What is yours?");
        Scanner scanner = new Scanner(System.in);
        int b = scanner.nextInt();
        System.out.println("your's is " + b);
        System.out.println("Mine minus your's " + (a - b));
        System.out.println("Enter some text");
        String c = scanner.next();
        System.out.println(c);
    }
}
