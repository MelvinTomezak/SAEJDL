package interfaces.saelavrai;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;


public class AccueilController {
    @FXML
    private Button button;

    /**
     * La fonction ONotice est relié au button notice de la page accueil.fxml et elle permet de pouvoir afficher la page notice en appuyant sur le button notice. On va rendre visible cette page et ne plus rendre visible les autres.
     * @param actionEvent
     */
    public void ONotice (ActionEvent actionEvent){
        AccueilMain.stackPane.getChildren().get(0).setVisible(true);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }

    /**
     * La fonction LeaveG va permettre de fermer notre application en appuyant sur le button Leave Game.
     * @param actionEvent
     */
    public void LeaveG (ActionEvent actionEvent){
        Platform.exit();
    }

    /**
     * La fonction Loginn est reliée au button Login de la page d'Accueil qui va permettre d'ouvrir une page internet grâce à un URL (cela sera liée par la suite à notre register pour les joueurs).
     * @param actionEvent
     * @throws IOException
     */
    public void Loginn(ActionEvent actionEvent) throws IOException {
        String url = "https://www.google.com";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    /**
     * La fonction Score est reliée au button Score de la page d'Accueil qui va permettre d'afficher les scores de chaque joueur après une partie.
     * @param actionEvent
     * @throws IOException
     */
    public void Score(ActionEvent actionEvent) throws IOException {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(true);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
    /**
     * La fonction Start est reliée au button Start de la page d'Accueil qui va permettre d'afficher la fenêtre de jeu pour pouvoir lancer le jeu.
     * @param actionEvent
     * @throws IOException
     */

    public void Start(ActionEvent actionEvent) throws Exception {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        //Platform.exit();
        Stage stage = new Stage();
        Plateau plateau = new Plateau();
        plateau.start(stage);
    }
}
