package comp1110.lectures.O02;

import comp1110.lectures.O01.Person;

public class Inheritance {
    public static void main(String[] args) {
        // Type inference
        var mary = new Student("Mary", 20, "u1234567");
        // Same as:
        // Student mary = new Student("Mary", 20, "u1234567");
        Student gary = new Student("Gary", 22, "u1234568");
        COMP1110Student fred = new COMP1110Student("Gary", 22, "u1234568",
                4, 20, 3, 5, 3, 20);
        COMP1110Student sarah = new COMP1110Student("Sarah", 22, "u1234568",
                4, 20, 3, 5, 3, 40);
        var ted = new Person("Mary", 30);

        Person[] people = new Person[]{mary, gary, fred, sarah, ted};
        for (var person : people) {
            person.happyBirthday();
            System.out.println(person);
            // Only for students
            //System.out.println(person.getUid());
        }

        System.out.println(fred.mark() + " " + fred.grade());
        System.out.println(sarah.mark() + " " + sarah.grade());
    }
}
