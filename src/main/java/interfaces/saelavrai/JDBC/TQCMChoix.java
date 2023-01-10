package interfaces.saelavrai.JDBC;

import java.sql.Timestamp;

public class TQCMChoix {

    private String typeQuestion;
    private int idQuestion;
    private int numReponse;
    private String choixReponse;
    private String emailAdm;
    private Timestamp dateMaj;

    public TQCMChoix(String typeQuestion, int idQuestion, int numReponse, String choixReponse, String emailAdm, Timestamp dateMaj) {
        this.typeQuestion = typeQuestion;
        this.idQuestion = idQuestion;
        this.numReponse = numReponse;
        this.choixReponse = choixReponse;
        this.emailAdm = emailAdm;
        this.dateMaj = dateMaj;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getNumReponse() {
        return numReponse;
    }

    public void setNumReponse(int numReponse) {
        this.numReponse = numReponse;
    }

    public String getChoixReponse() {
        return choixReponse;
    }

    public void setChoixReponse(String choixReponse) {
        this.choixReponse = choixReponse;
    }

    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    public Timestamp getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Timestamp dateMaj) {
        this.dateMaj = dateMaj;
    }

}
