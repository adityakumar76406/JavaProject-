/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.addressbookapp;

/**
 *
 * @author hp
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AddressBookApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Address Book");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel (Input Fields)
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();

        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(phoneLabel);
        topPanel.add(phoneField);

        frame.add(topPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Name", "Phone"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel (Buttons)
        JPanel bottomPanel = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        bottomPanel.add(addBtn);
        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add Contact
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Fill all fields!");
            } else {
                model.addRow(new Object[]{name, phone});
                nameField.setText("");
                phoneField.setText("");
            }
        });

        // Edit Contact
        editBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                model.setValueAt(nameField.getText(), selectedRow, 0);
                model.setValueAt(phoneField.getText(), selectedRow, 1);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a contact to edit!");
            }
        });

        // Delete Contact
        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a contact to delete!");
            }
        });

        // Table Row Click (Load data into fields)
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                nameField.setText(model.getValueAt(row, 0).toString());
                phoneField.setText(model.getValueAt(row, 1).toString());
            }
        });

        frame.setVisible(true);
    }
}
