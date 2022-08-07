package comp1110.lectures.J08;

public class Room {
    private String name;
    private Flooring floor;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", floor=" + floor +
                '}';
    }

    public Room(String name, String floor) {
        this.name = name;
        this.floor = new Flooring(floor, this);
    }
}
