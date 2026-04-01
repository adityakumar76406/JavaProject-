/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculator;

/**
 *
 * @author hp
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField tf;
    JButton b[] = new JButton[16];
    String s = "", operator = "";
    double num1 = 0, num2 = 0, result = 0;

    String buttons[] = {
        "7","8","9","/",
        "4","5","6","*",
        "1","2","3","-",
        "0","C","=","+"
    };

    Calculator() {
        setTitle("Basic Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());

        tf = new JTextField();
        tf.setFont(new Font("Arial", Font.BOLD, 20));
        tf.setHorizontalAlignment(JTextField.RIGHT);
        add(tf, BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,4,5,5));

        for(int i=0;i<16;i++) {
            b[i] = new JButton(buttons[i]);
            b[i].setFont(new Font("Arial", Font.BOLD, 18));
            b[i].addActionListener(this);
            p.add(b[i]);
        }

        add(p, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();

        if(btn.charAt(0) >= '0' && btn.charAt(0) <= '9') {
            s += btn;
            tf.setText(s);
        }
        else if(btn.equals("C")) {
            s = "";
            num1 = num2 = result = 0;
            tf.setText("");
        }
        else if(btn.equals("=")) {
            num2 = Double.parseDouble(s);

            switch(operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num1 / num2; break;
            }

            tf.setText(String.valueOf(result));
            s = "";
        }
        else {
            num1 = Double.parseDouble(s);
            operator = btn;
            s = "";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}