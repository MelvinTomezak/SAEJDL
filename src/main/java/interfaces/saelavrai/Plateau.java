package interfaces.saelavrai;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Plateau extends Application {
    private int de;
     AtomicInteger pos = new AtomicInteger();


     AtomicInteger numberOfSquaresTravelledCircle1 = new AtomicInteger(pos.get());

    AtomicInteger numberOfMovesCircle1 = new AtomicInteger();

    private Random random = new Random();
    private int ligne = 9;
    private int colonne = 0;
    static Circle pion1 = new Circle(10, Color.PURPLE);
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
                mouvpiont();
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

    public void LancerDe(){
        this.de = this.random.nextInt(6)+1;
    }
    public void mouvpiont() {
        AtomicInteger i = new AtomicInteger();
        i.set(0);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), ev -> {

            // while(numberOfSquaresTravelledCircle1.get() < 23) {
            if (numberOfSquaresTravelledCircle1.get() == 99) {
                System.out.println("joueur 1 a gagné");
                timeline.stop();
            }
                if (numberOfMovesCircle1.get() <= this.de - 1) {

/////pattern 1
                    if (numberOfSquaresTravelledCircle1.get() <= 9) {
                        pion1.setTranslateX(pion1.getTranslateX() + 50);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    } if (numberOfSquaresTravelledCircle1.get() == 9 && i.get() == 0) {
                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    } else if (numberOfSquaresTravelledCircle1.get() >= 10 && numberOfSquaresTravelledCircle1.get() < 19) {
                        pion1.setTranslateX(pion1.getTranslateX() - 50);


                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    } else if (numberOfSquaresTravelledCircle1.get() == 19 && i.get() == 0) {
                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    }
                    /////pattern 2

                    else if (numberOfSquaresTravelledCircle1.get() >= 19 && numberOfSquaresTravelledCircle1.get() < 29) {
                        pion1.setTranslateX(pion1.getTranslateX() + 50);


                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);


                    } else if (numberOfSquaresTravelledCircle1.get() == 29 && i.get() == 0) {

                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());



                    } else if (numberOfSquaresTravelledCircle1.get() >= 29 && numberOfSquaresTravelledCircle1.get() < 39) {
                        pion1.setTranslateX(pion1.getTranslateX() - 50);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);



                    } else if (numberOfSquaresTravelledCircle1.get() == 39 && i.get() == 0) {
                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    }
                    ///pattern 3
                    else if (numberOfSquaresTravelledCircle1.get() >= 38 && numberOfSquaresTravelledCircle1.get() < 49) {
                        pion1.setTranslateX(pion1.getTranslateX() + 50);


                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(i);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                    } else if (numberOfSquaresTravelledCircle1.get() == 49 && i.get() == 0) {

                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);

                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());



                    } else if (numberOfSquaresTravelledCircle1.get() >= 49 && numberOfSquaresTravelledCircle1.get() < 59) {
                        pion1.setTranslateX(pion1.getTranslateX() - 50);

                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);


                    } else if (numberOfSquaresTravelledCircle1.get() == 59 && i.get() == 0) {
                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());

                    }
                    ////pattern 4
                    else if (numberOfSquaresTravelledCircle1.get() >= 59 && numberOfSquaresTravelledCircle1.get() < 69) {
                        pion1.setTranslateX(pion1.getTranslateX() + 50);


                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                    } else if (numberOfSquaresTravelledCircle1.get() == 69 && i.get() == 0) {

                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    } else if (numberOfSquaresTravelledCircle1.get() >= 69 && numberOfSquaresTravelledCircle1.get() < 79) {
                        pion1.setTranslateX(pion1.getTranslateX() - 50);

                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);


                    } else if (numberOfSquaresTravelledCircle1.get() == 79 && i.get() == 0) {
                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    }
                    ////pattern 5
                    else if (numberOfSquaresTravelledCircle1.get() >= 79 && numberOfSquaresTravelledCircle1.get() < 89) {
                        pion1.setTranslateX(pion1.getTranslateX() + 50);

                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                    } else if (numberOfSquaresTravelledCircle1.get() == 89 && i.get() == 0) {

                        pion1.setTranslateY(pion1.getTranslateY() - 50);
                        i.set(1);
                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                        System.out.println(numberOfSquaresTravelledCircle1.get());


                    } else if (numberOfSquaresTravelledCircle1.get() >= 89 && numberOfSquaresTravelledCircle1.get() <= 98) {
                        pion1.setTranslateX(pion1.getTranslateX() - 50);

                        pos.set(pos.get() + 1);
                        numberOfSquaresTravelledCircle1.set(pos.get());
                        i.set(0);
                        System.out.println(numberOfSquaresTravelledCircle1.get());
                        numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                    }

                } else if (numberOfMovesCircle1.get()== this.de) {
                    numberOfMovesCircle1.set(0);
                    timeline.stop();

                }


        });
        timeline.getKeyFrames().add(kf);
        System.out.println("Le de a fait "+ getDe());
        timeline.play();

    }


    public int getDe() {
        return de;
    }

    public static void main(String[] args) {
        launch();
    }
}