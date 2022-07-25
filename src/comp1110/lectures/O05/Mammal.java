package comp1110.lectures.O05;

public abstract class Mammal extends Animal {
    public Mammal(String name) {
        super(name);
    }

    public static double getNumberOfSpecies() {
        return 1e6;
    }
}
