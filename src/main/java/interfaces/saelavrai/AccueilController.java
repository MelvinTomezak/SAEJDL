package interfaces.saelavrai;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;
public class AccueilController {
    @FXML

    /**
     * La fonction ONotice est relié au button notice de la page accueil.fxml et elle permet de pouvoir afficher la page notice en appuyant sur le button notice. On va rendre visible cette page et ne plus rendre visible les autres.
     */
    public void ONotice (){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(true);
    }

    /**
     * La fonction LeaveG va permettre de fermer notre application en appuyant sur le button Leave Game.
     */
    public void LeaveG (){
        Platform.exit();
    }
    /**
     * La fonction Loginn est reliée au button Login de la page d'Accueil qui va permettre d'ouvrir une page internet grâce à un URL (cela sera liée par la suite à notre register pour les joueurs).
     * @throws IOException
     */
    public void Loginn() throws IOException {
        String url = "http://adminsae.alwaysdata.net/";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

    /**
     * La fonction Score est reliée au button Score de la page d'Accueil qui va permettre d'afficher les scores de chaque joueur après une partie.

     */
    public void Score(){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(true);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
    /**
     * La fonction Start est reliée au button Start de la page d'Accueil qui va permettre d'afficher la fenêtre de jeu pour pouvoir lancer le jeu.
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

    public void GitHub() throws IOException {
        String url = "https://github.com/MelvinTomezak/SAEJDL";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }
}
