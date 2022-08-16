package comp1110.lectures.O05;

public abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + " is a "+ this.getClass().getSimpleName();
    }
}
