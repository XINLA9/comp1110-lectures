package comp1110.O05;

public class Inheritance {
    public static void main(String[] args) {
        //var ani = new Animal("steve");
        var jess = new Human("jess");
        var jeff = new Snake("jeff");
        //System.out.println(ani);
        var jess2 = new Human("jess");
        System.out.println("comparing jess to jeff "+ jess.equals(jeff));
        System.out.println("comparing jess to jess2 "+ jess.equals(jess2));
        System.out.println(jess);
        System.out.println(jeff);
        var animals = new Animal[]{jess,jeff};
        for(var a : animals){
            System.out.println(a);
            if( a instanceof Snake){
                ((Snake) a).BiteSomeone();
            }
        }
        // Animal jessAnimal = (Animal) jess;

    }
}
