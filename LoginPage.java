package com.hotelreservationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LoginPage extends JFrame {
    private JTextField nameTextField;
    private JTextField mobileNumberTextField;
    private JButton loginButton;
    private ArrayList<AnotherUser> users;

    public LoginPage(ArrayList<AnotherUser> users) {
        this.users = users;

        setTitle("Login Page");
        setSize(400, 300);
        setLayout(new BorderLayout());


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("/Users/macbook/Desktop/hotelreservationsystem/src/hotel4.jpeg")); // Adjust image path as needed
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 14);


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        nameTextField = new JTextField();
        nameTextField.setFont(font);
        gbc.gridx = 1;
        panel.add(nameTextField, gbc);

        JLabel mobileNumberLabel = new JLabel("Mobile Number:");
        mobileNumberLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(mobileNumberLabel, gbc);

        mobileNumberTextField = new JTextField();
        mobileNumberTextField.setFont(font);
        gbc.gridx = 1;
        panel.add(mobileNumberTextField, gbc);


        loginButton = new JButton("Login");
        loginButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        backgroundPanel.add(panel, BorderLayout.CENTER);

        add(backgroundPanel);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String name = nameTextField.getText();
        String mobileNumber = mobileNumberTextField.getText();

        if (name.isEmpty() || mobileNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean loggedIn = false;
        for (AnotherUser user : users) {
            if (user.getName().equals(name) && user.getMobileNumber().equals(mobileNumber)) {
                JOptionPane.showMessageDialog(this, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                loggedIn = true;
                break;
            }
        }

        if (!loggedIn) {
            JOptionPane.showMessageDialog(this, "Invalid name or mobile number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ArrayList<AnotherUser> users = new ArrayList<>();
        users.add(new AnotherUser("payal", "1234567890"));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage loginPage = new LoginPage(users);
                loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginPage.setVisible(true);
            }
        });
    }
}

class AnotherUser {
    private String name;
    private String mobileNumber;

    public AnotherUser(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
