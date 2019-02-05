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

                ///////////////// Чтение сериализационного файла и добавление в arrayList, отправка на клиента
             ///*
                String SubscriberId = null;
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
                obj = new Subscriber (SubscriberId, SubscriberName, SubscriberLastName, SubscriberAgreementNumber, SubscriberLogin, SubscriberPassword, startDate, endDate, feeRate, paid );

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

                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    objectOutputStream.writeObject(arrayList);
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
                    System.out.println (sb2.SubscriberName + sb2.SubscriberLastName);

                try {
                    FileOutputStream fileOut = new FileOutputStream("subscriber.ser");
                    ObjectOutputStream out = new ObjectOutputStream ( fileOut );
                    out.writeObject ( sb2 );
                    out.close ();
                    fileOut.close ();
                    System.out.println ("subscriber.cer записан");


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

