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

/**
 * Classe AccueilMain qui hérite de la classe Application et qui permet de gérer les différents écrans de l'application
 * @author Les Avanturiers
 */
public class AccueilMain extends Application {

    /**
     * Attribut qui permet de stocker le stackPane
     */
    @FXML
    public static StackPane stackPane;

    /**
     * Attribut qui permet de stocker le socket
     */
    private static Socket socket;

    /**
     * Constructeur de la classe AccueilMain
     *
     */
    public AccueilMain() throws IOException {

    }

    /**
     * Méthode qui retourne le socket
     *
     * @return Socket
     */
    public static Socket getClient(){
        return socket;
    }

    /**
     * Méthode qui a pour but d'initialiser le Stage et de gérer les différents écrans de l'application
     *
     * @param stage
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

    /**
     * Méthode main qui permet de lancer le jeu
     *
     * @param args
     */
    public static void main(String[] args) {
        Postgresql.connect();
        Serveur.fetchClients();
        launch();
    }


    /**
     * Méthode qui permet d'initialiser le jeu et afficher un message quand un nouveau client se connecte
     *
     * @throws IOException
     */
    public synchronized void init() throws IOException {
        out.println("New client ! ");
    }
}