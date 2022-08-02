package comp1110.lectures.J05;

public class ControlFlowIf {
    public static void main(String[] args) {
        boolean a = false;
        boolean b = true;

        if (a == true) { // overly verbose
            if (b) {
                System.out.println("a is true and b is true");
            } else {
                System.out.println("a is true and b is false");
            }
        } else {
            if (b) {
                System.out.println("a is false and b is true");
            } else {
                System.out.println("a is false and b is false");
            }
        }

        if (a && b) {
            System.out.println("both are true");
        } else {
            System.out.println("one of them are false");
        }

        int i = 6;
        if (i > 7) {
            System.out.println("i is greater than 7");
        } else if (i > 3) {
            System.out.println("i is greater than 3 but less or equal 7");
        }
    }
}
