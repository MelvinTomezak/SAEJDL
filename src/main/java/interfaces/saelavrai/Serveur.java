/**

 Cette classe implémente un serveur qui écoute les connexions entrantes sur un port spécifié et crée des threads de traitement
 pour chaque client connecté.
 */
package interfaces.saelavrai;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Serveur {
    /**
     * Port d'écoute du serveur.
     */
    private static final int PORT = 8080;
    /**
     * Pool de threads pour le traitement des clients.
     */
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * Socket client.
     */
    public Socket clientSocket;

    /**
     * Constructeur de la classe Serveur.
     *
     * @param clientSocket Socket client.
     */
    public Serveur(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Méthode qui sera exécutée lorsqu'un client se connecte.
     */
    public void run() {
        // Implementation laissée vide pour le moment.
    }

    /**
     * Méthode principale qui crée un socket serveur et écoute les connexions entrantes.
     *
     * @param args Arguments de la ligne de commande (non utilisés dans cette implémentation).
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la création du socket serveur.
     */
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