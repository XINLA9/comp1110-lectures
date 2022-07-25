package comp1110.lectures.J12;

public class Container<T extends Number> {  // T is a type parameter, with type parameter bounds
    T value;

    public Container(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int something() {
        return value.intValue();
    }

    public <V> String makeStrings(V v) {
        return value.toString() + v.toString();
    }

    public static void main(String[] args) {
        //var cont = new Container<Integer>(40);
        var cont1 = new Container<>(40);
        var cont2 = new Container<>(3.0);
        System.out.println(cont1.getValue());
        System.out.println(cont2.getValue());
        System.out.println(cont1.something());
        System.out.println(cont2.something());

        System.out.println(cont1.makeStrings("hello"));
        System.out.println(cont1.makeStrings(30.0));
        System.out.println(cont2.makeStrings(30.0));
    }
}
