package interfaces.saelavrai;

import interfaces.saelavrai.DAO.Postgresql;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


/**
 * Cette classe est le controller des scores
 * @author Les Avanturiers
 */public class ScoreController implements Initializable {

    @FXML
    private TableView<Score> scoreTable;

    @FXML
    private TableColumn<Score, Integer> idColumn;

    @FXML
    private TableColumn<Score, String> emailuserColumn;

    @FXML
    private TableColumn<Score, Integer> scoreColumn;

    @FXML
    private TableColumn<Score, String> dateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailuserColumn.setCellValueFactory(new PropertyValueFactory<>("emailuser"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_maj"));

        // récupération des scores à partir de la base de données
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://trumpet.db.elephantsql.com/itkrikdc",
                    "itkrikdc", "4KdTrccy3LgH8IGDpq9P2qeZAdJo4l-n");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM scorespseudo ORDER BY score DESC");            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String emailuser = rs.getString("emailuser");
                int score = rs.getInt("score");
                String date_maj = rs.getString("date_maj");
                scoreTable.getItems().add(new Score(id, emailuser, score, date_maj));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des scores: " + e.getMessage());
        }
    }



    /**
     * La fonction Home est reliée au button Home de la page score et elle permet de revenir à la page d'Accueil.
     */
    public void Home () {
        AccueilMain.stackPane.getChildren().get(0).setVisible(false);
        AccueilMain.stackPane.getChildren().get(1).setVisible(true);
        AccueilMain.stackPane.getChildren().get(2).setVisible(false);
        AccueilMain.stackPane.getChildren().get(3).setVisible(false);
        AccueilMain.stackPane.getChildren().get(4).setVisible(false);

    }



}
