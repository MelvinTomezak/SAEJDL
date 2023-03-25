package interfaces.saelavrai;
public class Score {
    private int id;
    private int email_usr;
    private int score;
    private String date_maj;

    public Score(int id, int email_usr, int score, String date_maj) {
        this.id = id;
        this.email_usr = email_usr;
        this.score = score;
        this.date_maj = date_maj;
    }

    public int getId() {
        return id;
    }

    public int getEmail_usr() {
        return email_usr;
    }

    public int getScore() {
        return score;
    }

    public String getDate_maj() {
        return date_maj;
    }
}
