package comp1110.lectures.J03;

public class Doubles {
    public static void main(String[] args) {
        double d = 1.23;
        System.out.println(d);
        d = 1.0 / 3.0;
        System.out.println(d);
        d = 1.76e-2;
        System.out.println(d);
        System.out.println(Double.parseDouble("1.37"));
        System.out.println(Double.parseDouble("13"));
        //System.out.println(Double.parseDouble("a13"));
        System.out.println(Integer.parseInt("13.5"));
    }
}
