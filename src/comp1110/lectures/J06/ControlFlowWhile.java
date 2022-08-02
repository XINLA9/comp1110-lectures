package comp1110.lectures.J06;

public class ControlFlowWhile {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 5) {
            System.out.println("i is " + i);
            i += 1;
        }

        System.out.println("Between");

        i = 4;
        do {
            System.out.println("i is " + i);
            i -= 1;
        } while (i >= 5);
    }
}
