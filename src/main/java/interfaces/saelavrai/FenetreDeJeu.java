/*package interfaces.saelavrai;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.util.ArrayList;

public class FenetreDeJeu {
    private Stage stageJeu;
    private StackPane stackPane;
    private ArrayList<Label> listeDeJoueur = new ArrayList<Label>();
    private ArrayList<Circle> listeDePion = new ArrayList<Circle>();
    private ArrayList<Image> imageDeDee = new ArrayList<Image>();
    private ArrayList<ImageView> Dee = new ArrayList<ImageView>();
    private Button TourSuivant = new Button("Passer au tour suivant");
    private Button nouvellePartie = new Button("Recommencer une nouvelle partie");
    private Color[] couleurs = new Color[]{Color.RED, Color.CYAN, Color.GREEN, Color.BLACK};
    private FenetreDeLancement fdl = new FenetreDeLancement(this);
    private Partie partie;
    public FenetreDeJeu(Stage stage){
        this.stageJeu = stage;

        stackPane = new StackPane();
        Scene scene = new Scene(stackPane, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Titre de la fenetre");
        fdl.getStage().show();

    }
}
*/