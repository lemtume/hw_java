package menu;

import sun.security.util.Password;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SubscriberTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Subscriber> mySubscriber = new ArrayList<>();

        Socket socketConnection = new Socket("127.0.0.1", 8080);

        ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
        ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());

        /////////////
        mySubscriber = (ArrayList<Subscriber>) clientInputStream.readObject();

        ////////////

        Menu menuObj = new Menu ();

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

        int idNumber = 0;

        try {

                int choice;
                int ignore;

                for (int LoginAttemp = 2; LoginAttemp >= 0; LoginAttemp--) {         // цикл по проверке количества неудачных входов
                    Scanner input1 = new Scanner ( System.in );
                    System.out.println ( "Введите Логин пользователя" );
                    String username = input1.next ();

                    Scanner input2 = new Scanner ( System.in );
                    System.out.println ( "Введите пароль " );
                    String password = input2.next ();

                    do {

                        for (int j=0; j<mySubscriber.size ();j++)
                            if (mySubscriber.get ( j ).getSubscriberLogin ().equals ( username ) && mySubscriber.get ( j ).getSubscriberPassword ().equals ( password)) {
                                obj.SubscriberID = mySubscriber.get ( j ).getSubscriberID ();
                                obj.SubscriberName = mySubscriber.get ( j ).getSubscriberName ();
                                obj.SubscriberLastName = mySubscriber.get ( j ).getSubscriberLastName ();
                                obj.SubscriberAgreementNumber = mySubscriber.get ( j ).getSubscriberAgreementNumber ();
                                obj.SubscriberLogin = mySubscriber.get ( j ).getSubscriberLogin ();
                                obj.SubscriberPassword = mySubscriber.get ( j ).getSubscriberPassword ();
                                obj.startDate = mySubscriber.get ( j ).getStartDate ();
                                obj.endDate = mySubscriber.get ( j ).getEndDate ();
                                obj.feeRate = mySubscriber.get ( j ).getFeeRate ();
                                obj.paid = mySubscriber.get ( j ).getPaid ();

                                menuObj.LoginPassword ( obj.SubscriberID, obj.SubscriberName, obj.SubscriberLastName, obj.SubscriberAgreementNumber,
                                        obj.SubscriberLogin, obj.SubscriberPassword, obj.startDate, obj.endDate, obj.feeRate, obj.paid );
                                idNumber=j;
                            }

                        if (menuObj.LoginInput ( username, password ) == true) {                    // если вход произведён

                            for (; ; ) {                       // бесконечный цикл пока не достигнут break

                                do {
                                    menuObj.showMenu ();

                                    choice = (char) System.in.read ();




                                    do {
                                        ignore = (char) System.in.read (); // игнор ввода

                                    } while (ignore != '\n');

                                }
                                while (menuObj.isValid ( choice ));//while (!choice.hasNextInt()); //
                                if (choice == 'q') {
////////////////////// отправка измененного листа на Сервер



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
                    while (menuObj.LoginInput ( username, password  ) != true);
                }


            ArrayList<Subscriber> Sub1 = new ArrayList<Subscriber> (  );
            System.out.println (menuObj.getSubList () );
            Sub1 = menuObj.getSubList ();
            // в принятый arraylist вносим изменения из array-list который сформирован в меню
            mySubscriber.get ( idNumber ).setSubscriberID (Sub1.get(0).SubscriberID);
            mySubscriber.get ( idNumber ).setSubscriberName ( Sub1.get(0).SubscriberName);
            mySubscriber.get ( idNumber ).setSubscriberLastName ( Sub1.get ( 0 ).SubscriberLastName );
            mySubscriber.get ( idNumber ).setSubscriberAgreementNumber ( Sub1.get ( 0 ).SubscriberAgreementNumber );
            mySubscriber.get ( idNumber ).setSubscriberLogin (Sub1.get ( 0 ).SubscriberLogin);
            mySubscriber.get ( idNumber ).setSubscriberPassword ( Sub1.get ( 0 ).SubscriberPassword);
            mySubscriber.get ( idNumber ).setStartDate ( Sub1.get ( 0 ).startDate );
            mySubscriber.get ( idNumber ).setEndDate ( Sub1.get ( 0 ).endDate );
            mySubscriber.get(idNumber).setFeeRate (Sub1.get ( 0 ).feeRate);
            mySubscriber.get ( idNumber ).setPaid ( Sub1.get(0).feeRate);
            clientOutputStream.writeObject(mySubscriber);





        //    }









        } finally {
            clientOutputStream.close();
            clientInputStream.close();
        }

    }


}
