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
import java.net.URL;


public class AccueilController {
    @FXML
    private Button button;
    public void ONotice (ActionEvent actionEvent){
        AccueilMain.stackPane.getChildren().get(0).setVisible(true);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
    public void LeaveG (ActionEvent actionEvent){
        Platform.exit();
    }
    public void Loginn(ActionEvent actionEvent) throws IOException {
        String url = "https://www.google.com";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }
    public void Score(ActionEvent actionEvent) throws IOException {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(true);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
    }
    public void Start(ActionEvent actionEvent) throws IOException {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(false);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(true);
    }
}
