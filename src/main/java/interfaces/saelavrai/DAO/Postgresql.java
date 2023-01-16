package interfaces.saelavrai.DAO;

import interfaces.saelavrai.Plateau;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Postgresql est une classe qui contient une fonction statique connect() qui se connecte à une base de données PostgreSQL.
 *
 * @author Les Avanturiers
 * @version 1.0
 */
public class Postgresql {
    static Connection c = null;
    /**
     * La fonction connect() est une méthode statique qui se connecte à une base de données PostgreSQL.
     */
    public static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
           c = DriverManager
                    .getConnection("jdbc:postgresql://lucky.db.elephantsql.com/nyjbtmho",
                            "nyjbtmho", "ILZMPn922ytEsKA_D5HGKSBIGo7rT7of");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
