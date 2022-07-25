package comp1110.lectures.O01;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
        //return name;
    }

    public void happyBirthday() {
        this.age += 1;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name +
                ", age='" + age + '\'' +
                '}';
    }
}
