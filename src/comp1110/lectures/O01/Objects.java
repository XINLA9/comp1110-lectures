package comp1110.lectures.O01;

public class Objects {
    public static void main(String[] args) {
        // Create a new instance of Person class
        // declaration = instantiation+ constructor
        Person person1 = new Person("freddy",23);
        Person person2 = new Person("debra",20);
        System.out.println(person1);
        System.out.println(person2);
        System.out.println("Has the name "+person1.getName());
        person1.happyBirthday();
        System.out.println("Freddy after birthday: "+ person1);
    }
}
