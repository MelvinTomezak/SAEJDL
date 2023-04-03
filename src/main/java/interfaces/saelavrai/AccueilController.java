package interfaces.saelavrai;
import javafx.application.Platform;
import javafx.fxml.FXML;

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
        String url = "https://adminsae.alwaysdata.net/signup";
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
