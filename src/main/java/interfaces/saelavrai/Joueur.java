package interfaces.saelavrai;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * La fonction setNomJoueur vas permettre aux joueurs de rentrer un nom et ce nom ne pourra pas être une chaine de caractères vide.
     * @param nomJoueur
     */
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