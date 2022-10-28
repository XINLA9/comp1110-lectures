/**
 * Example of a static utilities class (shamelessly paraphrased from
 * Eckel's book "Thinking in Java").
 */
package comp1110.lectures.R02;

import java.util.StringJoiner;

public class PrintUtil {

    static void printArray(Object[] stuffs) {
        StringJoiner j = new StringJoiner(", ");
        for (var s : stuffs) {
            j.add(s.toString());
        }
        System.out.println(j.toString());
    }

}
