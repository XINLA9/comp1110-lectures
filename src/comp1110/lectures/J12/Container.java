package comp1110.J12;

public class Container {

    private Integer value;

    public Container(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static void main(String[] args) {
        var c = new Container(30);
        System.out.println(c.getValue());
    }
}
