/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tempconverter;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame implements ActionListener {

    JTextField inputField, resultField;
    JButton cToF, fToC;

    TempConverter() {
        setTitle("Temperature Converter");
        setSize(350, 200);
        setLayout(new GridLayout(3,2,10,10));

        add(new JLabel("Enter Temperature:"));
        inputField = new JTextField();
        add(inputField);

        add(new JLabel("Result:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        cToF = new JButton("Celsius → Fahrenheit");
        fToC = new JButton("Fahrenheit → Celsius");

        cToF.addActionListener(this);
        fToC.addActionListener(this);

        add(cToF);
        add(fToC);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double temp = Double.parseDouble(inputField.getText());

            if(e.getSource() == cToF) {
                double f = (temp * 9/5) + 32;
                resultField.setText(f + " °F");
            }
            else if(e.getSource() == fToC) {
                double c = (temp - 32) * 5/9;
                resultField.setText(c + " °C");
            }

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Enter valid number!");
        }
    }

    public static void main(String[] args) {
        new TempConverter();
    }
}