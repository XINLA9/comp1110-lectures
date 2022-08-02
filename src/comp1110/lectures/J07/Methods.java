package comp1110.lectures.J07;

public class Methods {
    static void hello() {
        System.out.println("Hello");
    }

    static int addOne(int i) {
        i = i + 1;
        return i;
    }

    static String appendInt(int i) {
        return "prefix" + i;
    }

    static void mutateArray(int[] b) {
        b[1] = 8;

        b = new int[]{1, 1};
    }

    static int[] mutateArrayAndReturn(int[] b) {
        b[1] = 8;

        b = new int[]{1, 1};
        return b;
    }

    public static void main(String[] args) {
        hello();
        Methods.hello();

        int k = 5;
        System.out.println("k before " + k);
        //k = Methods.addOne(k);
        Methods.addOne(k);
        System.out.println("k after " + k);

        System.out.println("appending " + Methods.appendInt(k));

        int[] a = new int[]{1, 2, 3};
        System.out.println("Before");
        for (int v : a) {
            System.out.println("value is " + v);
        }
        Methods.mutateArray(a);
        System.out.println("After");
        for (int v : a) {
            System.out.println("value is " + v);
        }

        System.out.println("Next one");
        int[] c = Methods.mutateArrayAndReturn(a);
        for (int v : c) {
            System.out.println("value is " + v);
        }
    }
}
