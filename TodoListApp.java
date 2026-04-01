/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.todolistapp;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoListApp {

    public static void main(String[] args) {

        // Frame
        JFrame frame = new JFrame("To-Do List");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel (Input + Add Button)
        JPanel topPanel = new JPanel();
        JTextField taskField = new JTextField(20);
        JButton addButton = new JButton("Add Task");

        topPanel.add(taskField);
        topPanel.add(addButton);

        // Center (JList)
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> taskList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Bottom Panel (Delete Button)
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Task");
        bottomPanel.add(deleteButton);

        // Add Action (Add Task)
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText().trim();
                if (!task.isEmpty()) {
                    model.addElement(task);
                    taskField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a task!");
                }
            }
        });

        // Delete Action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index != -1) {
                    model.remove(index);
                } else {
                    JOptionPane.showMessageDialog(frame, "Select a task to delete!");
                }
            }
        });

        // Add to Frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
