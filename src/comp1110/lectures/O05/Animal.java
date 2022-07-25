package comp1110.lectures.O05;

public abstract class Animal {
    protected String name;

    @Override
    public String toString() {
        //return name + " is a " + super.toString();
        return name + " is a " + this.getClass().getSimpleName();
    }

    public Animal(String name) {
        this.name = name;
    }

    public static double getNumberOfSpecies() {
        return 2e9;
    }
}
