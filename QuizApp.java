/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quizapp;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp {

    static String[] questions = {
        "1. What is Java?",
        "2. Which keyword is used for inheritance?",
        "3. Which method is entry point?",
        "4. Which is not OOP concept?"
    };

    static String[][] options = {
        {"Programming Language", "Animal", "Car", "Device"},
        {"extends", "implements", "import", "package"},
        {"start()", "main()", "run()", "init()"},
        {"Encapsulation", "Polymorphism", "Compilation", "Inheritance"}
    };

    static int[] answers = {0, 0, 1, 2}; // correct option index

    static int current = 0;
    static int score = 0;
    static int[] userAnswers = new int[questions.length];

    public static void main(String[] args) {

        JFrame frame = new JFrame("Quiz Application");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Question Label
        JLabel questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(questionLabel, BorderLayout.NORTH);

        // Options Panel
        JPanel optionPanel = new JPanel(new GridLayout(4, 1));
        JRadioButton[] optionsBtn = new JRadioButton[4];
        ButtonGroup bg = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionsBtn[i] = new JRadioButton();
            bg.add(optionsBtn[i]);
            optionPanel.add(optionsBtn[i]);
        }

        frame.add(optionPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        JButton prevBtn = new JButton("Previous");
        JButton nextBtn = new JButton("Next");
        JButton submitBtn = new JButton("Submit");

        btnPanel.add(prevBtn);
        btnPanel.add(nextBtn);
        btnPanel.add(submitBtn);

        frame.add(btnPanel, BorderLayout.SOUTH);

        // Function to load question
        Runnable loadQuestion = () -> {
            bg.clearSelection();
            questionLabel.setText(questions[current]);

            for (int i = 0; i < 4; i++) {
                optionsBtn[i].setText(options[current][i]);
            }

            if (userAnswers[current] != -1) {
                optionsBtn[userAnswers[current]].setSelected(true);
            }
        };

        // Initialize userAnswers
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1;
        }

        // Save answer
        ActionListener saveAnswer = e -> {
            for (int i = 0; i < 4; i++) {
                if (optionsBtn[i].isSelected()) {
                    userAnswers[current] = i;
                }
            }
        };

        // Next Button
        nextBtn.addActionListener(e -> {
            saveAnswer.actionPerformed(e);
            if (current < questions.length - 1) {
                current++;
                loadQuestion.run();
            }
        });

        // Previous Button
        prevBtn.addActionListener(e -> {
            saveAnswer.actionPerformed(e);
            if (current > 0) {
                current--;
                loadQuestion.run();
            }
        });

        // Submit Button
        submitBtn.addActionListener(e -> {
            saveAnswer.actionPerformed(e);
            score = 0;

            for (int i = 0; i < answers.length; i++) {
                if (userAnswers[i] == answers[i]) {
                    score++;
                }
            }

            JOptionPane.showMessageDialog(frame,
                    "Your Score: " + score + "/" + questions.length);
        });

        // Load first question
        loadQuestion.run();

        frame.setVisible(true);
    }
}
