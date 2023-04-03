package interfaces.saelavrai;
public class Score {
    private final int id;
    private final String emailuser;
    private final int score;
    private final String date_maj;

    public Score(int id, String emailuser, int score, String date_maj) {
        this.id = id;
        this.emailuser = emailuser;
        this.score = score;
        this.date_maj = date_maj;
    }


    public int getId() {
        return id;
    }

    public String getEmailuser() {
        return emailuser;
    }

    public int getScore() {
        return score;
    }

    public String getDate_maj() {
        return date_maj;
    }
}
