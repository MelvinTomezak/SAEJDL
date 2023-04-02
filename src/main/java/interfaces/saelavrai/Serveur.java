package interfaces.saelavrai;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Serveur {
    private static final int PORT = 8080;
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);
    public Socket clientSocket;

    public Serveur(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    public void run() {
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket);

            Runnable clientHandler = new Client(clientSocket);
            pool.execute(clientHandler);
        }
    }
}
