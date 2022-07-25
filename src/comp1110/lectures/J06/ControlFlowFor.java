package comp1110.lectures.J06;

public class ControlFlowFor {
    public static void main(String[] args) {
        for (int i = 0; i <= 3; i++) {
            System.out.println("i is " + i);
        }

        System.out.println("Next example");

        for (int i = 10; i > 7; i--) {
            System.out.println("i is " + i);
        }

        System.out.println("Next example");

        int[] a = new int[]{3, 5, 6, 8, 10, 13};
        for (int i = 0; i < a.length; i++) {
            int v = a[i];
            System.out.println("the " + i + "th element of a is " + v);
        }

        System.out.println("Next example");

        for (int v : a) {
            System.out.println("array value this time is " + v);
        }

        System.out.println("Next example");

        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    System.out.println("the same");
                    //continue;
                    break;
                    //break outer;
                    //return;
                }
                System.out.println("i and j are " + i + " " + j);
            }
        }
    }
}
