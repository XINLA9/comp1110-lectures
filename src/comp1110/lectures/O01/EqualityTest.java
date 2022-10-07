package comp1110.lectures.O01;

public class EqualityTest {

    public static void main(String[] args) {
	//String p1 = "John";
	// String p2 = "John";
	//String p2 = new String("John II");

	Person p1 = new Person("John", 41);
	Person p2 = new Person("John", 14);

	// == compares references (object pointers)
	System.out.println("p1 == p2? " + (p1 == p2));
	// a.equals(b) uses a's type's definition of equality
	// (default inherited from Object, which is just pointer
	// comparison)
	System.out.println("p1.equals(p2)? " + p1.equals(p2));
	// default hashCode method is inherited from Object;
	// subclasses can override
	System.out.println("p1.hashCode() == " + p1.hashCode());
	System.out.println("p2.hashCode() == " + p2.hashCode());
    }

}
