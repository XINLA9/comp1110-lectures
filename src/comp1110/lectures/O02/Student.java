package comp1110.lectures.O02;

import comp1110.lectures.O01.Person;

public class Student extends Person {
    private String uid;

    Student(String name, int age, String uid) {
        super(name, age);
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return super.toString() + " is a student with UID " + uid;
    }
}
