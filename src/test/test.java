package test;

import menu.Subscriber;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


class test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


      /*  SubList.add ( obj );

        for (Subscriber dr : SubList) {//с помощью цикла for each c ArrayList читаем поля поочередно сохраняя с переменную dr
            dr.showSubscriberInfo ();
            System.out.println ( "Размер ArrayList SubList:   " + SubList.size () );
        }
        try {
            FileOutputStream fileOut1 = new FileOutputStream ( "subscriber.ser", true );
            ObjectOutputStream out1 = new ObjectOutputStream ( fileOut1 );
            out1.writeObject ( SubList );
            out1.flush();
            out1.close ();
            fileOut1.close ();
            System.out.println ( "Сериализованная информация о Subscriber сохранена в D:\\GIT\\subscriber.ser" + SubList.size () );
        } catch (IOException i) {
            i.printStackTrace ();
        }


        if (input.nextLine ().equals ( "q" )) {
            System.out.println ( "Введите q чтобы попасть назад: " );
            break;

        }
        System.out.println ( "\tНажмите ВВОД, чтобы продолжить" );
        */


        Subscriber SubscriberPoint = new Subscriber ( "1", "Роман", "Лемтюгов", "TS123123",
                "Roman", "123", "01.01.2018", "31.12.2018", 30, 20 );

        Subscriber SubscriberPoint2 =new Subscriber ( "2", "Роман2", "Лемтюгов2", "123",
                "Roman2", "123", "01.01.2018", "31.12.2018", 10, 20 );



        ArrayList<Subscriber> SubList = new ArrayList<> ();
        SubList.add ( SubscriberPoint );
        SubList.add ( SubscriberPoint2);



    /*    for (Subscriber dr : SubList) {//с помощью цикла for each c ArrayList читаем поля поочередно сохраняя с переменную dr
            dr.showSubscriberInfo ();
            System.out.println ( "Размер ArrayList SubList:   " + SubList.size () );
        }
        try {
            FileOutputStream fos = new FileOutputStream ( "subscriber.ser" );
            ObjectOutputStream oos = new ObjectOutputStream ( fos );
            oos.writeObject ( SubList );
            oos.flush ();
            oos.close ();
            System.out.println ( "Сериализованная информация о Subscriber сохранена в D:\\GIT\\temp2.txt" + SubList.size () );
        } catch (IOException i) {
            i.printStackTrace ();
        }
  */

        FileInputStream fis = new FileInputStream ( "temp2.txt" );
        ObjectInputStream oin = new ObjectInputStream ( fis );
        ArrayList<Subscriber> arrayList2 = new ArrayList<> ();
        arrayList2 = (ArrayList<Subscriber>) oin.readObject ();
        arrayList2.addAll ( SubList );

        for(int i = 0 ; i< arrayList2.size() ;i++) {
            System.out.println("Name:" + arrayList2.get(i).getSubscriberName () + "Year:" +    arrayList2.get(i).getStartDate ());
        }

    }
}








