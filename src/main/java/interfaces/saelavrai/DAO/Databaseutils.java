package interfaces.saelavrai.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseutils {
    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://trumpet.db.elephantsql.com/itkrikdc";
        String username = "itkrikdc";
        String password = "4KdTrccy3LgH8IGDpq9P2qeZAdJo4l-n";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}