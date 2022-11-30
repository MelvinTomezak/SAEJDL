package interfaces.saelavrai;

import java.util.ArrayList;

public class Plateau {
    private int IdJoueur = 0;
    private int NombreDeJoueur = 4;
    private int NombreDeCase = 47;
    private int NombreDeTour = 1;

    private ArrayList<Case> casee = new ArrayList<Case>();
    public LeDe de = new LeDe();

    public Plateau (int nombreDeJoueur, int nombreDeCase){
        this.NombreDeJoueur = nombreDeJoueur;
        this.NombreDeCase = nombreDeCase;
        for (int i=0; i<nombreDeCase;i++){
            casee.add(null);
        }
    }
    public Case getCase(int i){
        return this.casee.get(i);
    }


}
