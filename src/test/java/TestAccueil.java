import interfaces.saelavrai.AccueilMain;
import interfaces.saelavrai.Joueur;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestAccueil {
    @Test
    void test_applicationselance() {
        AccueilMain.launch(AccueilMain.class);
    }

    @Test
    public void test_should_never_fail() {
        assertThat(true).isTrue();
    }
    @Test
    void creationjoueur() {
        Joueur joueur = new Joueur("Melvin",1);
        Joueur joueur1 = new Joueur("PHUGO",2);
        Joueur joueur2 = new Joueur("Enzo", 3);
        Joueur joueur3 = new Joueur("MALEX", 4);
        System.out.println(joueur);
        System.out.println(joueur1);
        System.out.println(joueur2);
        System.out.println(joueur3);

    }
    @Test
    void testNomFenetre() {

    }
}