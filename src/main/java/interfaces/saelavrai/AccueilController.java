package interfaces.saelavrai;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class AccueilController {
    @FXML
    private Button button;
    public void ONotice (ActionEvent actionEvent){
        AccueilMain.stackPane.getChildren().get(0).setVisible(true);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
    }
    public void LeaveG (ActionEvent actionEvent){
        Platform.exit();
    }
    public void Loginn(ActionEvent actionEvent){
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://www.google.fr");
        
    }
}
