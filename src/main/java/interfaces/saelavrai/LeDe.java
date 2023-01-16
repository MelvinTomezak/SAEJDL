package interfaces.saelavrai;

import java.util.Random;

/**
 * La classe LeDe représente un dé à 6 faces.
 * @author Les Avanturiers
 */
public class LeDe {

    /**
     * Attribut représentant la valeur du dé
     */
    private int de1;

    /**
     * Attribut représentant le générateur de nombres aléatoires
     */
    private Random random = new Random();

    /**
     * Retourne la valeur du dé
     *
     * @return La valeur du dé
     */
    public int getDe1() {
        return this.de1;
    }

    /**
     * Lance le dé et retourne sa valeur
     *
     * @return La valeur du dé
     */
    public int LancerDe () {
        this.de1 = 1+this.random.nextInt(6);

        return getDe1();
    }
}
