package interfaces.saelavrai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NoticeController {
    @FXML
    private Button button;

    /**
     * La fonction Home est reliée au button Home de la page Notice et elle va permettre de revenir à la page d'Accueil.
     * @param actionEvent
     */
    public void Home (ActionEvent actionEvent){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
    }
}
