package com.hotelreservationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationPage extends JFrame {
    private JComboBox<String> roomCategoryComboBox;
    private JTextField numberOfRoomsTextField;
    private JButton searchButton;
    private JButton reserveButton;
    private JButton paymentButton;
    private JTextArea displayTextArea;

    private ArrayList<Room> rooms;

    public ReservationPage(ArrayList<Room> rooms) {
        this.rooms = rooms;

        setTitle("Reservation Page");
        setSize(800, 600);
        setLayout(new BorderLayout());


        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        background.setIcon(new ImageIcon(new ImageIcon("/Users/macbook/Desktop/hotelreservationsystem/src/hotel5.jpeg").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        setContentPane(background);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 150)); // Black with 150 alpha for transparency
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 14);


        JLabel roomCategoryLabel = new JLabel("Room Category:");
        roomCategoryLabel.setFont(font);
        roomCategoryLabel.setForeground(Color.white);  // Set text color to black
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(roomCategoryLabel, gbc);

        roomCategoryComboBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        roomCategoryComboBox.setFont(font);
        gbc.gridx = 1;
        panel.add(roomCategoryComboBox, gbc);


        JLabel numberOfRoomsLabel = new JLabel("Number of Rooms:");
        numberOfRoomsLabel.setFont(font);
        numberOfRoomsLabel.setForeground(Color.white);  // Set text color to black
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(numberOfRoomsLabel, gbc);

        numberOfRoomsTextField = new JTextField();
        numberOfRoomsTextField.setFont(font);
        gbc.gridx = 1;
        panel.add(numberOfRoomsTextField, gbc);


        searchButton = new JButton("Search Rooms");
        searchButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(searchButton, gbc);


        reserveButton = new JButton("Reserve Rooms");
        reserveButton.setFont(font);
        gbc.gridy = 3;
        panel.add(reserveButton, gbc);


        paymentButton = new JButton("Process Payment");
        paymentButton.setFont(font);
        gbc.gridy = 4;
        panel.add(paymentButton, gbc);


        displayTextArea = new JTextArea();
        displayTextArea.setFont(font);
        displayTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        add(panel, BorderLayout.CENTER);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRooms();
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveRooms();
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPaymentPage();
            }
        });
    }

    private void searchRooms() {
        String category = (String) roomCategoryComboBox.getSelectedItem();
        String numberOfRoomsText = numberOfRoomsTextField.getText();

        if (numberOfRoomsText.isEmpty()) {
            displayTextArea.setText("Please enter the number of rooms.");
            return;
        }

        int numberOfRooms;
        try {
            numberOfRooms = Integer.parseInt(numberOfRoomsText);
        } catch (NumberFormatException e) {
            displayTextArea.setText("Please enter a valid number of rooms.");
            return;
        }

        StringBuilder result = new StringBuilder("Available rooms:\n");

        for (Room room : rooms) {
            if (room.getCategory().equals(category) && !room.isReserved()) {
                result.append("Room ").append(room.getRoomNumber()).append("\n");
            }
        }

        displayTextArea.setText(result.toString());
    }

    private void reserveRooms() {
        String category = (String) roomCategoryComboBox.getSelectedItem();
        String numberOfRoomsText = numberOfRoomsTextField.getText();

        if (numberOfRoomsText.isEmpty()) {
            displayTextArea.setText("Please enter the number of rooms.");
            return;
        }

        int numberOfRooms;
        try {
            numberOfRooms = Integer.parseInt(numberOfRoomsText);
        } catch (NumberFormatException e) {
            displayTextArea.setText("Please enter a valid number of rooms.");
            return;
        }

        int roomsReserved = 0;

        for (Room room : rooms) {
            if (room.getCategory().equals(category) && !room.isReserved()) {
                room.setReserved(true);
                roomsReserved++;
                if (roomsReserved == numberOfRooms) {
                    break;
                }
            }
        }

        if (roomsReserved == numberOfRooms) {
            displayTextArea.setText("Rooms reserved successfully.");
        } else {
            displayTextArea.setText("Not enough rooms available.");
        }
    }

    private void openPaymentPage() {
        PaymentPage paymentPage = new PaymentPage(12000);
        paymentPage.setVisible(true);
    }
}
