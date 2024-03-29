package interfaces.saelavrai;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.scene.control.TableView;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public TableView<t_login> emailTable;
    public TableColumn<t_login, String> emailColumn;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;
    static List<String> connectedUsers = new ArrayList<>();
    static String[] Connections = new String[4];
    public static int conSize=0;
    int index = 0;



    @FXML
    private void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        password = encryptPassword(password);

        // Vérification des informations de connexion dans la base de données
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://trumpet.db.elephantsql.com/itkrikdc",
                    "itkrikdc", "4KdTrccy3LgH8IGDpq9P2qeZAdJo4l-n");

            String query = "SELECT * FROM login WHERE email=? AND password=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // L'authentification a réussi, récupération de l'e-mail de l'utilisateur connecté
                String email = rs.getString("email");
//                int id = rs.getInt("id");
                String passwordFromDB = rs.getString("password");



                // Création d'un nouvel objet t_login pour l'utilisateur connecté
                t_login user = new t_login(email, passwordFromDB);

                // Ajout de l'utilisateur connecté à la liste des objets t_login
                ObservableList<t_login> data = emailTable.getItems();
                data.add(user);

                // Ajout de l'utilisateur connecté à la liste des utilisateurs connectés
                connectedUsers.add(email);

                // Ajout de l'utilisateur connecté à la première case libre du tableau "Connections"
                for (int i = 0; i < Connections.length; i++) {
                    if (Connections[i] == null) {
                        Connections[i] = email;
                        index++;
                        break;
                    }
                    conSize =index+1;
                }
                // Affichage de l'email de l'utilisateur connecté
                errorMessage.setText("Vous êtes connecté avec l'adresse e-mail suivante : " + email);

                // Effacer les champs d'entrée utilisateur
                usernameField.setText("");
                passwordField.setText("");

            } else {
                // L'authentification a échoué, affichage d'un message d'erreur
                errorMessage.setText("Nom d'utilisateur ou mot de passe incorrect !");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailTable.getItems().clear();
        // Initialisation de la TableView
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailTable.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void startGame(ActionEvent event) {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
        AccueilMain.stackPane.getChildren().get(4).setVisible(false);
        Stage stage = new Stage();
        Plateau plateau = new Plateau();
        plateau.start(stage);
    }


    public void Home () {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
        AccueilMain.stackPane.getChildren().get(4).setVisible(false);

    }
    public String encryptPassword(String str)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");    // getInstance() is called with the SHA-512 algorithm

            // To calculate the message digest of the input string
            byte[] messageDigest = md.digest(str.getBytes());   // Returned as a byte array
            BigInteger no = new BigInteger(1, messageDigest);   // Convert bytes array into signum representation
            StringBuilder hashtext = new StringBuilder(no.toString(16));    // Convert message summary to hexadecimal value

            // Add the previous 0 to get the 32-bit.
            while (hashtext.length() < 32) {
                hashtext.insert(0,'0');
            }
            return hashtext.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
