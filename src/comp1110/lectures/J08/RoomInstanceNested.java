package comp1110.lectures.J08;

public class RoomInstanceNested {
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

        @Override
        public String toString() {
            return "Flooring{" +
                    "type='" + type + '\'' +
                    ", state=" + state +
                    ", room=" + name +
                    '}';
        }

        public Flooring(String type) {
            this.type = type;
            this.state = Cleanliness.CLEAN;
        }
    }

    private String name;
    private Flooring floor;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RoomInstanceNested{" +
                "name='" + name + '\'' +
                ", floor=" + floor +
                '}';
    }

    public RoomInstanceNested(String name, String floor) {
        this.name = name;
        this.floor = new Flooring(floor);
    }
}
