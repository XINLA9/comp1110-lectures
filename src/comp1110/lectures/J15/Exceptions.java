package comp1110.lectures.J15;

import java.util.List;
import java.util.ArrayList;

public class Exceptions {

    public static int length(List<Integer> intList) {
        return intList.size();
    }

    public static int sum(List<Integer> intList) {
        int total = 0;
        for (var i : intList)
            total += i;
        return total;
    }

    /**
     *
     * @param intList
     * @return
     * @throws ArithmeticException: If the list doesn't have a well-defined average.
     */
    public static double average(List<Integer> intList) throws ArithmeticException {
        int listLength;
        int listSum;
        try {
            listLength = length(intList);
            listSum = sum(intList);
        }
        catch (NullPointerException np) {
            System.out.println("null pointer caught in average");
            listLength = 0;
            listSum = 0;
        }
        return listSum / listLength;
    }

    static ArrayList<Integer> myList;

    public static void main(String[] args) {
//        myList = new ArrayList<Integer>();
//        myList.add(3);
//        myList.add(4);
//        myList.add(5);
        try {
            System.out.println("The average is " + average(myList));
        }
        catch (NullPointerException e) {
            System.out.println("exception " + e.toString() + " caught in main");
        }
        catch (ArithmeticException e) {
            System.out.println("exception " + e.toString() + " caught in main");
        }
    }
}
