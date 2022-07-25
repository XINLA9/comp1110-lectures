package comp1110.lectures.C04;

import java.io.IOException;

public class StreamStdIO {
    public static void main(String[] args) {
        try {
            while (true) {
                var b = System.in.read();
                if (b == -1) { // ctrl-D to simulate
                    System.out.println("EOF reached.");
                    break;
                } else {
                    System.out.println(String.format("%02X", b));
                    System.out.write(b);
                }
                System.out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
