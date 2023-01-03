package interfaces.saelavrai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    /**
     * @param args
     */
    private int port;
    int nbClients;

    public Serveur(int port, int nbClients) {
        this.port = port;
        this.nbClients = nbClients;

    }

    public void lancer() throws IOException {

        ServerSocket serveur = new ServerSocket(port);

        System.out.println("Serveur TCP Echo Multi-Threads lanc� sur le port " + port);

        for (int i = 1; i <= nbClients; i++) {
            Socket client = serveur.accept();

            TaskServeur task = new TaskServeur(client);
            new Thread(task).start();

        }

        serveur.close();

    }

    public static void main(String[] args) throws IOException {

        Serveur serveur = new Serveur(10007, 100000);
        serveur.lancer();

    }

}