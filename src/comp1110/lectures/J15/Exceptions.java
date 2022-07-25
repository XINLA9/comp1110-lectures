package comp1110.lectures.J15;

import java.io.IOException;

public class Exceptions {
    /**
     * Own checked exception that can accept a message
     * to provide some context.
     */
    static class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    static void exceptionalMethod() throws IOException {
        System.out.println("start of exceptional method");
        //int i = 5 / 0;

        if (true) {
            throw new IOException("Some IO problems");
        }
        System.out.println("end of exceptional method");
    }

    /**
     *
     * @param i must be non-negative
     */
    static void pickyMethod(int i) {
        System.out.println("start of picky method");
        if (i < 0) {
            throw new IllegalArgumentException("require non-negative int");
        }
        System.out.println("end of picky method");
    }

    public static void main(String[] args) {
        try {
            pickyMethod(5);
            pickyMethod(-5);
            exceptionalMethod();
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e);
        } catch (IOException e) {
            System.out.println("Exception caught: " + e);
        }
        System.out.println("end of main");
    }
}
