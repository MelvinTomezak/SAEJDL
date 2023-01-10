package interfaces.saelavrai.JDBC;

import java.sql.Date;

public class QuestionReponse {
    private Integer id;
    private String typeQuestion;
    private Integer idQuestion;
    private String qcm;
    private String question;
    private String reponse;
    private String emailAdm;
    private Date dateMaj;

    public QuestionReponse() {
    }

    public QuestionReponse(Integer id, String typeQuestion, Integer idQuestion, String qcm, String question, String reponse, String emailAdm, Date dateMaj) {
        this.id = id;
        this.typeQuestion = typeQuestion;
        this.idQuestion = idQuestion;
        this.qcm = qcm;
        this.question = question;
        this.reponse = reponse;
        this.emailAdm = emailAdm;
        this.dateMaj = dateMaj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQcm() {
        return qcm;
    }

    public void setQcm(String qcm) {
        this.qcm = qcm;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getEmailAdm() {
        return emailAdm;
    }

    public void setEmailAdm(String emailAdm) {
        this.emailAdm = emailAdm;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }
}
