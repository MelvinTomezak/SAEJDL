package test;

import static org.junit.jupiter.api.Assertions.*;

import interfaces.saelavrai.AccueilController;
import interfaces.saelavrai.AccueilMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class TestAccueil {
    void lanceMain(){
        AccueilMain.launch();
    }
    FXMLLoader loader = new FXMLLoader(getClass().getResource("accueil.fxml"));
    @Test
    void testONotice() {
        lanceMain();
        AccueilMain.stackPane.getChildren().get(0).setVisible(true);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(true);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);

        AccueilController accueilController = new AccueilController();
        accueilController.ONotice();

        assertFalse(AccueilMain.stackPane.getChildren().get(0).isVisible());
        assertFalse(AccueilMain.stackPane.getChildren().get(1).isVisible());
        assertFalse(AccueilMain.stackPane.getChildren().get(2).isVisible());
        assertTrue(AccueilMain.stackPane.getChildren().get(3).isVisible());
    }
}