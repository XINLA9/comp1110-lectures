package comp1110.lectures.C04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StreamFile {
    public static void main(String[] args) throws IOException {
        // try with resource
        try (var stream = new FileInputStream("assets/dictionary.txt")) {
            for (var i = 0; i < 50; i++) {
                var b = stream.read();
                System.out.write(b);
            }
            System.out.flush();
            System.out.println("\nAnother 10:");
            var bytes = stream.readNBytes(10);
            System.out.write(bytes);
            System.out.flush();
        }
        //finally {
        //    stream.close();
        //}
    }
}
