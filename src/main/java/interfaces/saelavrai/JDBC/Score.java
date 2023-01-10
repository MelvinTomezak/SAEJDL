package interfaces.saelavrai.JDBC;

import java.time.LocalDateTime;
import java.util.List;

public class Score {

    private int id;
    private int idUsr;
    private int score;
    private LocalDateTime dateMaj;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUsr() {
        return idUsr;
    }
    public void setIdUsr(int idUsr) {
        this.idUsr = idUsr;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public LocalDateTime getDateMaj() {
        return dateMaj;
    }
    public void setDateMaj(LocalDateTime dateMaj) {
        this.dateMaj = dateMaj;
    }

    public interface ScoreDao {

        public void createScore(Score score);
        public Score getScoreById(int id);
        public List<Score> getScoresByUsrId(int idUsr);
        public void updateScore(Score score);
        public void deleteScoreById(int id);

    }
}