package comp1110.lectures.C01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSort {
    /**
     * Sort array of ints using merge sort algorithm.
     * @param unsorted
     * @return
     */
    public static int[] mergeSort(int[] unsorted) {
        // base case
        if (unsorted.length <= 1) {
            return unsorted;
        }

        // splitting
        var lSize = unsorted.length / 2;
        var rSize = unsorted.length - lSize;
        var left = new int[lSize];
        var right = new int[rSize];
        System.arraycopy(unsorted, 0, left, 0, lSize);
        System.arraycopy(unsorted, lSize, right, 0, rSize);

        var lSorted = mergeSort(left);
        var rSorted = mergeSort(right);

        // merging
        var l = 0;
        var r = 0;
        var sorted = new int[unsorted.length];
        for (var i = 0; i < sorted.length; i++) {
            if (l >= lSorted.length) { // left-hand array has been "emptied"
                sorted[i] = rSorted[r];
                r += 1;
            } else if (r >= rSorted.length) { // right-hand array has been "emptied"
                sorted[i] = lSorted[l];
                l += 1;
            } else {
                if (lSorted[l] <= rSorted[r]) {
                    sorted[i] = lSorted[l];
                    l += 1;
                } else {
                    sorted[i] = rSorted[r];
                    r += 1;
                }
            }
        }

        return sorted;
    }

    @Test
    public void mergeSortTest() {
        var unsorted = new int[]{5, 6, 16, 13, 2, 3, 10, 2, 3};
        var sorted = new int[]{2, 2, 3, 3, 5, 6, 10, 13, 16};
        Assertions.assertArrayEquals(sorted, mergeSort(unsorted));
    }
}
