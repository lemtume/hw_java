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


            int choice;
            int ignore;

            // ArrayList<Services> ServicesList = new ArrayList<Services> ();

            Menu menuObj = new Menu ();
            //menuObj.readFile ();
            menuObj.LoginPassword ();

            for (int LoginAttemp = 2; LoginAttemp >= 0; LoginAttemp--) {         // цикл по проверке количества неудачных входов
                label:
                do {

                    if (menuObj.LoginInput () == true) {                    // если вход произведён

                        for (; ; ) {

                            do {
                                menuObj.showMenu ();

                                choice = (char) System.in.read ();

                                do {
                                    ignore = (char) System.in.read (); // игнор ввода

                                } while (ignore != '\n');
                            }
                            while (menuObj.isValid ( choice ));//while (!choice.hasNextInt()); //
                            if (choice == 'q') {
                                LoginAttemp = 0;
                                break /*label*/;
                            }

                            System.out.println ( "\n" );
                            menuObj.helpOn ( choice );
                        }
                    }
                    System.out.println ( "Осталось попыток: " + LoginAttemp + "\n\n" );
                    break label;
                }
                while (menuObj.LoginInput () != true);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubscriberTest.class.getName()).log( Level.SEVERE, null, ex);
        } finally {
            clientOutputStream.close();
            clientInputStream.close();
        }
    }


}
