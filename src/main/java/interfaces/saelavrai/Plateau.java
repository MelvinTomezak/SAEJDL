package interfaces.saelavrai;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

import static com.sun.javafx.fxml.expression.Expression.add;


public class Plateau extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Stage stage = new Stage();
        GridPane plateau = new GridPane();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rect = new Rectangle(50, 50);
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        rect.setFill(Color.GREEN);
                    } else {
                        rect.setFill(Color.YELLOW);
                    }
                }
                else if (i % 5 == 0){
                    if (j % 2 == 0) {
                        rect.setFill(Color.RED);
                    } else {
                        rect.setFill(Color.GREEN);
                    }
                }
                else {
                    if (j % 2 == 0) {
                        rect.setFill(Color.YELLOW);
                    } else {
                        rect.setFill(Color.RED);
                    }
                }
                plateau.add(rect, i, j);
            }
        }
        Scene scene = new Scene(plateau, 500,500);
        stage.setScene(scene);
        stage.setTitle("Goose Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}