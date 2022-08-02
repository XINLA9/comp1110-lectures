package O01;

public class Person extends Object { //this happens automatically
    private  int age;
    private String name;

    public String getName(){
        return this.name;
        //return name;
    }

    public void happyBirthday(){
        this.age += 1;
    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

}
