package com.hotelreservationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterPage extends JFrame {
    private JTextField nameTextField;
    private JTextField cityTextField;
    private JTextField mobileNumberTextField;
    private JButton registerButton;
    private ArrayList<User> users;

    public RegisterPage(ArrayList<User> users) {
        this.users = users;

        setTitle("Register Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("/Users/macbook/Desktop/hotelreservationsystem/src/hotel1.jpeg").getImage());


        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridBagLayout());
        registrationPanel.setBackground(new Color(0, 0, 0, 200));
        registrationPanel.setPreferredSize(new Dimension(400, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color textColor = Color.WHITE;


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(font);
        nameLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        registrationPanel.add(nameLabel, gbc);

        nameTextField = new JTextField(20);
        nameTextField.setFont(font);
        gbc.gridx = 1;
        registrationPanel.add(nameTextField, gbc);


        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(font);
        cityLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        registrationPanel.add(cityLabel, gbc);

        cityTextField = new JTextField(20);
        cityTextField.setFont(font);
        gbc.gridx = 1;
        registrationPanel.add(cityTextField, gbc);


        JLabel mobileNumberLabel = new JLabel("Mobile Number:");
        mobileNumberLabel.setFont(font);
        mobileNumberLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        registrationPanel.add(mobileNumberLabel, gbc);

        mobileNumberTextField = new JTextField(20);
        mobileNumberTextField.setFont(font);
        gbc.gridx = 1;
        registrationPanel.add(mobileNumberTextField, gbc);


        registerButton = new JButton("Register");
        registerButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        registrationPanel.add(registerButton, gbc);

        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.add(registrationPanel, new GridBagConstraints());


        setContentPane(backgroundPanel);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = nameTextField.getText();
        String city = cityTextField.getText();
        String mobileNumber = mobileNumberTextField.getText();

        if (name.isEmpty() || city.isEmpty() || mobileNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(name, city, mobileNumber);
        users.add(user);

        JOptionPane.showMessageDialog(this, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<User> users = new ArrayList<>();
                new RegisterPage(users).setVisible(true);
            }
        });
    }


    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
