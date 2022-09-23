package comp1110.J10;

public class Boxing {
    public static void main(String[] args) {
        Integer i = 43; //boxing
        int j = i; //unboxing

        Integer k = new Integer(3);
        var d = Integer.valueOf(3);
        var l = Integer.parseInt("4");
        Integer l1 = Integer.parseInt("4");

        Object o1 = l; //upcasting
        System.out.println(o1);

        Integer i1 = (Integer) o1;

        int x = 3002;
        int y = 3002;
        Integer a = x;
        Integer b = y;

        if(x == y){
            System.out.println("x equals y");
        } else{
            System.out.println("x not equal y");
        }
        if( a == b){
            System.out.println("a == b is true");
        } else {
            System.out.println("a == b is false");
        }
        if(a.equals(b)){
            System.out.println("a equals b is true");
        } else{
            System.out.println("a equals b is false");
        }

    }
}
