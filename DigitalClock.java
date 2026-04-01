/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.digitalclock;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {

    JLabel clockLabel;

    DigitalClock() {
        setTitle("Digital Clock");
        setSize(400, 200);
        setLayout(new FlowLayout());

        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 40));
        clockLabel.setForeground(Color.BLUE);

        add(clockLabel);

        // Timer - updates every 1 second
        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date d = new Date();
                clockLabel.setText(sdf.format(d));
            }
        });

        t.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DigitalClock();
    }
}