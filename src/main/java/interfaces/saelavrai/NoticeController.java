package interfaces.saelavrai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NoticeController {
    @FXML
    private Button button;
    public void Home (ActionEvent actionEvent){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);

    }
}
