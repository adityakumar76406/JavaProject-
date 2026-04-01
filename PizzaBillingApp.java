/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pizzabillingapp;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PizzaBillingApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Pizza Billing System");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Title
        JLabel title = new JLabel("Pizza Order System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title, BorderLayout.NORTH);

        // Center Panel (Items)
        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));

        JCheckBox pizza = new JCheckBox("Pizza (₹200)");
        JCheckBox burger = new JCheckBox("Burger (₹100)");
        JCheckBox pasta = new JCheckBox("Pasta (₹150)");
        JCheckBox coke = new JCheckBox("Coke (₹50)");

        JTextField pizzaQty = new JTextField();
        JTextField burgerQty = new JTextField();
        JTextField pastaQty = new JTextField();
        JTextField cokeQty = new JTextField();

        panel.add(new JLabel("Item"));
        panel.add(new JLabel("Select"));
        panel.add(new JLabel("Quantity"));

        panel.add(new JLabel("Pizza"));
        panel.add(pizza);
        panel.add(pizzaQty);

        panel.add(new JLabel("Burger"));
        panel.add(burger);
        panel.add(burgerQty);

        panel.add(new JLabel("Pasta"));
        panel.add(pasta);
        panel.add(pastaQty);

        panel.add(new JLabel("Coke"));
        panel.add(coke);
        panel.add(cokeQty);

        frame.add(panel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottom = new JPanel();

        JButton totalBtn = new JButton("Calculate Total");
        JButton resetBtn = new JButton("Reset");

        JLabel result = new JLabel("Total: ₹0");

        bottom.add(totalBtn);
        bottom.add(resetBtn);
        bottom.add(result);

        frame.add(bottom, BorderLayout.SOUTH);

        // Calculate Logic
        totalBtn.addActionListener(e -> {
            int total = 0;

            try {
                if (pizza.isSelected()) {
                    int qty = Integer.parseInt(pizzaQty.getText());
                    total += qty * 200;
                }
                if (burger.isSelected()) {
                    int qty = Integer.parseInt(burgerQty.getText());
                    total += qty * 100;
                }
                if (pasta.isSelected()) {
                    int qty = Integer.parseInt(pastaQty.getText());
                    total += qty * 150;
                }
                if (coke.isSelected()) {
                    int qty = Integer.parseInt(cokeQty.getText());
                    total += qty * 50;
                }

                result.setText("Total: ₹" + total);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Enter valid quantity!");
            }
        });

        // Reset Logic
        resetBtn.addActionListener(e -> {
            pizza.setSelected(false);
            burger.setSelected(false);
            pasta.setSelected(false);
            coke.setSelected(false);

            pizzaQty.setText("");
            burgerQty.setText("");
            pastaQty.setText("");
            cokeQty.setText("");

            result.setText("Total: ₹0");
        });

        frame.setVisible(true);
    }
}
