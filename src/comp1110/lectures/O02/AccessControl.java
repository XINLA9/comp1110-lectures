package comp1110.lectures.O02;

public class AccessControl {
    public static void main(String[] args) {
        FourOrderedInts foi = new FourOrderedInts();

        foi.replace(0, 3);
        foi.replace(0, 8);
        foi.replace(0, 1);
        foi.replace(0, 5);
        //foi.data[1] = 100;
        //foi.data = new int[]{1, 2, 3};

        System.out.println(foi);
    }
}
