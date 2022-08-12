package comp1110.lectures.O03;

public class Interfaces {
    public static void main(String[] args) {
        var snake = new BrownSnake();
        var gas = new HydrogenCyanide();
        var food = new Chocolate();

        Toxic toxicSnake = snake;
        var a = new Toxic[]{snake, gas, food};
        for (var v : a) {
            System.out.println(v + " " + v.isLethalToHumans());
        }

    }
}
