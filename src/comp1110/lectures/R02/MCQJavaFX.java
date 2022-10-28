package comp1110.lectures.R02;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Multiple choice question on JavaFX from S1 2020 final exam.

public class MCQJavaFX extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        System.out.println("A");
        scene.setOnKeyTyped(event -> {
            System.out.println(event.getCharacter());
            if (event.getCharacter().equals("Q"))
                Platform.exit();
        });
        System.out.println("B");
        stage.show();
        // System.out.println("C");
    }
}
