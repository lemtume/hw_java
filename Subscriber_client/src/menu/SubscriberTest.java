package menu;

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

        ////////////

        String SubscriberID=null;
        String SubscriberName = null;
        String SubscriberLastName = null;
        String SubscriberAgreementNumber = null;
        String SubscriberLogin = null;
        String SubscriberPassword = null;
        String startDate = null;
        String endDate = null;
        double feeRate = 0;
        double paid = 0;

        Subscriber obj;
        obj = new Subscriber (SubscriberID, SubscriberName, SubscriberLastName, SubscriberAgreementNumber,
                SubscriberLogin, SubscriberPassword, startDate, endDate, feeRate, paid );


        try {
            mySubscriber = (ArrayList<Subscriber>) clientInputStream.readObject();
            for (Subscriber e : mySubscriber) {
                obj.setSubscriberID ( e.getSubscriberID () );
                obj.setSubscriberName ( e.getSubscriberName () );
                obj.setSubscriberLastName ( e.getSubscriberLastName () );
                obj.setSubscriberAgreementNumber ( e.getSubscriberAgreementNumber () );
                obj.setSubscriberLogin ( e.getSubscriberLogin () );
                obj.setSubscriberPassword ( e.getSubscriberPassword () );
                obj.setStartDate ( e.getStartDate () );
                obj.setEndDate ( e.getEndDate () );
                obj.setFeeRate ( e.getFeeRate () );
                obj.setPaid ( e.getPaid () );

                System.out.println (obj.SubscriberID + obj.SubscriberName + obj.SubscriberLastName
                + obj.SubscriberAgreementNumber + obj.SubscriberLogin + obj.SubscriberPassword + obj.startDate + obj.endDate
                        + obj.feeRate + obj.paid );

                Menu menuObj = new Menu ();
                //menuObj.readFile ();
                menuObj.LoginPassword (obj.SubscriberID, obj.SubscriberName, obj.SubscriberLastName, obj.SubscriberAgreementNumber,
                        obj.SubscriberLogin, obj.SubscriberPassword, obj.startDate, obj.endDate, obj.feeRate, obj.paid);





                int choice;
                int ignore;

                for (int LoginAttemp = 2; LoginAttemp >= 0; LoginAttemp--) {         // цикл по проверке количества неудачных входов

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

                                  //  clientOutputStream.writeObject(mySubscriber);
                                    LoginAttemp = 0;
                                    break;
                                }

                                System.out.println ( "\n" );
                                menuObj.helpOn ( choice );
                            }
                        }
                        System.out.println ( "Осталось попыток: " + LoginAttemp + "\n\n" );
                        break;
                    }
                    while (menuObj.LoginInput () != true);
                }

                ArrayList<Subscriber> Sub1 = new ArrayList<Subscriber> (  );
                System.out.println (menuObj.getSubList () );
                Sub1 = menuObj.getSubList ();

                clientOutputStream.writeObject(Sub1);

            }


          //  System.out.println (d.getSubscriberName ());
          /*
            ArrayList<Subscriber> Sub1 = new ArrayList<> (  );
            Menu menupointer=new Menu ();
            System.out.println (menupointer.getSubList () );
            Sub1 = menupointer.getSubList ();

            clientOutputStream.writeObject(Sub1);
            */






        } catch (ClassNotFoundException ex) {
            Logger.getLogger( SubscriberTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            clientOutputStream.close();
            clientInputStream.close();
        }

    }


}
