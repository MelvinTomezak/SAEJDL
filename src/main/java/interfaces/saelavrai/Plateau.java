package interfaces.saelavrai;

import interfaces.saelavrai.DAO.Databaseutils;
import interfaces.saelavrai.DAO.RecuperationBD;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import static interfaces.saelavrai.LoginController.Connections;
/**
 * Classe permet d'afficher le plateau de jeu ainsi que les pions et leurs déplacements.
 * @author Les Avanturiers
 */
public class Plateau extends Application {
    public int de;
    public double hauteur = 550;
    public static int nbjoueur = 4;
    Joueur[] joueurs = new Joueur[1];
    Circle[] pions = new Circle[nbjoueur];
    Circle[] joueursCouleur;
    Color[] couleur = new Color[4];
    LeDe leDe = new LeDe(50, 50);
    AtomicInteger pos = new AtomicInteger();
    BorderPane leftBorderPane = new BorderPane();
    static AtomicInteger[] numberOfSquaresTravelledCircle = new AtomicInteger[nbjoueur];
    static public AtomicInteger numberOfMovesCircle1 = new AtomicInteger();

    public TextArea question = new TextArea();
    public TextField answer = new TextField();
    Text text = new Text("Answer");
    Text textquestion = new Text("Question");

    public TextField AffichageJoeur = new TextField();
    private boolean reponse = false;
    private final Random random = new Random();
    public int colonne = 0;
    public int ligne = 9;
    private Button check;

    GridPane plateau = new GridPane();
    BorderPane borderPane = new BorderPane();
    public Rectangle rect;
    int points = 0;

    StringProperty labelValue = new SimpleStringProperty();
    private static final int TIMER_DURATION = 20; // Durée en secondes du timer
    private Timer timer;
    private int remainingTime = TIMER_DURATION;
    private ProgressBar timerProgressBar;
    private boolean timerIsRunning = false;
    private Button De;

    public Plateau() {
        De = new Button();
    }

    @Override
/**
 * La fonction start permet de créer le plateau, les pions ainsi que les zone où seront afficher les questions et où les réponses seront saisies.
 * Il y a aussi l'appelle des questions qui seront afficher et la création de la stage.
 */
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
        nbjoueur = LoginController.conSize;

