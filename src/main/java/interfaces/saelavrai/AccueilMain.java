package interfaces.saelavrai;
import interfaces.saelavrai.DAO.Postgresql;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;

import static java.lang.System.out;

public class AccueilMain extends Application {
    @FXML
    static StackPane stackPane;
    private static Socket socket;

    public AccueilMain() throws IOException {

    }
    public static Socket getClient(){
        return socket;
    }


        /**
         * La fonction start prend stage en paramètre, elle permet de charger nos différentes pages FXML dans des AnchorsPanes pour qu'on puisse gérer l'affichage par la suite.
         * @param stage
         * @throws IOException
         */
    @Override
    public void start(Stage stage) {
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
        AnchorPane NoticeAnglais = null;
        try {
            NoticeAnglais = FXMLLoader.load(getClass().getResource("noticeAnglais.fxml"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        stackPane.getChildren().addAll(Notice,Accueil,Score,NoticeAnglais);
        Notice.setVisible(false);
        Accueil.setVisible(true);
        Score.setVisible(false);
        NoticeAnglais.setVisible(false);
        Scene scene = new Scene(stackPane);
        stage.setTitle("Goose Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        Postgresql.connect();
        Serveur.fetchClients();
        launch();
    }

    public synchronized void init() throws IOException {

        out.println("New client ! ");
        //Client client = new Client("127.0.0.1", 10007);
        //client.init();
        //Serveur serveur = new Serveur(10007);
        //serveur.init();


    }

}