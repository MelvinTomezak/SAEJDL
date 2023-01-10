package interfaces.saelavrai;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

public class Client {

    String hostname;
    int port;
    Socket socketClient;

    BufferedWriter out;

    AccueilMain accueilMain = new AccueilMain();

    public Client(String hostname, int port) throws IOException {
        this.hostname = hostname;
        this.port = port;
    }


    public class ClientReceiverThread extends Thread {

        private BufferedReader reader;

        public ClientReceiverThread(Client client) throws IOException {
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        }



        @Override
        public void run() {
            try {
                String message;
                System.out.println("Waiting for a response");
                while ((message = reader.readLine()) != null) {
                    // convert a JSON string to Java Map
                    Map<String, Object> map = new ObjectMapper().readValue(message, Map.class);
                    System.out.println("Receive: " + map);

                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        System.out.println(entry.getKey() + "=" + entry.getValue());
                    }

                    /*
                        {
                            "type": "JOIN/QUIT/DICE/...",
                            ("data": Map<String, String>)?
                        }

                        {
                            "type": "JOIN",
                            "data": {
                                "name": "Henry
                            }
                        }

                     */
                    if(!map.containsKey("type")) {
                        continue;
                    }
                    switch ((String) map.get("type")) {
                        case "JOIN":
                            if(map.containsKey("data"))
                                onJoin((Map<String, String>) map.get("data"));
                            break;
                        case "QUIT":
                            if(map.containsKey("data"))
                                onQuit((Map<String, String>) map.get("data"));
                            break;
                        case "DICE":
                            if(map.containsKey("data"))
                                onDice((Map<String, String>) map.get("data"));
                            break;
                    }
                    //TODO: Réaliser les actions en conséquences
                }
            } catch (Exception e) {
                System.err.println("Unable to translate ingoing message: " + e.getMessage());
            }
        }
    }

    private void sendMessage(Map<String, Object> message) throws IOException {
        String text = new ObjectMapper().writeValueAsString(message);
        System.out.println("Send data : " + text);
        // envoie le message vers le serveur
        out.write(text);
        // rajouter un saut de ligne pour pouvoir utiliser readline c�t� serveur
        out.newLine();
        // vider le buffer
        out.flush();
    }

    public void onJoin(Map<String, String> data) {
        System.out.println(data.get("name") + " à rejoint la partie");
    }

    public void onQuit(Map<String, String> data) {
        System.out.println(data.get("name") + " à quitté la partie");
    }

    public void onDice(Map<String, String> data) {
        if(!data.containsKey("name")) //TODO: Vérifier le contenu de data
            return;
        System.out.println(data.get("name") + " a obtenu " + data.get("value") + " au dé!");
    }

    public void init() throws UnknownHostException, IOException {

        System.out.println("Connexion à " + hostname);
        socketClient = new Socket(hostname, port);
        out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        new ClientReceiverThread(this).start();


        sendMessage(Map.of("type", "JOIN",
                "data", Map.of("name", "Slezak")));
        while (true) {
            //TODO: Faire la logic client
        }
        /*
            Connexion au serveurs
            -> JOIN P1
            -> JOIN P2
            -> JOIN P3
            -> JOIN P4
            <- START
            -> START (x4)

            while (pas de gagnant) (
            -> INFO (x4)
            <- DICE
            -> DICE 6
            -> INFO (x4)
            -> QUESTION
            <- ANSWER
            )

            -> WIN
            -> LOSE (x3)
         */

        /*in.close();
        out.close();
        clavier.close();
        socketClient.close();
        */
    }

    public static void main(String[] args) throws UnknownHostException, IOException {

        Client client = new Client("127.0.0.1", 10007);
        client.init();

    }


}
