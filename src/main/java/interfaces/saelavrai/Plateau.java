package interfaces.saelavrai;

import interfaces.saelavrai.DAO.FetcherOperations;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Plateau extends Application {
    private int de;
    double largeur = 1000, hauteur = 550;
    AtomicInteger pos = new AtomicInteger();

    BorderPane leftBorderPane = new BorderPane();
    AtomicInteger numberOfSquaresTravelledCircle1 = new AtomicInteger(pos.get());

    AtomicInteger numberOfMovesCircle1 = new AtomicInteger();

    TextArea qs=new TextArea(); //Créer un nouveau champ pour la question
    TextField an=new TextField(); //Créer un nouveau champ pour la réponse
    Text a=new Text("Answer"); //Créer un nouveau texte intitulé "Answer"
    Text q=new Text("Question");
    private boolean reponse = false;
    private Random random = new Random();
    private int ligne = 9;
    private int colonne = 0;
    static Circle pion1 = new Circle(10, Color.PURPLE);
    Circle pion2 = new Circle(10, Color.BLACK);
    Circle pion3 = new Circle(10, Color.CYAN);
    Circle pion4 = new Circle(10, Color.BLUE);
    GridPane plateau = new GridPane();
    BorderPane borderPane = new BorderPane();
    Rectangle rect;
    @Override
    public void start(Stage stage) {

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
        Button De = new Button("Lancer le dé");
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().addAll(plateau, De, leftBorderPane);
        borderPane.setCenter(vBox);

        VBox graphContainer = new VBox();
        graphContainer.setAlignment(Pos.CENTER_LEFT);
        graphContainer.setFillWidth(true);
        Button check=new Button("Check");


        De.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LancerDe();
                if (reponse == true) {
                    mouvpiont();
                }


                qs.setEditable(true);
                an.setEditable(true);
                check.setDisable(false);
                String mode="";

                if(getDe() >4){
                    mode="hard";
                    try {
                        FetcherOperations.diff();
                        q.setText("Answer this question to move your circle:");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(mode.equals(""))
                    if(getDe()<=2){
                        mode="easy";
                        try {
                            FetcherOperations.easy();
                            q.setText("Answer this question to move your circle:");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                if(mode.equals(""))
                    if(getDe()>2 && getDe()<=4){
                        mode="medium";
                        try {
                            FetcherOperations.med();
                            q.setText("Answer this question to move your circle:");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                qs.setText(FetcherOperations.question);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please answer the question to continue..", ButtonType.OK);
                alert.show();
            }
        });

        check.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                int flag = 0;
                qs.setText(FetcherOperations.question);
                if (an.getText().equalsIgnoreCase(FetcherOperations.answer)) {
                    flag = 1;
                    reponse = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct Answer!", ButtonType.OK);
                    alert.show();
                }

                qs.setText("");
                an.setText("");
                q.setText("Question:");
                //les champs de question et de réponse sur non modifiables
                qs.setEditable(false);
                an.setEditable(false);
                check.setDisable(true);

// si la réponse donnée est incorrecte, affiche une alerte
                if (flag == 0) {
                    reponse = false;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Wrong Answer!", ButtonType.OK);
                    alert.show();
                }
            } });


        //Ajout des éléments graphiques
        graphContainer.getChildren().add(q); //Ajoute le texte q au graphContainer
        qs.setMaxWidth(300); //Définit la largeur maximale à 300
        qs.setMaxHeight(hauteur / 2 - 50); //Définit la hauteur maximale à la hauteur divisée par 2 et moins 50
        graphContainer.getChildren().add(qs); //Ajoute le TextField qs au graphContainer
        qs.setEditable(false); // Rend le TextField qs non éditable

        graphContainer.getChildren().add(a); //Ajoute le texte a au graphContainer

        an.setMaxWidth(300); //Définit la largeur maximale à 300
        an.setMaxHeight(hauteur / 2 - 50); //Définit la hauteur maximale à la hauteur divisée par 2 et moins 50
        graphContainer.getChildren().add(an); //Ajoute le TextField an au graphContainer
        an.setEditable(false); //Rend le TextField an non éditable

        Text a1=new Text("."); //Création d'un nouveau Text
        graphContainer.getChildren().add(a1); //Ajoute le Text a1 au graphContainer

        check.setMaxWidth(300); //Définit la largeur maximale à 300
        check.setMaxHeight(hauteur / 2 - 50); //Définit la hauteur maximale à la hauteur divisée par 2 et moins 50
        graphContainer.getChildren().add(check); //Ajout du TextField check au graphContainer
        check.setDisable(true); //Rend le TextField check non éditable
        leftBorderPane.setCenter(graphContainer);
        Scene scene = new Scene(borderPane, 1000,700);
        scene.setFill(Color.LIGHTGREEN);
        stage.setScene(scene);
        stage.setTitle("Goose Game");
        stage.show();

    }

    public  Circle getPion1() {
        return pion1;
    }

    public static void setPion1(Circle pion1) {
        Plateau.pion1 = pion1;
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