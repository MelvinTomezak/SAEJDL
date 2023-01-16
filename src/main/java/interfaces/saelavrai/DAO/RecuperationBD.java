package interfaces.saelavrai.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Cette classe sert à récupérer des questions et leurs réponses depuis la base de données.
 * Les questions et réponses récupérées sont ensuite utilisées pour vérifier si l'utilisateur a
 * saisi la bonne réponse.
 */
public class RecuperationBD {
    /**
     * Question et Réponse stockées par la classe
     */
    public static String question;
    public static String answer;

    /**
     * Les requêtes SQL qui récupèrent des questions et leurs réponses depuis la base de données.
     * Les requêtes se basent sur les types de questions (difficiles, moyennes ou faciles).
     */
    static final String difficult = "SELECT Question,Reponse FROM T_QUESTION_REPONSE WHERE TYPE_QUESTION = 'D' \n" +
            "ORDER BY RANDOM()" + '\n' +
            "LIMIT 1;";

    static final String medium = "SELECT Question,Reponse FROM T_QUESTION_REPONSE WHERE TYPE_QUESTION = 'M' \n" +
            "ORDER BY RANDOM()" + '\n' +
            "LIMIT 1;";

    static final String easy = "SELECT Question,Reponse FROM T_QUESTION_REPONSE WHERE TYPE_QUESTION = 'F' \n" +
            "ORDER BY RANDOM()" + '\n' +
            "LIMIT 1;";

    /**
     * Cette fonction récupère des questions et leurs réponses depuis la base de données
     * pour des questions difficiles.
     * @throws SQLException
     */
    public static void diff() throws SQLException {
        Statement statement = Postgresql.c.createStatement();
        ResultSet resultSet = statement.executeQuery(difficult);
        resultSet.next();
        question=resultSet.getString(1);
        answer=resultSet.getString(2);
    }

    /**
     * Cette fonction récupère des questions et leurs réponses depuis la base de données
     * pour des questions moyennes.
     * @throws SQLException
     */
    public static void med() throws SQLException {
        Statement statement = Postgresql.c.createStatement();
        ResultSet resultSet = statement.executeQuery(medium);
        resultSet.next();
        question=resultSet.getString(1);
        answer=resultSet.getString(2);
    }

    /**
     * Cette fonction récupère des questions et leurs réponses depuis la base de données
     * pour des questions faciles.
     * @throws SQLException
     */
    public static void easy() throws SQLException {
        Statement statement = Postgresql.c.createStatement();
        ResultSet resultSet = statement.executeQuery(easy);
        resultSet.next();
        question=resultSet.getString(1);
        answer=resultSet.getString(2);
    }

    /**
     * Cette fonction vérifie si l'utilisateur a saisi la bonne réponse
     * @param q La question que l'utilisateur a saisie
     * @param a La réponse que l'utilisateur a saisie
     * @return true si la question et la réponse sont les mêmes que celles stockées dans la classe, false sinon
     */
    public static boolean comparator(String q,String a){
        if( q.equals(question) && a.equals(answer) ){
            return true;
        }
        return false;
    }
}
