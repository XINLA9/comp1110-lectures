package comp1110.O05;

public class Snake extends Reptile {
    public Snake(String name) {
        super(name);
    }
    void BiteSomeone(){
        System.out.println("Ouch!");
    }

    @Override
    void lick(){
        System.out.println("slurp!");
    }
}
