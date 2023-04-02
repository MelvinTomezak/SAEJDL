package interfaces.saelavrai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private final Socket clientSocket;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message from client " + clientSocket + ": " + inputLine);
                out.println("Server: " + inputLine);
            }

            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket);
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}