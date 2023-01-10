package interfaces.saelavrai.DAO;

import interfaces.saelavrai.JDBC.QuestionReponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionReponseDao {
    private Connection connection;

    public QuestionReponseDao(Connection connection) {
        this.connection = connection;
    }

    public void addQuestionReponse(QuestionReponse questionReponse) throws SQLException {
        String query = "INSERT INTO t_question_reponse (type_question, id_question, qcm, question, reponse, emailadm, date_maj) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, questionReponse.getTypeQuestion());
        preparedStatement.setInt(2, questionReponse.getIdQuestion());
        preparedStatement.setString(3, questionReponse.getQcm());
        preparedStatement.setString(4, questionReponse.getQuestion());
        preparedStatement.setString(5, questionReponse.getReponse());
        preparedStatement.setString(6, questionReponse.getEmailAdm());
        preparedStatement.setDate(7, (java.sql.Date) questionReponse.getDateMaj());

        preparedStatement.executeUpdate();
    }

    public List<QuestionReponse> getAllQuestionReponses() throws SQLException {
        String query = "SELECT * FROM t_question_reponse";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<QuestionReponse> questionReponses = new ArrayList<>();

        while (resultSet.next()) {
            QuestionReponse questionReponse = new QuestionReponse(
                    resultSet.getInt("id"),
                    resultSet.getString("type_question"),
                    resultSet.getInt("id_question"),
                    resultSet.getString("qcm"),
                    resultSet.getString("question"),
                    resultSet.getString("reponse"),
                    resultSet.getString("emailadm"),
                    resultSet.getDate("date_maj")
            );
            questionReponses.add(questionReponse);
        }

        return questionReponses;
    }

    public QuestionReponse getQuestionReponseById(Integer id) throws SQLException {
        String query = "SELECT * FROM t_question_reponse WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        QuestionReponse questionReponse = null;

        if (resultSet.next()) {
            questionReponse = new QuestionReponse(
                    resultSet.getInt("id"),
                    resultSet.getString("type_question"),
                    resultSet.getInt("id_question"),
                    resultSet.getString("qcm"),
                    resultSet.getString("question"),
                    resultSet.getString("reponse"),
                    resultSet.getString("emailadm"),
                    resultSet.getDate("date_maj")
            );
        }

        return questionReponse;
    }

    public void updateQuestionReponse(QuestionReponse questionReponse) throws SQLException {
        String query = "UPDATE t_question_reponse SET type_question = ?, id_question = ?, qcm = ?, question = ?, reponse = ?, emailadm = ?, date_maj = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, questionReponse.getTypeQuestion());
        preparedStatement.setInt(2, questionReponse.getIdQuestion());
        preparedStatement.setString(3, questionReponse.getQcm());
        preparedStatement.setString(4, questionReponse.getQuestion());
        preparedStatement.setString(5, questionReponse.getReponse());
        preparedStatement.setString(6, questionReponse.getEmailAdm());
        preparedStatement.setDate(7, (java.sql.Date) questionReponse.getDateMaj());
        preparedStatement.setInt(8, questionReponse.getId());

        preparedStatement.executeUpdate();
    }

    public void deleteQuestionReponse(Integer id) throws SQLException {
        String query = "DELETE FROM t_question_reponse WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }
}