package interfaces.saelavrai;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cette classe représente un serveur, qui est chargé de gérer les connexions entrantes.
 * Elle contient une méthode statique fetchClients(), qui permet de gérer les connexions entrantes et de les ajouter au tableau Connections.
 * @author Les Avanturiers
 */
public class Serveur {
    public static int conSize=0;
    static String[] Connections=new String[4];
    /**
     * La méthode main() du serveur, qui crée une socket et écoute sur le port 6666.
     * @param args Les arguments
     */
    public static void main(String[] args){
        try{
            ServerSocket ss=new ServerSocket(6666);
            Socket s=ss.accept();//establishes connection
            DataInputStream dis=new DataInputStream(s.getInputStream());
            String  str=(String)dis.readUTF();
            System.out.println("message= "+str);
            ss.close();
        }catch(Exception e){System.out.println(e);}
    }
    /**
     * Cette méthode permet d'extraire les informations des clients et de les ajouter au tableau Connections.
     */
    static public void fetchClients(){
        boolean exit=false;
        Scanner sc=new Scanner(System.in);
        System.out.println("\n -> ");
        Connections[0]=sc.nextLine();
        int index=0;
        Scanner scc=new Scanner(System.in);
        System.out.println("\n -> ");
        Connections[1]=scc.nextLine();
        index++;
        System.out.println("\n Type 1 to exit, 2 to continue: ");
        if(scc.nextInt()!=1){
            System.out.println("\n -> ");
            Scanner sc1=new Scanner(System.in);
            Connections[2]=sc1.nextLine();
            index++;
            System.out.println("\n Type 1 to exit, 2 to continue: ");
            if(sc1.nextInt()!=1){
                System.out.println("\n -> ");
                Scanner sc2=new Scanner(System.in);
                Connections[3]=sc2.nextLine();
                index++;
                System.out.println("\n Type 1 to exit, 2 to continue: ");
                if(scc.nextInt()!=1){
                    System.out.println("\n -> ");
                    Connections[4]=scc.nextLine();
                }

            }
        }
        conSize=index+1;
    }
}
