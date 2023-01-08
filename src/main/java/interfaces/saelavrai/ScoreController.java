package interfaces.saelavrai;
import javafx.fxml.FXML;
public class ScoreController {
    @FXML
    /**
     * La fonction Home est reliée au button Home de la page score et elle permet de revenir à la page d'Accueil.
     */
    public void Home () {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
}
