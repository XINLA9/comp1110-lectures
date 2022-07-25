package comp1110.lectures.J10;

public class Boxing {
    public static void main(String[] args) {
        Integer i = 43; // auto-boxing
        int j = i; // auto-unboxing

        //Integer k = new Integer(3);
        var k = Integer.valueOf(3);
        Integer l = Integer.parseInt("30");

        Object o1 = k;
        Object o2 = j; // compiler smart enough to auto-box before upcast
        System.out.println(o2.toString());

        Integer m = (Integer) o1; // downcast back to Integer

        int x = 3;
        int y = 3;
        Integer a = Integer.valueOf(3000);
        Integer b = 3000;
        if (x == y) {
            System.out.println("== same prim");
        } else {
            System.out.println("== different prim");
        }
        if (a == b) {
            System.out.println("== same boxed");
        } else {
            System.out.println("== different boxed");
        }
        if (a.equals(b)) {
            System.out.println("equals same boxed");
        } else {
            System.out.println("equals different boxed");
        }
    }
}
