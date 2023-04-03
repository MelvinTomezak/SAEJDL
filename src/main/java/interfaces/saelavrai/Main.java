package interfaces.saelavrai;

import interfaces.saelavrai.DAO.Postgresql;

import java.sql.SQLException;

public class Main {

    /**
     * Méthode main qui permet de lancer le jeu
     *
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        Postgresql.connect();
        // Serveur.fetchClients();
        AccueilMain.main();
    }

}
