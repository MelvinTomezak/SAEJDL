package interfaces.saelavrai;

import java.awt.*;
import java.util.ArrayList;

public abstract class Case {
    private String nomCase;
    private int idCase = 0;
    private int valeurCase = 0;
    private Polygon polygon = new Polygon();

    public Case (String nomCase, int valeurCase) {
        this.nomCase = nomCase;
        this.valeurCase = valeurCase;
    }

    public String getNomCase() {
        return nomCase;
    }

    public int getIdCase() {
        return idCase;
    }

    public void setIdCase(int idCase) {
        this.idCase = idCase;
    }

    public int getValeurCase() {
        return valeurCase;
    }

    public void setValeurCase(int valeurCase) {
        this.valeurCase = valeurCase;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
    public abstract String getCouleur();

    public abstract void actionCase (Joueur joueur, Plateau plateau);

    @Override
    public String toString() {
        return "Case{" + "nomCase='" + nomCase + '\'' + ", idCase=" + idCase + ", valeurCase=" + valeurCase + '}';
    }
}
