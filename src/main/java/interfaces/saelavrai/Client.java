package interfaces.saelavrai;

import java.io.*;
import java.net.*;

/**
 * Classe Client
 * @author Les Avanturiers
 */
public class Client {

    /**
     * Méthode principale du client
     * @param args arguments du main
     */
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("Hello Server");
            dout.flush();
            dout.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
