/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.imageviewer;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ImageViewer extends JFrame implements ActionListener {

    JLabel imageLabel;
    JButton openBtn, nextBtn, prevBtn;

    File[] imageFiles;
    int index = 0;

    public ImageViewer() {
        setTitle("Image Viewer");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Image display label
        imageLabel = new JLabel("No Image Selected", JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel panel = new JPanel();

        openBtn = new JButton("Open");
        prevBtn = new JButton("Previous");
        nextBtn = new JButton("Next");

        panel.add(openBtn);
        panel.add(prevBtn);
        panel.add(nextBtn);

        add(panel, BorderLayout.SOUTH);

        // Add listeners
        openBtn.addActionListener(this);
        prevBtn.addActionListener(this);
        nextBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // OPEN
        if (e.getSource() == openBtn) {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);

            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                imageFiles = chooser.getSelectedFiles();
                index = 0;
                showImage();
            }
        }

        // NEXT
        else if (e.getSource() == nextBtn) {
            if (imageFiles != null && index < imageFiles.length - 1) {
                index++;
                showImage();
            }
        }

        // PREVIOUS
        else if (e.getSource() == prevBtn) {
            if (imageFiles != null && index > 0) {
                index--;
                showImage();
            }
        }
    }

    // Function to display image
    void showImage() {
        if (imageFiles != null && imageFiles.length > 0) {

            ImageIcon icon = new ImageIcon(imageFiles[index].getAbsolutePath());

            // Resize image
            Image img = icon.getImage();
            Image scaled = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);

            imageLabel.setIcon(new ImageIcon(scaled));
            imageLabel.setText(""); // remove text
        }
    }

    public static void main(String[] args) {
        new ImageViewer();
    }
}