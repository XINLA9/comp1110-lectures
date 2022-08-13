package comp1110.X01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JFXHelloWorld extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello");
        var root = new StackPane();
        var scene = new Scene(root,300,300);
        stage.setScene(scene);
        var text = new Text("Hello world");
        text.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,40));
        text.setFill(Color.RED);
        root.getChildren().add(text);
        var r = new Rectangle(250,100);
        r.setOpacity(0.5);

        root.getChildren().add(r);

        stage.show();
    }
}
