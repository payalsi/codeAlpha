package com.hotelreservationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HotelReservationSystem extends JFrame {
    private JPanel mainPanel;
    private JButton reserveButton;
    private JButton registerButton;
    private JButton aboutUsButton;
    private JButton contactUsButton;

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public HotelReservationSystem() {
        setTitle("Payal Hotel Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        background.setIcon(new ImageIcon(new ImageIcon("/Users/macbook/Desktop/hotelreservationsystem/src/hotel.jpeg").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
        setContentPane(background);


        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setOpaque(false);


        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(registerButton);


        aboutUsButton = new JButton("About Us");
        aboutUsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(aboutUsButton);


        contactUsButton = new JButton("Contact Us");
        contactUsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(contactUsButton);

        add(headerPanel, BorderLayout.NORTH);


        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 14);

        JPanel aboutUsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        aboutUsPanel.setLayout(new GridBagLayout());
        aboutUsPanel.setOpaque(false);

        JLabel aboutUsLabel = new JLabel("<html><div style='text-align: center;'><h1 style='color: white;'>About Us</h1><p style='color: white;'>Payal Hotel is dedicated to providing luxurious accommodations with unmatched service.</p></div></html>");
        aboutUsLabel.setFont(font);
        aboutUsLabel.setForeground(Color.WHITE);
        aboutUsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        aboutUsPanel.add(aboutUsLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(aboutUsPanel, gbc);


        reserveButton = new JButton("Make a Reservation");
        reserveButton.setFont(font);
        reserveButton.setPreferredSize(new Dimension(150, 30)); // Adjusting the button width
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(reserveButton, gbc);

        add(mainPanel, BorderLayout.CENTER);


        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.WHITE);

        JLabel contactInfoLabel = new JLabel("<html><h2>Contact Information</h2><p>Email: contact@payalhotel.com<br>Phone: +91 12345 67890</p></html>");
        contactInfoLabel.setFont(font);
        footerPanel.add(contactInfoLabel);

        add(footerPanel, BorderLayout.SOUTH);


        populateRooms();


        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservationPage();
            }
        });

        contactUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showContactUs();
            }
        });

        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAboutUs();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegisterPage();
            }
        });
    }

    private void populateRooms() {
        // Populate room list with sample data
        rooms.add(new Room("Single", 101));
        rooms.add(new Room("Single", 102));
        rooms.add(new Room("Double", 201));
        rooms.add(new Room("Double", 202));
        rooms.add(new Room("Suite", 301));
        rooms.add(new Room("Suite", 302));
    }

    private void showReservationPage() {

        ReservationPage reservationPage = new ReservationPage(rooms);
        reservationPage.setVisible(true);
    }

    private void showContactUs() {
        JOptionPane.showMessageDialog(this, "Contact Us:\nEmail: contact@payalhotel.com\nPhone: +91 12345 67890", "Contact Us", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAboutUs() {
        JOptionPane.showMessageDialog(this, "About Us:\nPayal Hotel is dedicated to providing luxurious accommodations with unmatched service.", "About Us", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showRegisterPage() {

        RegisterPage registerPage = new RegisterPage(users);
        registerPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelReservationSystem().setVisible(true);
            }
        });
    }
}

class Room {
    private String category;
    private int roomNumber;
    private boolean reserved;

    public Room(String category, int roomNumber) {
        this.category = category;
        this.roomNumber = roomNumber;
        this.reserved = false;
    }

    public String getCategory() {
        return category;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}

class User {
    private String name;
    private String city;
    private String mobileNumber;

    public User(String name, String city, String mobileNumber) {
        this.name = name;
        this.city = city;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
