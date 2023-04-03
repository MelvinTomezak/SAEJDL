package interfaces.saelavrai;


/**
 * Cette classe représente un joueur de jeu
 * @author Les Avanturiers
 */
public class Joueur {
    private String nomJoueur;
    private int idJoueur;
    private int positionJoueur = 0;
    /**
     * Constructeur de la classe Joueur
     * @param nomJoueur le nom du joueur
     * @param idJoueur l'identifiant du joueur
     */
    public Joueur (String nomJoueur, int idJoueur){
        setNomJoueur(nomJoueur);
        setIdJoueur(idJoueur);
    }

    /**
     * Retourne le nom du joueur
     * @return le nom du joueur
     */
    public String getNomJoueur() {
        return this.nomJoueur;
    }

    /**
     * Définit le nom du joueur
     * @param nomJoueur le nom du joueur
     */
    public void setNomJoueur(String nomJoueur) {
        if (nomJoueur == null || nomJoueur.isEmpty())
            throw new IllegalArgumentException("Veuillez saisir un nom valide");
        this.nomJoueur = nomJoueur;
    }

    /**
     * Retourne l'identifiant du joueur
     * @return l'identifiant du joueur
     */
    public int getIdJoueur() {
        return idJoueur;
    }

    /**
     * Définit l'identifiant du joueur
     * @param idJoueur l'identifiant du joueur
     */
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    /**
     * Retourne la position du joueur
     * @return la position du joueur
     */
    public int getPositionJoueur() {
        return positionJoueur;
    }

    /**
     * Définit la position du joueur
     * @param positionJoueur la position du joueur
     */
    public void setPositionJoueur(int positionJoueur) {
        this.positionJoueur = positionJoueur;
    }

    @Override
    public String toString() {
        return "Joueur{" + "nomJoueur='" + nomJoueur + '\'' + ", idJoueur=" + idJoueur + ", positionJoueur=" + positionJoueur + '}';
    }
}