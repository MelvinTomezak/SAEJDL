package interfaces.saelavrai;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class AccueilMain extends Application {
  /*  private static Socket socket;
    public AccueilMain() throws IOException {
        socket = new Socket("127.0.0.1",10007);
    }
    public static Socket getClient(){
        return socket;
    }*/
    @FXML
    static StackPane stackPane;

    /**
     * La fonction start prend stage en paramètre, elle permet de charger nos différentes pages FXML dans des AnchorsPanes pour qu'on puisse gérer l'affichage par la suite.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        stackPane = new StackPane();
        AnchorPane Accueil = null;
        try {
           Accueil= FXMLLoader. load(getClass().getResource("accueil.fxml"));
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
        AnchorPane Score = null;
        try {
            Score = FXMLLoader.load(getClass().getResource("score.fxml"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        stackPane.getChildren().addAll(Notice,Accueil,Score);
        Notice.setVisible(false);
        Accueil.setVisible(true);
        Score.setVisible(false);
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