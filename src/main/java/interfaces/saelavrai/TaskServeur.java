package interfaces.saelavrai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
/**
 * Cette classe implémente l'interface Runnable et sert à gérer une connexion client.
 * @author Les Avanturiers
 */
public class TaskServeur implements Runnable {

    /**
     * Socket client
     */
    private Socket client;

    /**
     * Constructeur de la classe
     *
     * @param client Socket client à gérer
     */
    public TaskServeur(Socket client) {
        this.client = client;
    }

    /**
     * Cette méthode est exécutée lorsque la tâche est lancée. Elle reçoit des données du client, les affiche dans la console et envoie des données au client
     */
    public void run() {
        String chaine;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String buf;
            while ((buf = in.readLine())!= null)
                System.out.println(buf);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            while ((chaine = in.readLine()) != null) {
                if (chaine.equals("quit"))
                    break;
            }
            out.close();
            in.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}