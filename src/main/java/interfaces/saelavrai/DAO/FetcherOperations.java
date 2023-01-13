package interfaces.saelavrai.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetcherOperations {

    public static String question;
    public static String answer;
    static final String difficult = "SELECT Question,Reponse FROM T_QUESTION_REPONSE WHERE TYPE_QUESTION = 'D' \n" +
            "ORDER BY RANDOM()" + '\n' +
            "LIMIT 1;";

    static final String medium = "SELECT Question,Reponse FROM T_QUESTION_REPONSE WHERE TYPE_QUESTION = 'M' \n" +
            "ORDER BY RANDOM()" + '\n' +
            "LIMIT 1;";

    static final String easy = "SELECT Question,Reponse FROM T_QUESTION_REPONSE WHERE TYPE_QUESTION = 'F' \n" +
            "ORDER BY RANDOM()" + '\n' +
            "LIMIT 1;";



    public static void diff() throws SQLException {

        Statement statement = Postgresql.c.createStatement();
        ResultSet resultSet = statement.executeQuery(difficult);
        resultSet.next();
        question=resultSet.getString(1);
        answer=resultSet.getString(2);


    }

    public static void med() throws SQLException {

        Statement statement = Postgresql.c.createStatement();
        ResultSet resultSet = statement.executeQuery(medium);
        resultSet.next();
        question=resultSet.getString(1);
        answer=resultSet.getString(2);


    }

    public static void easy() throws SQLException {

        Statement statement = Postgresql.c.createStatement();
        ResultSet resultSet = statement.executeQuery(easy);
        resultSet.next();
        question=resultSet.getString(1);
        answer=resultSet.getString(2);


    }

    public static boolean comparator(String q,String a){
        if( q.equals(question) && a.equals(answer) ){
            return true;
        }
        return false;
    }



}
