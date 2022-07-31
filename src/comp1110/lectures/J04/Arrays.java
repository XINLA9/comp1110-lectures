package comp1110.lectures.J04;

public class Arrays {
    public static void main(String[] args) {
        double[] a; // declaration

        //System.out.println(a[0]);

        a = new double[4]; // instantiate

        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
        System.out.println(a[3]);

        a[1] = 5.5;

        System.out.println();
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
        System.out.println(a[3]);

        double[] b = new double[6];
        System.out.println(b[0]);

        int[] c = new int[]{1, 2, 3}; // initialise with values
        System.out.println(c[1]);

        System.out.println("Length of c: " + c.length);
        System.out.println("Last element in c: " + c[c.length - 1]);

        // Two ways to declare
        int[] d; // common convention
        int e[]; // don't do this
    }
}
