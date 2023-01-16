package interfaces.saelavrai;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;
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
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(true);
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
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(true);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }

    /**
     * Méthode qui démarre le jeu
     */
    public void Start()  {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
        Stage stage = new Stage();
        Plateau plateau = new Plateau();
        plateau.start(stage);
    }

    /**
     * Méthode qui ouvre le lien du dépôt GitHub
     */
    public void GitHub() throws IOException {
        String url = "https://github.com/MelvinTomezak/SAEJDL";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }
}