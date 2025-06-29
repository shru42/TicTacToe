import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    boolean isXTurn = true;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return;

        clicked.setText(isXTurn ? "X" : "O");
        clicked.setEnabled(false);
        isXTurn = !isXTurn;

        checkWinner();
    }

    void checkWinner() {
        int[][] combos = {
                {0,1,2}, {3,4,5}, {6,7,8}, // rows
                {0,3,6}, {1,4,7}, {2,5,8}, // columns
                {0,4,8}, {2,4,6}           // diagonals
        };

        for (int[] combo : combos) {
            String a = buttons[combo[0]].getText();
            String b = buttons[combo[1]].getText();
            String c = buttons[combo[2]].getText();
            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                JOptionPane.showMessageDialog(this, a + " wins!");
                resetGame();
                return;
            }
        }

        boolean draw = true;
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                draw = false;
                break;
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
