package interfaces.saelavrai.JDBC;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class jdbc {
    static final String CONNECT_URL = "jdbc:postgresql://lucky.db.elephantsql.com/nyjbtmho";
    static final String LOGIN = "nyjbtmho";
    static final String PASSWORD = "ILZMPn922ytEsKA_D5HGKSBIGo7rT7of";
    static final String QUERY = "SELECT *" + "FROM t_login;";

    private static final Logger logger = Logger.getLogger("jdbc");

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Connexion a la base de données
        logger.log(Level.INFO, "Connexion a " + CONNECT_URL);
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(CONNECT_URL, LOGIN, PASSWORD)) {
            logger.log(Level.INFO, "Création instruction SQL");
            try (Statement statement = connection.createStatement()) {
                logger.log(Level.INFO, "Execution de la requete : " + QUERY);
                try (ResultSet resultSet = statement.executeQuery(QUERY)) {
                    logger.log(Level.INFO, "Affichage du resultat");
                    while (resultSet.next()) {
                        logger.log(Level.INFO, "{0}", resultSet.getString("email") + " " + resultSet.getString("password") + " " + resultSet.getString("usertype"));
                    }
                }
                logger.log(Level.INFO, "Fin de l'exemple");
            } catch (SQLException e) {
                e.printStackTrace();// Arggg!!!
                logger.log(Level.INFO, e.getMessage());
            }
        }
    }
}

