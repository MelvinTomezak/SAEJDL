package interfaces.saelavrai;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
public class AccueilMain extends Application {
    @FXML
    static StackPane stackPane;
    @Override
    public void start(Stage stage) throws IOException {
        stackPane = new StackPane();
        AnchorPane Accueil = null;
        try {
           Accueil= FXMLLoader.load(getClass().getResource("accueil.fxml"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        AnchorPane Notice = null;
        try {
            Notice = FXMLLoader.load(getClass().getResource("notice.fxml"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        stackPane.getChildren().addAll(Notice,Accueil);
        Notice.setVisible(false);
        Accueil.setVisible(true);

        Scene scene = new Scene(stackPane);
        stage.setTitle("Goose Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}