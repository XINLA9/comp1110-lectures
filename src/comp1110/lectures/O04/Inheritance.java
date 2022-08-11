package comp1110.lectures.O04;

public class Inheritance {
    public static void main(String[] args) {
        var ani = new Animal("Steve");
        var jess = new Human("Jess");
        var snake = new Snake("Jeff");

        var jess2 = new Human("Jess");
        System.out.println("jess equals jess2? " + jess.equals(jess2));

        // Upcast to parent class (done at compile time)
        Animal jessyAnimal = (Animal) jess2;
        //Animal jessyAnimal = jess2;  // don't need explicit cast here
        //int jessyInt = (int) jess2;

        var animals = new Animal[]{ani, jess, snake};

        for (var a : animals) {
            System.out.println(a);
            if (a instanceof Snake) {
                // Downcast to child class (done at runtime)
                Snake s = (Snake) a;
                s.biteSomeone();
            }
            // Can use instanceof with interface types also
            if (a instanceof Greeter) {
                ((Greeter) a).greet();
            }
            // Runtime error when Downcast fails
            //Snake s2 = (Snake) a;
            //s2.biteSomeone();
        }

        System.out.println(Animal.getNumberOfSpecies());
        System.out.println(Mammal.getNumberOfSpecies());

        Greeter jessGreet = jess;
        var greeters = new Greeter[]{jess, new Robot(), new Human("Wang", Human.Language.MANDARIN)};
        for (var v : greeters) {
            v.greet();
        }
    }
}
