/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tictactoe;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton buttons[][] = new JButton[3][3];
    boolean xTurn = true;
    JLabel statusLabel;
    JButton resetButton;

    TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3,3));

        Font f = new Font("Arial", Font.BOLD, 40);

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(f);
                buttons[i][j].addActionListener(this);
                grid.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Player X Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());

        add(statusLabel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();

        if(!b.getText().equals("")) return;

        if(xTurn) {
            b.setText("X");
            statusLabel.setText("Player O Turn");
        } else {
            b.setText("O");
            statusLabel.setText("Player X Turn");
        }

        xTurn = !xTurn;

        checkWinner();
    }

    void checkWinner() {

        // Rows & Columns
        for(int i=0;i<3;i++) {
            if(buttons[i][0].getText().equals("") == false &&
               buttons[i][0].getText().equals(buttons[i][1].getText()) &&
               buttons[i][1].getText().equals(buttons[i][2].getText())) {
                showWinner(buttons[i][0].getText());
                return;
            }

            if(buttons[0][i].getText().equals("") == false &&
               buttons[0][i].getText().equals(buttons[1][i].getText()) &&
               buttons[1][i].getText().equals(buttons[2][i].getText())) {
                showWinner(buttons[0][i].getText());
                return;
            }
        }

        // Diagonals
        if(buttons[0][0].getText().equals("") == false &&
           buttons[0][0].getText().equals(buttons[1][1].getText()) &&
           buttons[1][1].getText().equals(buttons[2][2].getText())) {
            showWinner(buttons[0][0].getText());
            return;
        }

        if(buttons[0][2].getText().equals("") == false &&
           buttons[0][2].getText().equals(buttons[1][1].getText()) &&
           buttons[1][1].getText().equals(buttons[2][0].getText())) {
            showWinner(buttons[0][2].getText());
            return;
        }

        // Draw
        boolean draw = true;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(buttons[i][j].getText().equals("")) {
                    draw = false;
                }
            }
        }

        if(draw) {
            JOptionPane.showMessageDialog(this, "Match Draw!");
            resetGame();
        }
    }

    void showWinner(String player) {
        JOptionPane.showMessageDialog(this, "Player " + player + " Wins!");
        resetGame();
    }

    void resetGame() {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true;
        statusLabel.setText("Player X Turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
