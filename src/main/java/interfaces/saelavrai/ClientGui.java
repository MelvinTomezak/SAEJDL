package interfaces.saelavrai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ClientGui implements ActionListener {
    private final JTextField messageField;
    private final JFrame frame;
    private final JTextArea messageArea;
    private final JButton connectButton;
    private final JScrollPane scrollPane;
    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    private final boolean isRunning = false;
    private Socket socket; // ajout d'un champ socket pour stocker la connexion au serveur

    public ClientGui() {
        frame = new JFrame("Client GUI");

        messageArea = new JTextArea(20, 50);
        messageArea.setEditable(false);

        scrollPane = new JScrollPane(messageArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        connectButton = new JButton("Connect");
        connectButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connectButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        messageField = new JTextField(30);

        JPanel messagePanel = new JPanel();
        messagePanel.add(messageField);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                sendMessage(message);
                messageField.setText("");
            }

            private void sendMessage(String message) {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(message);
                    messageArea.append("Sent message to server: " + message + "\n");
                } catch (IOException e) {
                    messageArea.append("Error sending message to server: " + e.getMessage() + "\n");
                }

            }
        });
        messagePanel.add(sendButton);

        frame.getContentPane().add(messagePanel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == connectButton) {
            connectToServer();
        }
    }

    private void connectToServer() {
        String ipAddress = JOptionPane.showInputDialog(frame, "Enter the IP address of the server:");
        if (ipAddress != null && !ipAddress.isEmpty()) {
            try {
                socket = new Socket(ipAddress, 8080); // stocke la connexion au serveur dans le champ socket
                messageArea.append("Connected to server at " + ipAddress + "\n");

                Serveur serverHandler = new Serveur(socket);
                Thread thread = new Thread(String.valueOf(serverHandler));
                thread.start();
            } catch (IOException e) {
                messageArea.append("Failed to connect to server: " + e.getMessage() + "\n");
            }
        }
    }
}
