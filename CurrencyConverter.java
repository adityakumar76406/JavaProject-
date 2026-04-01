/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.currencyconverter;

/**
 *
 * @author hp
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {

    JTextField amountField, resultField;
    JComboBox<String> fromCurrency, toCurrency;
    JButton convertButton;

    String currencies[] = {"INR", "USD", "EUR"};

    CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(350, 200);
        setLayout(new GridLayout(4,2,10,10));

        add(new JLabel("Enter Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("From:"));
        fromCurrency = new JComboBox<>(currencies);
        add(fromCurrency);

        add(new JLabel("To:"));
        toCurrency = new JComboBox<>(currencies);
        add(toCurrency);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());

            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double result = convert(amount, from, to);

            resultField.setText(String.valueOf(result));

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Enter valid amount!");
        }
    }

    double convert(double amt, String from, String to) {

        // Convert to INR first (base currency)
        double inr = 0;

        switch(from) {
            case "INR": inr = amt; break;
            case "USD": inr = amt * 83; break;
            case "EUR": inr = amt * 90; break;
        }

        // Convert INR to target
        switch(to) {
            case "INR": return inr;
            case "USD": return inr / 83;
            case "EUR": return inr / 90;
        }

        return 0;
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}