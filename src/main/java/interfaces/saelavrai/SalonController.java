package interfaces.saelavrai;


import javafx.fxml.FXML;

public class SalonController {
    @FXML
    public void CRoom(){
            ServeurGui serveurGui = new ServeurGui();
    }
    public void JRoom() {
            ClientGui clientGui = new ClientGui();
        }

    @FXML
    public void Home (){
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
        AccueilMain.stackPane.getChildren().get(4).setVisible(false);
        AccueilMain.stackPane.getChildren().get(5).setVisible(false);
    }
}
