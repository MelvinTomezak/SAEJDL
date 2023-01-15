package interfaces.saelavrai;
import javafx.fxml.FXML;

public class NoticeController {
    @FXML
    /**
     * La fonction Home est reliée au button Home de la page Notice et elle va permettre de revenir à la page d'Accueil.
     */
    public void Home (){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
    @FXML
    public void Translate() {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(true);
    }
}
