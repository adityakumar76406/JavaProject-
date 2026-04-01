/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.guessgame;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessGame extends JFrame implements ActionListener {

    JTextField inputField;
    JLabel messageLabel, attemptsLabel;
    JButton guessButton, resetButton;

    int number, attempts;

    GuessGame() {
        setTitle("Number Guessing Game");
        setSize(350, 250);
        setLayout(new FlowLayout());

        number = new Random().nextInt(100) + 1;
        attempts = 0;

        add(new JLabel("Guess a number (1-100):"));

        inputField = new JTextField(10);
        add(inputField);

        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");

        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        add(guessButton);
        add(resetButton);

        messageLabel = new JLabel("Start guessing...");
        attemptsLabel = new JLabel("Attempts: 0");

        add(messageLabel);
        add(attemptsLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(inputField.getText());
                attempts++;

                if (guess > number) {
                    messageLabel.setText("Too High!");
                } else if (guess < number) {
                    messageLabel.setText("Too Low!");
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Correct! You guessed in " + attempts + " attempts.");
                }

                attemptsLabel.setText("Attempts: " + attempts);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number!");
            }
        }

        if (e.getSource() == resetButton) {
            number = new Random().nextInt(100) + 1;
            attempts = 0;
            inputField.setText("");
            messageLabel.setText("Game Reset! Start guessing...");
            attemptsLabel.setText("Attempts: 0");
        }
    }

    public static void main(String[] args) {
        new GuessGame();
    }
}