package comp1110.lectures.J08;

public class Nested {
    public static void main(String[] args) {
        var room1 = new Room("COP T", "Carpet");
        System.out.println(room1);
        var room2 = new RoomStaticNested("Somewhere T", "Tiles");
        System.out.println(room2);
        var room3 = new RoomInstanceNested("Home", "Concrete");
        System.out.println(room3);
    }
}
