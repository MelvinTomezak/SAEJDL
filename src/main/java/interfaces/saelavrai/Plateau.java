package interfaces.saelavrai;

import interfaces.saelavrai.DAO.FetcherOperations;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
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
    public int de;
    public double hauteur = 550;
    public int nbjoueur = 4;

    Joueur[] joueurs = new Joueur[1];
    Circle[] pions = new Circle[nbjoueur];
    Circle[] joueursCouleur;
    Color[] couleur = new Color[4];

    AtomicInteger pos = new AtomicInteger();

    BorderPane leftBorderPane = new BorderPane();
    AtomicInteger[] numberOfSquaresTravelledCircle = new AtomicInteger[nbjoueur];

    public AtomicInteger numberOfMovesCircle1 = new AtomicInteger();

    public TextArea question =new TextArea();
    public TextField answer =new TextField();
    Text text =new Text("Answer");
    Text textquestion =new Text("Question");
    private boolean reponse = false;
    private final Random random = new Random();
    public int colonne = 0;
    public int ligne = 9;

    GridPane plateau = new GridPane();
    BorderPane borderPane = new BorderPane();
    public Rectangle rect;
    @Override
    public void start(Stage stage) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                rect = new Rectangle(50, 50);
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        rect.setFill(Color.GREEN);
                    } else {
                        rect.setFill(Color.YELLOW);
                    }
                } else if (i % 5 == 0) {
                    if (j % 2 == 0) {
                        rect.setFill(Color.RED);
                    } else {
                        rect.setFill(Color.GREEN);
                    }
                } else {
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

        couleur[0] = Color.CYAN;
        couleur[1] = Color.DARKBLUE;
        couleur[2] = Color.BLACK;
        couleur[3] = Color.WHITE;
        nbjoueur = Serveur.conSize;

        joueurs = new Joueur[nbjoueur];
        pions = new Circle[nbjoueur];
        joueursCouleur = new Circle[nbjoueur];

            for (int i = 0; i < nbjoueur; ++i) {
                pions[i] = new Circle();
                pions[i].setRadius(hauteur / 50);
                pions[i].setFill(Color.rgb(0, 0, 0, 0.0));
                DropShadow innerShadow = new DropShadow(3, Color.BLACK);
                innerShadow.setInput(new InnerShadow(2, Color.BLACK));
                pions[i].setEffect(innerShadow);
                pions[i].setTranslateX(15);
                pions[i].setTranslateY(15);
                plateau.add(pions[i], colonne, ligne);
                pions[i].setFill(couleur[i]);

            }

        for (int j = 0; j < nbjoueur; j++) {
            numberOfSquaresTravelledCircle[j] = new AtomicInteger();
        }
            VBox vBox = new VBox();
            Button De = new Button("Lancer le dé");
            vBox.setAlignment(Pos.CENTER_LEFT);
            vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.getChildren().addAll(plateau, De, leftBorderPane);
            borderPane.setCenter(vBox);

            VBox graphContainer = new VBox();
            graphContainer.setAlignment(Pos.CENTER_LEFT);
            graphContainer.setFillWidth(true);
            Button check = new Button("Check");

        De.setOnAction(actionEvent -> {
            System.out.println("C'est au tour du joueur " + pions[playerIndex] + " de lancer le dé ! ");
            LancerDe();
                    question.setEditable(false);
                    answer.setEditable(true);
                    check.setDisable(false);
                    De.setDisable(true);
                    String mode = "";
                    if (getDe() > 4) {
                        mode = "hard";
                        try {
                            FetcherOperations.diff();
                            textquestion.setText("Answer this question to move your circle:");
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (mode.equals(""))
                        if (getDe() <= 2) {
                            mode = "easy";
                            try {
                                FetcherOperations.easy();
                                textquestion.setText("Answer this question to move your circle:");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    if (mode.equals(""))
                        if (getDe() == 3 || getDe() == 4) {
                            mode = "medium";
                            try {
                                FetcherOperations.med();
                                textquestion.setText("Answer this question to move your circle:");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    question.setText(FetcherOperations.question);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please answer the question to continue..", ButtonType.OK);
                    alert.show();
                });
            check.setOnAction(ae -> {
                int flag = 0;
                question.setText(FetcherOperations.question);
                if (answer.getText().equalsIgnoreCase(FetcherOperations.answer)) {
                    flag = 1;
                    reponse = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct Answer!", ButtonType.OK);
                    alert.show();
                }
                question.setText("");
                answer.setText("");
                textquestion.setText("Question:");

                if (flag == 0) {
                    reponse = false;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Wrong Answer!", ButtonType.OK);
                    alert.show();
                }
                if (reponse) {
                    mouvpion();
                    question.setEditable(true);
                    De.setDisable(false);
                }
                if (!reponse){
                    this.playerIndex = (this.playerIndex + 1) % pions.length;
                    question.setEditable(true);
                    De.setDisable(false);
                }
            });

            graphContainer.getChildren().add(textquestion);
            question.setMaxWidth(300);
            question.setMaxHeight(hauteur / 2 - 50);
            graphContainer.getChildren().add(question);
            question.setEditable(false);
            graphContainer.getChildren().add(text);
            answer.setMaxWidth(300);
            answer.setMaxHeight(hauteur / 2 - 50);
            graphContainer.getChildren().add(answer);
            answer.setEditable(false);
            Text a1 = new Text(".");
            graphContainer.getChildren().add(a1);
            check.setMaxWidth(300);
            check.setMaxHeight(hauteur / 2 - 50);
            graphContainer.getChildren().add(check);
            check.setDisable(true);
            leftBorderPane.setCenter(graphContainer);
            Scene scene = new Scene(borderPane, 1000, 700);
            scene.setFill(Color.LIGHTGREEN);
            stage.setScene(scene);
            stage.setTitle("Goose Game");
            stage.show();
        }

        public void LancerDe () {
            this.de = this.random.nextInt(6) + 1;
        }
    public int playerIndex = 0;
    public void mouvpion() {
        AtomicInteger i = new AtomicInteger();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.3), ev -> {
            Circle pion = pions[this.playerIndex];
            if (numberOfMovesCircle1.get() <= this.de - 1) {
                if (numberOfSquaresTravelledCircle[this.playerIndex].get() < 9) {
                    pion.setTranslateX(pion.getTranslateX() + 50);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get() + 1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                }
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 9 && i.get() == 0) {
                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get() + 1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 9 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 19) {
                    pion.setTranslateX(pion.getTranslateX() - 50);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get() + 1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());

                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 19 && i.get() == 0) {
                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get() + 1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                }
                //pattern 2

                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 19 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 29) {
                    pion.setTranslateX(pion.getTranslateX() + 50);


                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());



                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 29 && i.get() == 0) {

                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());



                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 29 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 39) {
                    pion.setTranslateX(pion.getTranslateX() - 50);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);



                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 39 && i.get() == 0) {
                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());


                }
                //pattern 3
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 38 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 49) {
                    pion.setTranslateX(pion.getTranslateX() + 50);


                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 49 && i.get() == 0) {

                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);

                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());



                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 49 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 59) {
                    pion.setTranslateX(pion.getTranslateX() - 50);

                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);


                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 59 && i.get() == 0) {
                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());

                }
                //pattern 4
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 59 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 69) {
                    pion.setTranslateX(pion.getTranslateX() + 50);


                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 69 && i.get() == 0) {

                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());


                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 69 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 79) {
                    pion.setTranslateX(pion.getTranslateX() - 50);

                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);


                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 79 && i.get() == 0) {
                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());


                }
                //pattern 5
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 79 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 89) {
                    pion.setTranslateX(pion.getTranslateX() + 50);

                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 89 && i.get() == 0) {

                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());


                } else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 89 && numberOfSquaresTravelledCircle[this.playerIndex].get() <= 98) {
                    pion.setTranslateX(pion.getTranslateX() - 50);

                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                }
            } else if (numberOfMovesCircle1.get() == this.de) {
                numberOfMovesCircle1.set(0);
                timeline.stop();
                this.playerIndex = (this.playerIndex + 1) % pions.length;  // passage au joueur suivant
            }
            if (numberOfSquaresTravelledCircle[this.playerIndex].get() ==99){
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION, "\t\t\t\t\tGame has ended!\n", ButtonType.OK);
                alert4.show();
            }
        });

        timeline.getKeyFrames().add(kf);
        System.out.println("Le de a fait " + getDe());
        timeline.play();
    }

    public int getDe() {
        return de;
    }

    public static void main(String[] args) {
        launch();
    }
}