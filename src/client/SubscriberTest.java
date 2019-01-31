package client;

import menu.Menu;
import menu.Subscriber;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubscriberTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Subscriber> mySubscriber = new ArrayList<>();

        Socket socketConnection = new Socket("127.0.0.1", 8080);

        ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
        ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
        try {
            mySubscriber = (ArrayList<Subscriber>) clientInputStream.readObject();
            for (Subscriber e : mySubscriber) {
                System.out.println("Subscriber" + e);
            }
            Menu menuObj = new Menu ();
            //menuObj.readFile ();
            menuObj.LoginPassword ();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscriberTest.class.getName()).log( Level.SEVERE, null, ex);
        } finally {
            clientOutputStream.close();
            clientInputStream.close();
        }
    }


}
