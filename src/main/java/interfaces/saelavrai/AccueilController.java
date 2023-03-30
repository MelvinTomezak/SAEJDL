package interfaces.saelavrai;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static interfaces.saelavrai.AccueilMain.stackPane;

/**
 * Classe controller pour l'accueil qui fait appel à des méthodes pour naviguer entre les différentes vues.
 * @author Les Avanturiers
 */
public class AccueilController {
    /**
     * Méthode qui fait appel à la vue Notice
     */
    @FXML
    public void ONotice (){
        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(false);
        stackPane.getChildren().get(3).setVisible(true);
        stackPane.getChildren().get(4).setVisible(false);
        stackPane.getChildren().get(5).setVisible(false);
    }

    /**
     * Méthode qui quitte le jeu
     */
    public void LeaveG (){
        Platform.exit();
    }

    /**
     * Méthode qui ouvre le lien du site web
     */
    public void Loginn() throws IOException {
        String url = "http://adminsae.alwaysdata.net/";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    /**
     * Méthode qui fait appel à la vue Score
     */
    public void Score(){
        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(true);
        stackPane.getChildren().get(3).setVisible(false);
        stackPane.getChildren().get(4).setVisible(false);
        stackPane.getChildren().get(5).setVisible(false);

    }

    public void Multi(){
        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(false);
        stackPane.getChildren().get(3).setVisible(false);
        stackPane.getChildren().get(4).setVisible(false);
        stackPane.getChildren().get(5).setVisible(true);
    }



//        private Button connexionButton;

//    @FXML
//    public void initialize() {
//        // Ajouter un événement de clic au bouton "Connexion"
//        connexionButton.setOnAction(event -> {
//            // Charger la page de connexion depuis le fichier FXML
//            AnchorPane Connexion = null;
//            try {
//                Connexion = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // Remplacer la page courante par la page de connexion
//            StackPane stackPane = (StackPane) connexionButton.getScene().getRoot();
//            stackPane.getChildren().set(0, Connexion);
//        });
//
//    }


    /**
     * Méthode qui ouvre le lien du dépôt GitHub
     */
    public void GitHub() throws IOException {
        String url = "https://github.com/MelvinTomezak/SAEJDL";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }


    public void Start() {
        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(false);
        stackPane.getChildren().get(3).setVisible(false);
        stackPane.getChildren().get(4).setVisible(true);
        stackPane.getChildren().get(5).setVisible(false);
    }
    }
