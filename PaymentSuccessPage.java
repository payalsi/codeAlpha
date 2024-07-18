package com.hotelreservationsystem;

import javax.swing.*;
import java.awt.*;

public class PaymentSuccessPage extends JFrame {
    private JLabel successLabel;
    private JTextArea paymentDetailsTextArea;

    public PaymentSuccessPage(String upiId, String amountPaid) {
        setTitle("Payment Successful");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("/Users/macbook/Desktop/hotelreservationsystem/src/hotel4.jpeg").getImage());

        JPanel successPanel = new JPanel();
        successPanel.setLayout(new GridBagLayout());
        successPanel.setBackground(new Color(0, 0, 0, 200));
        successPanel.setPreferredSize(new Dimension(400, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color textColor = Color.WHITE; // Text color is white


        successLabel = new JLabel("Payment successful!");
        successLabel.setFont(font);
        successLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        successPanel.add(successLabel, gbc);


        paymentDetailsTextArea = new JTextArea();
        paymentDetailsTextArea.setFont(font);
        paymentDetailsTextArea.setForeground(textColor);
        paymentDetailsTextArea.setBackground(new Color(0, 0, 0, 200));
        paymentDetailsTextArea.setText("UPI ID: " + upiId + "\nAmount Paid: " + amountPaid + " rupees");
        paymentDetailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(paymentDetailsTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        successPanel.add(scrollPane, gbc);


        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.add(successPanel, new GridBagConstraints());


        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentSuccessPage("example@upi", "1000").setVisible(true);
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
