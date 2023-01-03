package interfaces.saelavrai;

import javax.swing.*;
import java.awt.*;

public class Plateau extends JFrame {
    private int NbCase = 1;
    JButton[][] cases = new JButton[10][10];
    JPanel plateau = new JPanel();

    public Plateau() {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setTitle("Goose Game");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        plateau.setLayout(new GridLayout(10, 10));
        plateau.setBackground(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cases[i][j] = new JButton();
                plateau.add(cases[i][j]);
            }
        }
        for (int i = 9; i > 0; i = i - 1) {
            for (int j = 1; j < 10; j = j + 1) {
                cases[i][j].setBackground(Color.GREEN);
                cases[i][j].setEnabled(false);
                cases[i][j].setText(String.format(String.valueOf(NbCase)));
                NbCase = NbCase + 1;
                j = j + 1;
                cases[i][j].setBackground(Color.YELLOW);
                cases[i][j].setEnabled(false);
                cases[i][j].setText(String.format(String.valueOf(NbCase)));
                NbCase = NbCase + 1;
                j = j + 1;
                cases[i][j].setBackground(Color.RED);
                cases[i][j].setEnabled(false);
                cases[i][j].setText(String.format(String.valueOf(NbCase)));
                NbCase = NbCase + 1;
            }
            if (i == 1) {
                break;
            }
            --i;
            for (int j = 9; j > 0; j = j - 1) {
                cases[i][j].setBackground(Color.GREEN);
                cases[i][j].setEnabled(false);
                cases[i][j].setText(String.format(String.valueOf(NbCase)));
                j = j - 1;
                NbCase = NbCase + 1;
                cases[i][j].setBackground(Color.YELLOW);
                cases[i][j].setEnabled(false);
                cases[i][j].setText(String.format(String.valueOf(NbCase)));
                j = j - 1;
                NbCase = NbCase + 1;
                cases[i][j].setBackground(Color.RED);
                cases[i][j].setEnabled(false);
                cases[i][j].setText(String.format(String.valueOf(NbCase)));
                NbCase = NbCase + 1;
            }
        }
        add(plateau, BorderLayout.CENTER);
    }

    public JButton[][] getCases() {
        return cases;
    }



    public void setCases(JButton[][] cases) {
        this.cases = cases;
    }
}