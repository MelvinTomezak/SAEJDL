package interfaces.saelavrai;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.Random;

public class PlateauController {
    @FXML
    private Button button;
    private int dee;
    public void lancede(ActionEvent actionEvent) {
        dee = (int)(Math.random()*6);
        if (dee == 0) {
            System.out.println("Vous avez fait 1");
        }if (dee == 1) {
            System.out.println("Vous avez fait 2");
        }if (dee == 2) {
            System.out.println("Vous avez fait 3");
        }if (dee == 3) {
            System.out.println("Vous avez fait 4");
        }if (dee == 4) {
            System.out.println("Vous avez fait 5");
        }if (dee == 5) {
            System.out.println("Vous avez fait 6");}

    }

}
