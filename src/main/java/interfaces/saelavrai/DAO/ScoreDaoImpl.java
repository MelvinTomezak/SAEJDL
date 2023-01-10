package interfaces.saelavrai.DAO;

import interfaces.saelavrai.JDBC.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDaoImpl implements Score.ScoreDao {

    private Connection con;

    public ScoreDaoImpl(Connection con) {
        this.con = con;
    }

    public void createScore(Score score) {
        String sql = "INSERT INTO t_scores_pseudo (id_usr, score, date_maj) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, score.getIdUsr());
            stmt.setInt(2, score.getScore());
            stmt.setTimestamp(3, Timestamp.valueOf(score.getDateMaj()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Score getScoreById(int id) {
        String sql = "SELECT * FROM t_scores_pseudo WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Score score = new Score();
                score.setId(rs.getInt("id"));
                score.setIdUsr(rs.getInt("id_usr"));
                score.setScore(rs.getInt("score"));
                score.setDateMaj(rs.getTimestamp("date_maj").toLocalDateTime());
                return score;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Score> getScoresByUsrId(int idUsr) {
        String sql = "SELECT * FROM t_scores_pseudo WHERE id_usr = ?";
        List<Score> scores = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsr);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Score score = new Score();
                score.setId(rs.getInt("id"));
                score.setIdUsr(rs.getInt("id_usr"));
                score.setScore(rs.getInt("score"));
                score.setDateMaj(rs.getTimestamp("date_maj").toLocalDateTime());
                scores.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public void updateScore(Score score) {
        String sql = "UPDATE t_scores_pseudo SET id_usr = ?, score = ?, date_maj = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, score.getIdUsr());
            stmt.setInt(2, score.getScore());
            stmt.setTimestamp(3, Timestamp.valueOf(score.getDateMaj()));
            stmt.setInt(4, score.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteScoreById(int id) {
        String sql = "DELETE FROM t_scores_pseudo WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}