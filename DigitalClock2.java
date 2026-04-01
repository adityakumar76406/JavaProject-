/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.digitalclock2;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock2 extends JFrame {

    JLabel timeLabel;

    public DigitalClock2() {
        setTitle("Digital Clock");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        timeLabel = new JLabel("", JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(timeLabel);

        // Timer to update time every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        setVisible(true);
    }

    void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a | dd-MM-yyyy");
        String time = sdf.format(new Date());
        timeLabel.setText(time);
    }

    public static void main(String[] args) {
        new DigitalClock2();
    }
}