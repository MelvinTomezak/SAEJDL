package interfaces.saelavrai.DAO;

import interfaces.saelavrai.JDBC.TQCMChoix;

import java.sql.*;

public class TQCMChoixDao {

    private Connection con;

    public TQCMChoixDao(Connection con) {
        this.con = con;
    }

    public void addTQCMChoix(TQCMChoix TQCMChoix) throws SQLException {
        String sql = "INSERT INTO t_qcm_choix (type_question, id_question, num_reponse, choix_reponse, emailadm, date_maj) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, TQCMChoix.getTypeQuestion());
        stmt.setInt(2, TQCMChoix.getIdQuestion());
        stmt.setInt(3, TQCMChoix.getNumReponse());
        stmt.setString(4, TQCMChoix.getChoixReponse());
        stmt.setString(5, TQCMChoix.getEmailAdm());
        stmt.setTimestamp(6, TQCMChoix.getDateMaj());
        stmt.executeUpdate();
    }

    public TQCMChoix getTQCMChoix(String typeQuestion, int idQuestion) throws SQLException {
        String sql = "SELECT * FROM t_qcm_choix WHERE type_question = ? AND id_question = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, typeQuestion);
        stmt.setInt(2, idQuestion);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int numReponse = rs.getInt("num_reponse");
            String choixReponse = rs.getString("choix_reponse");
            String emailAdm = rs.getString("emailadm");
            Timestamp dateMaj = rs.getTimestamp("date_maj");
            return new TQCMChoix(typeQuestion, idQuestion, numReponse, choixReponse, emailAdm, dateMaj);
        } else {
            return null;
        }
    }

    public void updateTQCMChoix(TQCMChoix TQCMChoix) throws SQLException {
        String sql = "UPDATE t_qcm_choix SET num_reponse = ?, choix_reponse = ?, emailadm = ?, date_maj = ? WHERE type_question = ? AND id_question = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, TQCMChoix.getNumReponse());
        stmt.setString(2, TQCMChoix.getChoixReponse());
        stmt.setString(3, TQCMChoix.getEmailAdm());
        stmt.setTimestamp(4, TQCMChoix.getDateMaj());
        stmt.setString(5, TQCMChoix.getTypeQuestion());
        stmt.setInt(6, TQCMChoix.getIdQuestion());
        stmt.executeUpdate();
    }

    public void deleteTQCMChoix(String typeQuestion, int idQuestion) throws SQLException {
        String sql = "DELETE FROM t_qcm_choix WHERE type_question = ? AND id_question = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, typeQuestion);
        stmt.setInt(2, idQuestion);
        stmt.executeUpdate();
    }

}
