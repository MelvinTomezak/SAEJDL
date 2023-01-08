package interfaces.saelavrai;

import javafx.fxml.FXML;

public class NoticeAnglaisController {
    @FXML
    public void Home(){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
    @FXML
    public void Translate(){
        AccueilMain.stackPane.getChildren().get(0).setVisible(true);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
}
