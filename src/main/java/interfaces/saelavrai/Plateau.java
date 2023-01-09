package interfaces.saelavrai;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class Plateau extends Application {
    private int de;
    private Random random = new Random();
    private boolean FinTourJoueur1;
    private boolean FinTourJoueur2;
    private boolean FinTourJoueur3;
    private boolean FinTourJoueur4;
    private int ligne = 9;
    private int colonne = 0;
    Circle pion1 = new Circle(10, Color.PURPLE);
    Circle pion2 = new Circle(10, Color.BLACK);
    Circle pion3 = new Circle(10, Color.CYAN);
    Circle pion4 = new Circle(10, Color.BLUE);
    GridPane plateau = new GridPane();
    Rectangle rect;
    @Override
    public void start(Stage stage) {
        Button De = new Button("Lancer le dé");
        De.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LancerDe();
                mouvementPion();
            }
        });
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                rect = new Rectangle(50,50);
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        rect.setFill(Color.GREEN);
                    } else {
                        rect.setFill(Color.YELLOW);
                    }
                }
                else if (i % 5 == 0){
                    if (j % 2 == 0) {
                        rect.setFill(Color.RED);
                    } else {
                        rect.setFill(Color.GREEN);
                    }
                }
                else {
                    if (j % 2 == 0) {
                        rect.setFill(Color.YELLOW);
                    } else {
                        rect.setFill(Color.RED);
                    }
                }
                plateau.add(rect, i, j);
                plateau.setAlignment(Pos.CENTER);
            }
        }
        plateau.add(pion1,colonne,ligne);
        plateau.add(pion2,colonne,ligne);
        plateau.add(pion3,colonne,ligne);
        plateau.add(pion4,colonne,ligne);
        pion1.setTranslateX(15);
        pion1.setTranslateY(15);
        pion2.setTranslateX(15);
        pion2.setTranslateY(-15);
        pion3.setTranslateX(0);
        pion3.setTranslateY(0);
        pion4.setTranslateX(30);
        pion4.setTranslateY(0);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().addAll(plateau, De);
        Scene scene = new Scene(vBox, 800,600);
        scene.setFill(Color.LIGHTGREEN);
        stage.setScene(scene);
        stage.setTitle("Goose Game");
        stage.show();
    }

    public int LancerDe (){
        this.de = this.random.nextInt(6) + 1;
        return this.de;
    }
    public void mouvementPion(){
        while(pion1.getTranslateX() < 515){
            System.out.println("Vous avez fait " + getDe());
            pion1.setTranslateX(pion1.getTranslateX() + (50*getDe()));
            FinTourJoueur1 = true;
            if (FinTourJoueur1 == true){
                System.out.println("Fin tour joueur 1");
                break;
            }
            if (pion1.getTranslateX() >= 515){
                pion1.setTranslateY(pion1.getTranslateY() - 50);
                pion1.setTranslateX(pion1.getTranslateX() - (50*getDe()));
                System.out.println("Ligne 1 fini");
            }
        }
    }

    public int getDe() {
        return de;
    }

    public static void main(String[] args) {
        launch();
    }
}