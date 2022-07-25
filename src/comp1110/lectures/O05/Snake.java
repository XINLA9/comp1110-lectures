package comp1110.lectures.O05;

public class Snake extends Reptile {
    public Snake(String name) {
        super(name);
    }

    public void biteSomeone() {
        System.out.println("Ouch!");
    }

    @Override
    public void lick() {
        System.out.println("slurp");
    }
}
