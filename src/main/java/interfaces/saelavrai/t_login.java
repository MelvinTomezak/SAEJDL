package interfaces.saelavrai;



public class t_login {
    private int id;
    private String email;
    private String mot_de_passe;

    public t_login(String email, String mot_de_passe) {

        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {

    }}