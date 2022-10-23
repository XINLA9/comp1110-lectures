package comp1110.lectures.O01;

public class Person implements Comparable<Person> {
    private int age;
    private String name;
	
    public Person(String name, int age) {
	this.age = age;
	this.name = name;
    }

    public String getName() {
        return name;
    }

    public void happyBirthday() {
        this.age += 1;
    }

    @Override
    public String toString() {
        return "Person{" + "age=" + age + ", name=" + name + "}";
    }



    @Override
    public boolean equals(Object other) {
	try {
	    Person p = (Person)other;
	    return (this.name == p.name) && (this.age == p.age);
	}
	catch (ClassCastException cce) {
	    return false; // not same or subtype, so can't be equal
	}
    }

    @Override
    public int hashCode() {
	return name.hashCode() + age;
    }

    @Override
    public int compareTo(Person other) {
    	int c1 = name.compareTo(other.name);
	    if (c1 != 0) return c1;
	    return age - other.age;
    }
    
}
