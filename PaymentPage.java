package com.hotelreservationsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPage extends JFrame {
    private JLabel amountLabel;
    private JLabel upiLabel;
    private JTextField upiTextField;
    private JTextField amountTextField;
    private JButton doneButton;

    private int amount;

    public PaymentPage(int amount) {
        this.amount = amount;

        setTitle("Payment Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("/Users/macbook/Desktop/hotelreservationsystem/src/hotel2.jpeg").getImage());


        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridBagLayout());
        paymentPanel.setBackground(new Color(0, 0, 0, 200));
        paymentPanel.setPreferredSize(new Dimension(400, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font font = new Font("Arial", Font.PLAIN, 14);
        Color textColor = Color.WHITE;


        amountLabel = new JLabel("Pay Online " + amount + " rupees");
        amountLabel.setFont(font);
        amountLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        paymentPanel.add(amountLabel, gbc);


        upiLabel = new JLabel("Enter UPI ID:");
        upiLabel.setFont(font);
        upiLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        paymentPanel.add(upiLabel, gbc);

        upiTextField = new JTextField();
        upiTextField.setFont(font);
        upiTextField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        paymentPanel.add(upiTextField, gbc);


        JLabel payAmountLabel = new JLabel("Enter Amount:");
        payAmountLabel.setFont(font);
        payAmountLabel.setForeground(textColor);
        gbc.gridx = 0;
        gbc.gridy = 2;
        paymentPanel.add(payAmountLabel, gbc);

        amountTextField = new JTextField(String.valueOf(amount));
        amountTextField.setFont(font);
        amountTextField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        paymentPanel.add(amountTextField, gbc);


        doneButton = new JButton("Done");
        doneButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        paymentPanel.add(doneButton, gbc);


        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.add(paymentPanel, new GridBagConstraints());


        setContentPane(backgroundPanel);


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSuccessPage();
            }
        });
    }

    private void openSuccessPage() {
        String upiId = upiTextField.getText();
        String amountPaid = amountTextField.getText();
        PaymentSuccessPage successPage = new PaymentSuccessPage(upiId, amountPaid);
        successPage.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentPage(1000).setVisible(true);
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
