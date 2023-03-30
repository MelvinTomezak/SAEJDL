package interfaces.saelavrai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServeurGui implements ActionListener {
    private JTextField messageField;
    private JFrame frame;
    private JTextArea messageArea;
    private JButton startButton, stopButton, connectButton;
    private JScrollPane scrollPane;
    private ServerSocket serverSocket;
    private ExecutorService pool = Executors.newFixedThreadPool(10);
    private boolean isRunning = false;

    public ServeurGui() {
        frame = new JFrame("Server GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messageArea = new JTextArea(20, 50);
        messageArea.setEditable(false);

        scrollPane = new JScrollPane(messageArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(this);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);

        connectButton = new JButton("Connect");
        connectButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
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
                Socket socket = new Socket();
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
        if (event.getSource() == startButton) {
            startServer();
        } else if (event.getSource() == stopButton) {
            stopServer();
        } else if (event.getSource() == connectButton) {
            connectToServer();
        }
    }

    private void startServer() {
        if (!isRunning) {
            try {
                serverSocket = new ServerSocket(8080);
                messageArea.append("Server started on port 8080\n");
                isRunning = true;
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            } catch (IOException e) {
                messageArea.append("Failed to start server: " + e.getMessage() + "\n");
            }

            Thread serverThread = new Thread(new Runnable() {
                public void run() {
                    while (isRunning) {
                        try {
                            Socket clientSocket = serverSocket.accept();
                            messageArea.append("New client connected: " + clientSocket + "\n");

                            Runnable clientHandler = new Client(clientSocket);
                            pool.execute(clientHandler);
                        } catch (IOException e) {
                            messageArea.append("Error handling client: " + e.getMessage() + "\n");
                        }
                    }
                }
            });
            serverThread.start();
        }
    }

    private void stopServer() {
        if (isRunning) {
            isRunning = false;
            try {
                serverSocket.close();
                pool.shutdown();
                messageArea.append("Server stopped\n");
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            } catch (IOException e) {
                messageArea.append("Failed to stop server: " + e.getMessage() + "\n");
            }
        }
    }

    private void connectToServer() {
        String ipAddress = JOptionPane.showInputDialog(frame, "Enter the IP address of the server:");
        if (ipAddress != null && !ipAddress.isEmpty()) {
            try {
                Socket socket = new Socket(ipAddress, 8080);
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
