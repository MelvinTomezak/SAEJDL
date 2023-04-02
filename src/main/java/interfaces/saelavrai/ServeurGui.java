package interfaces.saelavrai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServeurGui implements ActionListener {
    private final JTextField messageField;
    private final JFrame frame;
    private final JTextArea messageArea;
    private final JButton startButton;
    private final JButton stopButton;
    private JScrollPane scrollPane;
    private ServerSocket serverSocket;
    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    private boolean isRunning = false;

    public ServeurGui() {
        frame = new JFrame("Server GUI");

        messageArea = new JTextArea(20, 50);
        messageArea.setEditable(false);

        scrollPane = new JScrollPane(messageArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(this);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        messageField = new JTextField(30);

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
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == startButton) {
            startServer();
        } else if (event.getSource() == stopButton) {
            stopServer();
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
}