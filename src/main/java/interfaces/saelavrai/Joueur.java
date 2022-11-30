package interfaces.saelavrai;

public class Joueur {
    private String nomJoueur;
    private int idJoueur;
    private int positionJoueur = 0;

    public Joueur (String nomJoueur, int idJoueur){
        setNomJoueur(nomJoueur);
        setIdJoueur(idJoueur);
    }

    public String getNomJoueur() {
        return this.nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        if (nomJoueur == null || nomJoueur.isEmpty())
                throw new IllegalArgumentException("Veuillez saisir un nom valide");
        this.nomJoueur = nomJoueur;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getPositionJoueur() {
        return positionJoueur;
    }

    public void setPositionJoueur(int positionJoueur) {
        this.positionJoueur = positionJoueur;
    }

    @Override
    public String toString() {
        return "Joueur{" + "nomJoueur='" + nomJoueur + '\'' + ", idJoueur=" + idJoueur + ", positionJoueur=" + positionJoueur + '}';
    }
}
