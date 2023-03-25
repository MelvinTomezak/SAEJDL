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
                    .getConnection("jdbc:postgresql://trumpet.db.elephantsql.com/itkrikdc",
                            "itkrikdc", "4KdTrccy3LgH8IGDpq9P2qeZAdJo4l-n");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
