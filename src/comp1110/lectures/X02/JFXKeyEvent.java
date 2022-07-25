package comp1110.lectures.X02;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JFXKeyEvent extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello");
        var root = new StackPane();
        var scene = new Scene(root, 300, 300);
        stage.setScene(scene);

        var text = new Text("Hello world!");
        text.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 40));
        text.setFill(Color.RED);

        root.getChildren().add(text);

        scene.setOnKeyTyped(event -> {
            var c = event.getCharacter();
            if (c.equals("q")) {
                Platform.exit();
                // System.exit(0);
            }
            text.setText(c);
        });

        stage.show();
    }
}
