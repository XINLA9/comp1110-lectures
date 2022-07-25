package comp1110.lectures.C01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaternalLineage {
    /**
     * Name of maternal ancestor
     * @param n n=1 => "mother", n=2 => "grandmother", ...
     * @return
     */
    public static String maternalAncestor(int n) {
        assert n > 0;
        return switch (n) {
            case 1 -> "mother"; // base case
            case 2 -> "grandmother"; // base case
            default -> "great-" + maternalAncestor(n - 1); // recursion
        };
    }

    public static void main(String[] args) {
        System.out.println("n=1: " + maternalAncestor(1));
        System.out.println("n=2: " + maternalAncestor(2));
        System.out.println("n=10: " + maternalAncestor(10));
    }

    @Test
    public void maternalAncestorTest() {
        Assertions.assertEquals("mother", maternalAncestor(1));
        Assertions.assertEquals("grandmother", maternalAncestor(2));
        Assertions.assertEquals("great-grandmother", maternalAncestor(3));
    }
}
