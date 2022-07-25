package comp1110.lectures.J08;

public class RoomStaticNested {
    public static class Flooring {
        enum Cleanliness {
            CLEAN,
            SOILED,
            DIRTY,
            FILTHY,
            RANCID;
        };

        private String type;
        private Cleanliness state;
        private RoomStaticNested room;

        @Override
        public String toString() {
            return "Flooring{" +
                    "type='" + type + '\'' +
                    ", state=" + state +
                    ", room=" + room.getName() +
                    '}';
        }

        public Flooring(String type, RoomStaticNested room) {
            this.type = type;
            this.state = Cleanliness.CLEAN;
            this.room = room;
        }
    }

    private String name;
    private Flooring floor;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RoomStaticNested{" +
                "name='" + name + '\'' +
                ", floor=" + floor +
                '}';
    }

    public RoomStaticNested(String name, String floor) {
        this.name = name;
        this.floor = new Flooring(floor, this);
    }
}
