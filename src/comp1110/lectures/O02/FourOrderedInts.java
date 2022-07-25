package comp1110.lectures.O02;


import java.util.Arrays;

/**
 * Class invariant / Control: always has 4 ints in order
 */
public class FourOrderedInts {
    private int[] data = new int[4];

    public int get(int i) {
        return data[i];
    }

    public void replace(int value1, int value2) {
        for (int i = 0; i < 4; i++) {
            if (data[i] == value1) {
                data[i] = value2;
                break;
            }
        }
        Arrays.sort(data);
    }

    @Override
    public String toString() {
        return "{" + Arrays.toString(data) + '}';
    }
}
