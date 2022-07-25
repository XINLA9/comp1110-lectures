package comp1110.lectures.J08;

public class Flooring {
    enum Cleanliness {
        CLEAN,
        SOILED,
        DIRTY,
        FILTHY,
        RANCID;
    };

    private String type;
    private Cleanliness state;
    private Room room;

    @Override
    public String toString() {
        return "Flooring{" +
                "type='" + type + '\'' +
                ", state=" + state +
                ", room=" + room.getName() +
                '}';
    }

    public Flooring(String type, Room room) {
        this.type = type;
        this.state = Cleanliness.CLEAN;
        this.room = room;
    }
}