        joueurs = new Joueur[nbjoueur];
        pions = new Circle[nbjoueur];
        joueursCouleur = new Circle[nbjoueur];

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);

        // Créer la barre de progression du timer
        timerProgressBar = new ProgressBar(1);
        timerProgressBar.setPrefWidth(200);
        timerProgressBar.setProgress(1.0);
        gridPane.add(timerProgressBar, 0, 0, 2, 1);

        for (int i = 0; i < nbjoueur; ++i) {
            Circle circle = new Circle(hauteur / 65);
            circle.setFill(couleur[i]);

            Text text = new Text();
            text.setText("Nom joueur : " + Connections[i]);

            // Créer le Label pour afficher la valeur de points
            Label labelPoints = new Label("Points : 0");

            gridPane.add(text, 0, i + 1);
            gridPane.add(circle, 1, i + 1);
            gridPane.add(labelPoints, 2, i + 1);

            // Mettre à jour la valeur de points chaque fois qu'un joueur répond correctement à une question
            if (answer.getText().equalsIgnoreCase(RecuperationBD.answer)) {
                int currentPoints = Integer.parseInt(labelPoints.getText().split(": ")[1]);
                currentPoints += 10; // ou une autre valeur si vous souhaitez que la réponse correcte rapporte plus de points
                labelPoints.setText("Points : " + currentPoints);

                // Insérer les scores dans la base de données
                try {
                    Connection conn = Databaseutils.connect();
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO scorespseudo (emailuser, score, date_maj) VALUES (?, ?, NOW())");
                    stmt.setString(1, Connections[i]);
                    stmt.setInt(2, currentPoints);
                    stmt.executeUpdate();
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }

        gridPane.setAlignment(Pos.TOP_LEFT);

        leftBorderPane.setLeft(gridPane);

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

        De = new Button("Lancer le dé");
        De.setStyle("-fx-background-color: #0f100f; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 4px;");
        De.getStyleClass().add("De-style");

        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().addAll(plateau, leDe, De, leftBorderPane);

        borderPane.setCenter(vBox);

        VBox graphContainer = new VBox();
        graphContainer.setAlignment(Pos.CENTER_LEFT);
        graphContainer.setFillWidth(true);
        check = new Button("Check");
        check.setStyle("-fx-background-color: #0f100f; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 4px;");

        De.setOnAction(actionEvent -> {
            LancerDe();
            leDe.setShow(true);
            leDe.update(getDe());
            remainingTime = TIMER_DURATION;
            if (!timerIsRunning) {
                startTimer();
            } else {
                timerProgressBar.setProgress(1.0);
            }

            question.setEditable(false);
            answer.setEditable(true);
            check.setDisable(false);
            De.setDisable(true);
            String mode = "";
            if (getDe() > 4) {
                mode = "hard";
                try {
                    RecuperationBD.diff();
                    textquestion.setText("Answer this question to move your circle:");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (mode.equals(""))
                if (getDe() <= 2) {
                    mode = "easy";
                    try {
                        RecuperationBD.easy();
                        textquestion.setText("Answer this question to move your circle:");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            if (mode.equals(""))
                if (getDe() == 3 || getDe() == 4) {
                    mode = "medium";
                    try {
                        RecuperationBD.med();
                        textquestion.setText("Answer this question to move your circle:");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            question.setText(RecuperationBD.question);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please answer the question to continue..", ButtonType.OK);
            alert.show();
        });

        check.setOnAction(ae -> {
            checkButton();
        });

        // Ajout du VBox à la gauche du borderPane
        BorderPane.setAlignment(gridPane, Pos.TOP_LEFT);
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
        Scene scene = new Scene(borderPane, 1000, 900);
        scene.setFill(Color.LIGHTGREEN);
        stage.setScene(scene);
        stage.setTitle("Goose Game");
        stage.show();
    }

    private void startTimer() {
        remainingTime = TIMER_DURATION;
        timerIsRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                double progress = (double) remainingTime / TIMER_DURATION;
                Platform.runLater(() -> timerProgressBar.setProgress(progress));
                if (remainingTime <= 0) {
                    Platform.runLater(() -> {
                        timerProgressBar.setProgress(0.0);
                        checkButton();
                    });

                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                        timerIsRunning = false;
                    }
                } else {
                    remainingTime--;
                }
            }
        }, 0, 1000);
    }

    public void checkButton() {
        question.setText(RecuperationBD.question);
        int flag = 0;
        if (answer.getText().equalsIgnoreCase(RecuperationBD.answer)) {
            flag = 1;
            reponse = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct Answer!", ButtonType.OK);
            alert.show();
        }
        question.setText("");
        answer.setText("");
        textquestion.setText("Question:");
        AffichageJoeur.getText();
        question.setStyle("-fx-font-weight: bold; -fx-padding: 5px; -fx-background-color: #f1f1f1; -fx-border-radius: 5px; -fx-border-color: #262525; -fx-border-width: 1px; -fx-alignment: center-left;");
        answer.setStyle("-fx-padding: 5px; -fx-background-color: #f9f9f9; -fx-border-radius: 5px; -fx-border-color: #131313; -fx-border-width: 1px; -fx-alignment: center-left;");
        textquestion.setStyle("-fx-font-size: 16px; -fx-text-fill: #212121;");

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
        if (!reponse) {
            this.playerIndex = (this.playerIndex + 1) % pions.length;
            question.setEditable(true);
            De.setDisable(false);
        }
    }
    /**
     * Cette méthode permet de lancer un dé à 6 faces.
     * Elle prend en compte un objet Random, et renvoie un nombre entier compris entre 1 et 6.
     */
    public void LancerDe () {
        this.de = this.random.nextInt(6) + 1;
    }
    public  int playerIndex = 0;
    /**
     * Cette méthode permet de faire bouger les pions.
     */
    public void mouvpion() {
        AtomicInteger i = new AtomicInteger();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        String email = Connections[playerIndex];

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

                }
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 69 && i.get() == 0) {

                    pion.setTranslateY(pion.getTranslateY() - 50);
                    i.set(1);
                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());

                }
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() >= 69 && numberOfSquaresTravelledCircle[this.playerIndex].get() < 79) {
                    pion.setTranslateX(pion.getTranslateX() - 50);

                    pos.set(pos.get() + 1);
                    numberOfSquaresTravelledCircle[this.playerIndex].set(numberOfSquaresTravelledCircle[this.playerIndex].get() + 1);
                    i.set(0);
                    System.out.println(numberOfSquaresTravelledCircle[this.playerIndex].get());
                    numberOfMovesCircle1.set(numberOfMovesCircle1.get()+1);

                }
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 79 && i.get() == 0) {

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

                }
                else if (numberOfSquaresTravelledCircle[this.playerIndex].get() == 89 && i.get() == 0) {

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
                // ajouter les points au score du joueur dans la base de données
                try {
                    Connection conn = Databaseutils.connect();
                    Statement stmt = conn.createStatement();
                    String sql = "UPDATE scorespseudo SET score = score + " + points + ", date_maj = NOW() WHERE emailuser = '" + email + "'";
                    stmt.executeUpdate(sql);
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION, "\t\t\t\t\tGame has ended!\n", ButtonType.OK);
                alert4.show();
            }
        });
        timeline.getKeyFrames().add(kf);
        System.out.println("Le de a fait " + getDe());
        timeline.play();
    }
    /**
     * Récupère la valeur du dé
     * @return la valeur de la variable de
     */
    public int getDe() {
        return de;
    }
    /**
     * Point d'entrée de l'application.
     * @param args arguments passés en ligne de commande
     */
    public static void main(String[] args) {
        launch();
    }
}