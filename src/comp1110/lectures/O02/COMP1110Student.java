package comp1110.lectures.O02;

public class COMP1110Student extends Student {
    private int ass1;
    private int ass2;
    private int labtest;
    private int ce;
    private int mse;
    private int exam;

    public COMP1110Student(String name, int age, String uid, int ass1, int ass2, int labtest, int ce, int mse, int exam) {
        super(name, age, uid);
        this.ass1 = ass1;
        this.ass2 = ass2;
        this.labtest = labtest;
        this.ce = ce;
        this.mse = mse;
        this.exam = exam;
    }

    public int mark() {
        return ass1 + ass2 + labtest + ce + mse + exam;
    }

    public Grade grade() {
        return Grade.fromMark(mark());
    }
}
