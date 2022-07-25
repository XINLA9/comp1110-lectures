package comp1110.lectures.J05;

import java.util.Random;

public class ControlFlowSwitch {
    public static void main(String[] args) {
        // Old style switch statement (avoid using)
        int c = 1;
        switch (c) {
            case 0:
                System.out.println("c is 0");
                break;
            case 1:
                System.out.println("c is 1");
                break;
            case 2:
                System.out.println("c is 2");
                break;
            default:
                System.out.println("c is something else");
        }

        // New style switch statement
        Random rand = new Random();
        c = rand.nextInt();
        switch (c) {
            case 0 -> System.out.println("c is 0");
            case 1 -> {
                System.out.println("c is 1");
                System.out.println("and c is one");
            }
            case 2 -> System.out.println("c is 2");
            default -> System.out.println("c is something else");
        }

        // New style switch "expression"
        String month = "Jul";
        String season = switch (month) {
            case "Dec", "Jan", "Feb" -> "Summer";
            case "Mar", "Apr", "May" -> "Autumn";
            case "Jun", "Jul", "Aug" -> {
                System.out.println("My birthday is in one of these months");
                yield "Winter";
            }
            case "Sep", "Oct", "Nov" -> "Spring";
            default -> null;
        };
        System.out.println(month + " is in the season " + season);
    }
}
