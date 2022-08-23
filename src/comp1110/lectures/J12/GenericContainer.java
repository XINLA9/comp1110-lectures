package comp1110.J12;

public class GenericContainer<T extends Number> { //T is a type parameter

    private T value;

    public GenericContainer(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int toInt(){
        return value.intValue();
    }

    public <V> String makeStrings(V value){
        return this.value.toString()+" "+value;
    }

    public static void main(String[] args) {

        var c = new GenericContainer<>(30);
        var c2 = new GenericContainer<>(2.3);
        var c3 = new GenericContainer<Float>(4.3f);
        GenericContainer<Double> c4 = new GenericContainer<>(3.4);
        System.out.println(c4.toInt());
        System.out.println(c4.<Integer>makeStrings(3));

    }
}
