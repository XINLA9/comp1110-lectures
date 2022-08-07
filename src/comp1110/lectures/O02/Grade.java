package comp1110.lectures.O02;

public enum Grade {
    HD(80, "High Distinction"),
    D(70, "Distinction"),
    CR(60, "Credit"),
    P(50, "Pass"),
    N(0, "Fail");

    private final int atLeast; // need a mark of at least this value
    private final String longName;

    Grade(int atLeast, String longName) {
        this.atLeast = atLeast;
        this.longName = longName;
    }

    public static Grade fromMark(int mark) {
        for (var g : Grade.values()) {
            if (mark >= g.atLeast) {
                return g;
            }
        }
        return N;
    }
}
