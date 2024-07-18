import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.ApiException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

public class ChatbotGUI {
    private static final String PROJECT_ID = "chatbot-kpqd"; // Use your project ID from the JSON file
    private static final String CREDENTIALS_PATH = "/Users/macbook/Downloads/chatbot-kpqd-846799a46a16.json"; // Path to your service account key file

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Chatbot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Set initial size
        frame.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(52, 152, 219)); // Set header background color

        // Load and resize logo image
        ImageIcon logoIcon = new ImageIcon("/Users/macbook/Desktop/chatbot/src/chatbot.png"); // Adjust path if necessary
        if (logoIcon.getIconWidth() > 0 && logoIcon.getIconHeight() > 0) {
            Image logoImage = logoIcon.getImage(); // Transform it
            Image resizedLogoImage = logoImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // Scale it to desired size
            ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage); // Transform it back

            JLabel logoLabel = new JLabel(resizedLogoIcon);
            headerPanel.add(logoLabel);
        } else {
            System.out.println("Image not found at the specified path.");
        }

        JLabel headerLabel = new JLabel("Chatbot");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Chat area
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextField userInputField = new JTextField();
        userInputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputPanel.add(userInputField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Arial", Font.BOLD, 16));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = userInputField.getText().trim();
                if (!userInput.isEmpty()) {
                    chatArea.append("You: " + userInput + "\n");
                    String response = generateResponse(userInput);
                    chatArea.append("Bot: " + response + "\n");
                    userInputField.setText("");
                }
            }
        });
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Footer panel

        // Add inputPanel to frame after footerPanel to ensure it's above the footer
        frame.add(inputPanel, BorderLayout.PAGE_END);

        frame.setVisible(true); // Set frame visible after adding all components
    }

    private static String generateResponse(String input) {
        String responseText = "";
        try {
            // Load credentials from file
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH));
            SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                    .build();
            try (SessionsClient sessionsClient = SessionsClient.create(sessionsSettings)) {
                String sessionId = UUID.randomUUID().toString();
                String session = String.format("projects/%s/agent/sessions/%s", PROJECT_ID, sessionId);

                // Set the text (user input) and language code (en-US) for the query
                TextInput.Builder textInput = TextInput.newBuilder().setText(input).setLanguageCode("en-US");
                QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

                // Detect intent
                DetectIntentResponse queryResult = sessionsClient.detectIntent(session, queryInput);
                responseText = queryResult.getQueryResult().getFulfillmentText();
            }
        } catch (IOException | ApiException e) {
            e.printStackTrace();
            responseText = "Sorry, I couldn't process that.";
        }

        return responseText;
    }
}
