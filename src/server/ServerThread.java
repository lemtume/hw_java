package server;

import menu.Subscriber;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {

    ObjectInputStream serverInputStream = null;
    ObjectOutputStream serverOutputStream = null;
    ArrayList<Subscriber> mySubscriber = new ArrayList<Subscriber>();
    String SubsriberId;
    Socket clientSocket;

    public ServerThread(Socket clientSocket) throws IOException, FileNotFoundException, ClassNotFoundException {


        Subscriber d = null;

        try {
            FileInputStream fileIn = new FileInputStream ( "subscriber.ser" );
            ObjectInputStream in = new ObjectInputStream ( fileIn );
            d = (Subscriber) in.readObject ();
            in.close ();
            fileIn.close ();
        } catch (IOException i) {
            i.printStackTrace ();
            return;
        } catch (ClassNotFoundException cl) {
            System.out.println ( "Subscriber класс не найден" );
            cl.printStackTrace ();
            return;
        }
        ////////////


        ArrayList<Subscriber> arrayList =  new ArrayList<Subscriber>();
        arrayList.add(d);

        this.clientSocket = clientSocket;



    }
    @Override
    public void run() {
        try {
            serverInputStream = new ObjectInputStream(clientSocket.getInputStream());
            serverOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());


         //   serverOutputStream.writeObject(d);



        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ArrayList<Subscriber> readFromFile(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Subscriber> mySubscriber = new ArrayList<>();
        FileInputStream fin = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fin);
        while (true) {
            try {
                mySubscriber.add((Subscriber) ois.readObject());
            } catch (EOFException e) {
                return mySubscriber;
            }
        }

    }

    public static void writeToFile(String filename, ArrayList<Subscriber> mySubscriber) throws IOException {
        final FileOutputStream fos = new FileOutputStream(filename);
        //fos.
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Subscriber e : mySubscriber) {
            oos.writeObject(e);
        }
    }
}