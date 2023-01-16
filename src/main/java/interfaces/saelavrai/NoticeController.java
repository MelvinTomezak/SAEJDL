package interfaces.saelavrai;
import javafx.fxml.FXML;

/**
 * Cette classe est le contrôleur de la vue de la notice.
 *
 * @author Nom de l'auteur
 */
public class NoticeController {
    /**
     * Cette fonction permet de retourner à la page d'accueil.
     */
    @FXML
    public void Home (){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
    /**
     * Cette fonction permet de traduire le texte en anglais.
     */
    @FXML
    public void Translate() {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(true);
    }
}