package interfaces.saelavrai;

import javax.swing.*;
import java.awt.*;

public class Plateau extends JFrame {
    int NbCase;
    JButton[][] cases = new JButton[10][10];
    JPanel plateau = new JPanel();
    public Plateau(){
        setSize(800,800);
        setLocationRelativeTo(null);
        setTitle("Goose Game");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        plateau.setLayout(new GridLayout(10, 10));
        plateau.setBackground(Color.GREEN);
        for (int i = 0; i<10;i++){
            for (int j = 0; j<10; j++){
                cases[i][j] = new JButton();
                plateau.add(cases[i][j]);
                cases[i][j].setEnabled(false);
            }
        }
        cases[9][1].setBackground(Color.GREEN);
        cases[9][1].setText("1");
        cases[9][2].setBackground(Color.YELLOW);
        cases[9][2].setText("2");
        cases[9][3].setBackground(Color.RED);
        cases[9][3].setText("3");
        cases[9][4].setBackground(Color.GREEN);
        cases[9][5].setBackground(Color.YELLOW);
        cases[9][6].setBackground(Color.RED);
        cases[9][7].setBackground(Color.GREEN);
        cases[9][8].setBackground(Color.YELLOW);
        cases[9][9].setBackground(Color.RED);
        cases[8][9].setBackground(Color.GREEN);
        cases[8][8].setBackground(Color.YELLOW);
        cases[8][7].setBackground(Color.RED);
        cases[8][6].setBackground(Color.GREEN);
        cases[8][5].setBackground(Color.YELLOW);
        cases[8][4].setBackground(Color.RED);
        cases[8][3].setBackground(Color.GREEN);
        cases[8][2].setBackground(Color.YELLOW);
        cases[8][1].setBackground(Color.RED);
        cases[8][0].setBackground(Color.GREEN);
        cases[7][0].setBackground(Color.YELLOW);
        cases[7][1].setBackground(Color.RED);
        cases[7][2].setBackground(Color.GREEN);
        cases[7][3].setBackground(Color.YELLOW);
        cases[7][4].setBackground(Color.RED);
        cases[7][5].setBackground(Color.GREEN);
        cases[7][6].setBackground(Color.YELLOW);
        cases[7][7].setBackground(Color.RED);
        cases[7][8].setBackground(Color.GREEN);
        cases[7][9].setBackground(Color.YELLOW);
        cases[6][9].setBackground(Color.RED);
        cases[6][8].setBackground(Color.GREEN);
        cases[6][7].setBackground(Color.YELLOW);
        cases[6][6].setBackground(Color.RED);
        cases[6][5].setBackground(Color.GREEN);
        cases[6][4].setBackground(Color.YELLOW);
        cases[6][3].setBackground(Color.RED);
        cases[6][2].setBackground(Color.GREEN);
        cases[6][1].setBackground(Color.YELLOW);
        cases[6][0].setBackground(Color.RED);
        cases[5][0].setBackground(Color.GREEN);
        cases[5][1].setBackground(Color.YELLOW);
        cases[5][2].setBackground(Color.RED);
        cases[5][3].setBackground(Color.GREEN);
        cases[5][4].setBackground(Color.YELLOW);
        cases[5][5].setBackground(Color.RED);
        cases[5][6].setBackground(Color.GREEN);
        cases[5][7].setBackground(Color.YELLOW);
        cases[5][8].setBackground(Color.RED);
        cases[5][9].setBackground(Color.GREEN);
        cases[4][9].setBackground(Color.YELLOW);
        cases[4][8].setBackground(Color.RED);
        cases[4][7].setBackground(Color.GREEN);
        cases[4][6].setBackground(Color.YELLOW);
        cases[4][5].setBackground(Color.RED);
        cases[4][4].setBackground(Color.GREEN);
        cases[4][3].setBackground(Color.YELLOW);
        cases[4][2].setBackground(Color.RED);
        cases[4][1].setBackground(Color.GREEN);
        cases[4][0].setBackground(Color.YELLOW);
        cases[3][0].setBackground(Color.RED);
        cases[3][1].setBackground(Color.GREEN);
        cases[3][2].setBackground(Color.YELLOW);
        cases[3][3].setBackground(Color.RED);
        cases[3][4].setBackground(Color.GREEN);
        cases[3][5].setBackground(Color.YELLOW);
        cases[3][6].setBackground(Color.RED);
        cases[3][7].setBackground(Color.GREEN);
        cases[3][8].setBackground(Color.YELLOW);
        cases[3][9].setBackground(Color.RED);
        cases[2][9].setBackground(Color.GREEN);
        cases[2][8].setBackground(Color.YELLOW);
        cases[2][7].setBackground(Color.RED);
        cases[2][6].setBackground(Color.GREEN);
        cases[2][5].setBackground(Color.YELLOW);
        cases[2][4].setBackground(Color.RED);
        cases[2][3].setBackground(Color.GREEN);
        cases[2][2].setBackground(Color.YELLOW);
        cases[2][1].setBackground(Color.RED);
        cases[2][0].setBackground(Color.GREEN);
        cases[1][0].setBackground(Color.YELLOW);
        cases[1][1].setBackground(Color.RED);
        cases[1][2].setBackground(Color.GREEN);
        cases[1][3].setBackground(Color.YELLOW);
        cases[1][4].setBackground(Color.RED);
        cases[1][5].setBackground(Color.GREEN);
        cases[1][6].setBackground(Color.YELLOW);
        cases[1][7].setBackground(Color.RED);
        cases[1][8].setBackground(Color.GREEN);
        cases[1][9].setBackground(Color.YELLOW);
        cases[0][9].setBackground(Color.RED);
        cases[0][8].setBackground(Color.GREEN);
        cases[0][7].setBackground(Color.YELLOW);
        cases[0][6].setBackground(Color.RED);
        cases[0][5].setBackground(Color.GREEN);
        cases[0][4].setBackground(Color.YELLOW);
        cases[0][3].setBackground(Color.RED);
        cases[0][2].setBackground(Color.GREEN);
        cases[0][1].setBackground(Color.YELLOW);
        add(plateau, BorderLayout.CENTER);
    }

    public JButton[][] getCases() {
        return cases;
    }

    public void setVisble(boolean b) {
        this.setVisible(true);
    }

    public void setCases(JButton[][] cases) {
        this.cases = cases;
    }
}