package interfaces.saelavrai;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import static java.lang.System.out;


public class Serveur {

    private static int NB_PLAYER = 4;
    Socket socketServeur;
    Socket socketLeDe;
    Socket socketPlateau;

    /**
     * @param args
     */

    private int port;

    public Serveur(int port) throws IOException {
        this.port = port;

    }

    //TODO: Identifier quels sont les joueurs/clients (identifier qui est l'admin?)
    //TODO: Pouvoir lancer des actions sur un client
    //TODO: Créer une faux client-socket ou faire un vrai socket
    //TODO: Descriptif des actions pouvant être exécuté
    //Par qui ?

    /*Actions:
        [ADMIN]
        * Lance la partie -> START (Sortante)

        |
        V

        [JOUEUR]
        * Accepte l'invitation -> Connexion au socket (Entrante)
        * Rentre dans la partie -> Join (Sortante)
        * Lance le dé -> Dice (Entrante)
        * Reçois son lancé de dé -> Dice <value> (Sortante)
        * Reçois une question -> QUESTION <question> (Sortante)
        * Réponds à la question -> ANSWER <answer> (Entrante)
        * Reçois une nouvelle position d'un joueur -> INFO (Sortante)
        * Quitte la partie -> Quit (Fermeture du socket) (Sortante)
        * Victoire -> Win (Sortante)
        * Defaite -> Lose (Sortante)
     */

    public class ServveurReceiverThread extends Thread {

        private BufferedReader reader;

        public ServveurReceiverThread(Serveur serveur) throws IOException{
            reader = new BufferedReader(new InputStreamReader(socketServeur.getInputStream()));
        }


        @Override
        public void run(){

        }


    }
    public class LeDeReceiverThread extends Thread{
        private BufferedReader reader;

        public LeDeReceiverThread (LeDe lede) throws IOException{
            reader = new BufferedReader(new InputStreamReader(socketLeDe.getInputStream()));
        }
    }
    public class PlateaureceiverThread extends Thread{
        private BufferedReader reader;

        public PlateaureceiverThread (Plateau plateau) throws IOException{
            reader = new BufferedReader(new InputStreamReader(socketPlateau.getInputStream()));
        }
    }


    public synchronized void init() throws IOException {

        ServerSocket serveur = new ServerSocket(port);
        out.println("Serveur TCP Echo lanc� sur le port " + port);
        for (int i = 1; i <= NB_PLAYER; i++) {
            out.println("Waiting for " + (NB_PLAYER-i+1) + "players");
            Socket socketClient = serveur.accept();
            out.println("New client ! ");
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            out.write("JOIN");
            out.newLine();
            out.flush();
        }
        serveur.close();
    }


    /*
        [WAIT]
        Connexion d'un client
        <- Join P1
        [WAIT]
        Connexion d'un client
        <- Join P2
        [WAIT]
        Connexion d'un client
        <- Join P3
        [WAIT]
        Connexion d'un client
        <- Join P4
        [WAIT de P1]
        -> START
        <- START (x4)

        -> QUIT
        while (pas de gagnant) (
        <- INFO (x4)
        [WAIT de joueur en cours]
        -> DICE
        <- DICE 6 (x4)
        <- INFO (x4)
        <- QUESTION
        [WAIT]
        -> ANSWER
        )

        <- WIN
        <- LOSE


     */


    //publi



    public static void main(String[] args) throws IOException {

        Serveur serveur = new Serveur(10007);
        serveur.init();

    }

}

