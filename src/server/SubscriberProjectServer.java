package server;

import menu.Subscriber;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SubscriberProjectServer {

    public static void main(String[] arg) throws IOException, FileNotFoundException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is waiting on " + serverSocket.getInetAddress());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client has connected");
                //создание отдельного потока для обмена данными с соединившимся клиентом

                ServerThread thread = new ServerThread(clientSocket);
                // запуск потока
               //  thread.start();



                try {

                    FileInputStream fis = new FileInputStream("subscriber.txt");
                    ObjectInputStream oin = new ObjectInputStream(fis);
                    ArrayList<Subscriber> arrayList3 = new ArrayList<>();

                    arrayList3  = (ArrayList<Subscriber>) oin.readObject();



                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                    objectOutputStream.writeObject(arrayList3);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                /////*/

                /////////////////// Приём arrayList с клиента и запись в файл /////////////////
                ObjectInputStream serverInputStream = new ObjectInputStream ( clientSocket.getInputStream() );
                ArrayList<Subscriber> arrayList2 =  new ArrayList<>();
                arrayList2 = (ArrayList<Subscriber>) serverInputStream.readObject ();

                for (Subscriber sb2: arrayList2) {
                    sb2.showSubscriberInfo ();

                try {
                    FileOutputStream fileOut = new FileOutputStream("subscriber.txt");
                    ObjectOutputStream out = new ObjectOutputStream ( fileOut );
                    out.writeObject ( arrayList2 );
                    out.close ();
                    fileOut.close ();
                    System.out.println ("subscriber.txt записан");


                } catch (IOException i) {
                    i.printStackTrace ();
                }
            }
                /////////////////////////////////////

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

