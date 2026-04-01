/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stopwatchapp;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class StopwatchApp {

    private static int seconds = 0;
    private static int minutes = 0;
    private static int hours = 0;
    private static boolean running = false;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Stopwatch");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Time Display
        JLabel timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(timeLabel, BorderLayout.NORTH);

        // Center Panel (Lap List)
        DefaultListModel<String> lapModel = new DefaultListModel<>();
        JList<String> lapList = new JList<>(lapModel);
        JScrollPane scrollPane = new JScrollPane(lapList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel (Buttons)
        JPanel buttonPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");
        JButton resetBtn = new JButton("Reset");
        JButton lapBtn = new JButton("Lap");

        buttonPanel.add(startBtn);
        buttonPanel.add(stopBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(lapBtn);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Timer
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
                timeLabel.setText(
                        String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }
        });

        // Start Button
        startBtn.addActionListener(e -> {
            if (!running) {
                timer.start();
                running = true;
            }
        });

        // Stop Button
        stopBtn.addActionListener(e -> {
            timer.stop();
            running = false;
        });

        // Reset Button
        resetBtn.addActionListener(e -> {
            timer.stop();
            running = false;
            seconds = minutes = hours = 0;
            timeLabel.setText("00:00:00");
            lapModel.clear();
        });

        // Lap Button
        lapBtn.addActionListener(e -> {
            if (running) {
                lapModel.addElement("Lap: " + timeLabel.getText());
            } else {
                JOptionPane.showMessageDialog(frame, "Start the stopwatch first!");
            }
        });

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save Laps");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Save Laps (File I/O)
        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(frame);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (int i = 0; i < lapModel.size(); i++) {
                        writer.write(lapModel.getElementAt(i));
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(frame, "Laps saved successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file!");
                }
            }
        });

        // Exit
        exitItem.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
