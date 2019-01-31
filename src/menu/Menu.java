package menu;

import java.io.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements Serializable {


    ArrayList<Subscriber> SubList = new ArrayList<Subscriber> ();
    ArrayList<ServicesName> ServicesArrayList = new ArrayList<ServicesName> (  );

/////////////////////////////////////////////////default subscriber/////////////////////////////////////////////////////////////////////////////////////
    Subscriber SubscriberPoint = new Subscriber ("1","Роман", "Лемтюгов", "TS123123",
            "Roman", "123", "01.01.2018", "31.12.2018", 30, 20 );


//////////////////////// Menu Items/////////////////////////////////////////////

    public void helpOnShowSubscribers(int what) throws java.io.IOException {
        do {
            System.out.println ( "|--------- Данные об абоненте -----------|" );
            SubscriberPoint.showSubscriberInfo ();
            System.out.print ( "\n\n" );
            System.out.println ( " 1. Показать номер договора" );
            System.out.println ( " 2. Показать Фамилию" );
            System.out.println ( " 3. Вернуться в главное меню" );
            System.out.println ( " 4. Нажмите 4 или q чтобы вернуться назад\n" );
            what = (char) System.in.read ();

            switch (what) {
                case '1':

                    System.out.println ( SubscriberPoint.SubscriberAgreementNumber );
                    System.out.print ( "\n\n" );
                    break;

                case '2':
                    System.out.println ( SubscriberPoint.SubscriberLastName );
                    break;

                case '3':
                    break;
            }
            char ignore;
            do {
                ignore = (char) System.in.read ();//new Scanner (System.in).nextInt(); //;

            } while (ignore != '\n');
        } while (isValid ( what ));
    }           //1

    public void helpOnBalance(int what) throws java.io.IOException {
        do {

            System.out.println ( "--------- Данные о балансе -----------:" );
            SubscriberPoint.showSubscriberInfo ();
            //SubscriberPoint.showCurrentSubscriberTime ();

            System.out.print ( "\n\n" );
            System.out.println ( " 1. Показать баланс" );
            System.out.println ( " 2. Показать сколько осталось дней" );
            System.out.println ( " 3. Вернуться в главное меню" );
            System.out.println ( "Bыбepитe пункт (q - назад): " );
            what = (char) System.in.read ();

            switch (what) {
                case '1':

                    SubscriberPoint.showSubscriberBalance ();
                    System.out.print ( "\n\n" );
                    break;

                case '2':
                    SubscriberPoint.showSubscriberTime ();
                   // SubscriberPoint.showCurrentSubscriberTime ();
                    break;

                case '3':
                    break;
            }
            char ignore;
            do {
                ignore = (char) System.in.read ();//new Scanner (System.in).nextInt(); //;

            } while (ignore != '\n');
        } while (isValid ( what ));
    }                   //2

    public void helpOnEditSubscriberData(int what) throws java.io.IOException {


        do {

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
            obj = new Subscriber (SubscriberID, SubscriberName, SubscriberLastName, SubscriberAgreementNumber, SubscriberLogin, SubscriberPassword, startDate, endDate, feeRate, paid );

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

            System.out.println ( "|--------- Данные об абоненте из сохраненного файла -------------| \n" );
            String format = "|%1$-20s|%2$-20s|%3$-20s|%4$-20s|%5$-20s|%6$-20s|%7$-20s|%8$-20s|%9$-20s|\n";
            String format2 = "|%1$-188s|\n";
            System.out.format ( format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" );
            System.out.format ( format, "SubscriberID", "SubscriberName", "SubscriberLastName", "AgreementNumber", "SubscriberLogin", "SubscriberPassword", "StartDate", "EndDate", "FeeRate", "Paid total" );
            System.out.format ( format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" );
            System.out.format ( format,d.getSubscriberID (), d.getSubscriberName (), d.getSubscriberLastName (), d.getSubscriberAgreementNumber (), d.getSubscriberLogin (), d.getSubscriberPassword (), d.getStartDate (), d.getEndDate (), d.getFeeRate (), d.getPaid () );
            System.out.format ( format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" );


          //  System.out.println ( "|--------- В настоящий момент у абонента следующие данные -----------|" ); //перепроверка передачи файла из ArrayList в Subscriber объект
          //  SubscriberPoint.showSubscriberInfo ();


            System.out.print ( "\n\n" );
            System.out.println ( "1. Ввести новые данные" );
            System.out.println ( "2. Изменить логин и пароль" );
            System.out.println ( "3. Изменить платежи" );
            System.out.println ( "   Введите значение или нажмите q чтобы выйти в главное меню:\n" );



            what = (char) System.in.read ();
            Scanner input = new Scanner ( System.in );
            switch (what) {
                case '1':     //с клавиатуры вводи значение, его передаем соответствующему сеттеру. Параметру объекта присваиваем значение, которое было введенно с клавы
                    //int c = new Scanner(System.in).nextInt();

                    System.out.println ( "Введите новый ID абонента: " );
                    obj.setSubscriberID ( input.next() );
                    SubscriberPoint.SubscriberID = obj.SubscriberID;

                    System.out.println ( "Введите новое Имя абонента: " );
                    obj.setSubscriberName ( input.next () );
                    SubscriberPoint.SubscriberName = obj.SubscriberName;

                    System.out.println ( "Введите новую Фамиилию абонента: " );
                    obj.setSubscriberLastName ( input.next () );
                    SubscriberPoint.SubscriberLastName = obj.SubscriberLastName;

                    System.out.println ( "Введите новый номер договора: " );
                    obj.setSubscriberAgreementNumber ( input.next () );
                    SubscriberPoint.SubscriberAgreementNumber = obj.SubscriberAgreementNumber;

                    System.out.println ( "Введите новый логин" );
                    obj.setSubscriberLogin ( input.next () );
                    SubscriberPoint.SubscriberLogin = obj.SubscriberLogin;

                    System.out.println ( "Введите новый пароль" );
                    obj.setSubscriberPassword ( input.next () );
                    SubscriberPoint.SubscriberPassword = obj.SubscriberPassword;

                    System.out.println ( "Введите новую дату начала в формате дд.мм.гггг" );
                    obj.setStartDate ( input.next () );
                    SubscriberPoint.startDate = obj.startDate;

                    System.out.println ( "Введите новую дату окончания дд.мм.гггг" );
                    obj.setEndDate ( input.next () );
                    SubscriberPoint.endDate = obj.endDate;

                    System.out.println ( "Введите новую ставку в месяц в формате 0,00" );
                    obj.setFeeRate ( input.nextDouble () );
                    SubscriberPoint.feeRate = obj.feeRate;


                    System.out.println ( "Введите оплаченную сумму за весь период 0,00" );
                    obj.setPaid ( input.nextDouble () );
                    SubscriberPoint.paid = obj.paid;



                    SubList.add ( obj );

                    for (Subscriber dr : SubList) {//с помощью цикла for each c ArrayList читаем поля поочередно сохраняя с переменную dr
                        dr.showSubscriberInfo ();
                        System.out.println ( "Размер ArrayList SubList:   " + SubList.size () );
                    }
                    try {
                        FileOutputStream fileOut1 = new FileOutputStream ( "subscriber.ser" );
                        ObjectOutputStream out1 = new ObjectOutputStream ( fileOut1 );
                        out1.writeObject ( obj );
                        out1.close ();
                        fileOut1.close ();
                        System.out.println ( "Сериализованная информация о Subscriber сохранена в D:\\GIT\\subscriber.ser" );
                    } catch (IOException i) {
                        i.printStackTrace ();
                    }
                    if (input.nextLine ().equals ( "q" )) {
                        System.out.println ( "Введите q чтобы попасть назад: " );
                        break;

                    }
                    System.out.println ( "\tНажмите ВВОД, чтобы продолжить" );
                    break;

                case '2':

                    obj.setSubscriberID ( SubscriberPoint.SubscriberID );
                    obj.setSubscriberName ( SubscriberPoint.SubscriberName );
                    obj.setSubscriberLastName ( SubscriberPoint.SubscriberLastName );
                    obj.setSubscriberAgreementNumber ( SubscriberPoint.SubscriberAgreementNumber );

                    System.out.println ( "Введите новый логин" );
                    obj.setSubscriberLogin ( input.next () );
                    SubscriberPoint.SubscriberLogin = obj.SubscriberLogin;

                    System.out.println ( "Введите новый пароль" );
                    obj.setSubscriberPassword ( input.next () );
                    SubscriberPoint.SubscriberPassword = obj.SubscriberPassword;

                    obj.setStartDate ( SubscriberPoint.startDate );
                    obj.setEndDate ( SubscriberPoint.endDate );
                    obj.setFeeRate ( SubscriberPoint.feeRate );
                    obj.setPaid ( SubscriberPoint.paid );


                    SubList.add ( obj );

                    for (Subscriber dr : SubList) {//с помощью цикла for each c ArrayList читаем поля поочередно сохраняя в переменную dr
                        dr.showSubscriberInfo ();
                        System.out.println ( "Размер ArrayList SubList:   " + SubList.size () );
                    }
                    try {
                        FileOutputStream fileOut1 = new FileOutputStream ( "subscriber.ser" );
                        ObjectOutputStream out1 = new ObjectOutputStream ( fileOut1 );
                        out1.writeObject ( obj );
                        out1.close ();
                        fileOut1.close ();
                        System.out.println ( "Сериализованная информация о  сохранена в D:\\GIT\\subscriber.ser" );
                    } catch (IOException i) {
                        i.printStackTrace ();
                    }
                    if (input.nextLine ().equals ( "q" )) {
                        System.out.println ( "Введите q чтобы попасть назад: " );
                        break;

                    }
                    System.out.println ( "\tНажмите ВВОД, чтобы продолжить" );

                    break;

                case '3':

                    obj.setSubscriberID( SubscriberPoint.SubscriberID );
                    obj.setSubscriberName ( SubscriberPoint.SubscriberName );
                    obj.setSubscriberLastName ( SubscriberPoint.SubscriberLastName );
                    obj.setSubscriberAgreementNumber ( SubscriberPoint.SubscriberAgreementNumber );
                    obj.setSubscriberLogin ( SubscriberPoint.SubscriberLogin );

                    obj.setSubscriberPassword ( SubscriberPoint.SubscriberPassword );

                    obj.setStartDate ( SubscriberPoint.startDate );
                    obj.setEndDate ( SubscriberPoint.endDate );

                    System.out.println ( "Введите новую стоимость в месяц 0,00: " );
                    obj.setFeeRate ( input.nextDouble () );
                    SubscriberPoint.feeRate= obj.feeRate;

                    System.out.println ( "Введите стоимость оплаченную за всё время 0,00: " );
                    obj.setPaid ( input.nextDouble () );
                    SubscriberPoint.paid= obj.paid;


                    SubList.add ( obj );

                    for (Subscriber dr : SubList) {//с помощью цикла for each c ArrayList читаем поля поочередно сохраняя с переменную dr
                        dr.showSubscriberInfo ();
                        System.out.println ( "Размер ArrayList DrList:   " + SubList.size () );
                    }
                    try {
                        FileOutputStream fileOut1 = new FileOutputStream ( "subscriber.ser" );
                        ObjectOutputStream out1 = new ObjectOutputStream ( fileOut1 );
                        out1.writeObject ( obj );
                        out1.close ();
                        fileOut1.close ();
                        System.out.println ( "Сериализованная информация о  сохранена в D:\\GIT\\subscriber.ser" );
                    } catch (IOException i) {
                        i.printStackTrace ();
                    }
                    if (input.nextLine ().equals ( "q" )) {
                        System.out.println ( "Введите q чтобы попасть назад: " );
                        break;

                    }
                    System.out.println ( "\tНажмите ВВОД, чтобы продолжить" );

                    break;


                default:
                    break;
            }
            char ignore;
            do {
                ignore = (char) System.in.read ();//new Scanner (System.in).nextInt(); //;

            } while (ignore != '\n');
        } while (isValid ( what ));


    }        //3

    public void helpOnShowSavedFileInfo(int what) throws java.io.IOException {

        readFile ();
    }         //4

    public void helpOnServices(int what) throws java.io.IOException {
        do {
            System.out.println ( "|--------- Данные об абоненте -----------|" );
            SubscriberPoint.showSubscriberInfo ();
            System.out.print ( "\n\n" );
            System.out.println ( " 1. Показать список услуг" );
            System.out.println ( " 2. Изменить стоимость услуг" );
            System.out.println ( " 3. Нажмите 3 или q чтобы вернуться назад\n" );
            what = (char) System.in.read ();

            switch (what) {
                case '1':
                    showServicesList ();

                    System.out.print ( "\n\n" );
                    break;

                case '2':
                    changeServicesFee();
                    break;

                default: break;

            }
            char ignore;
            do {
                ignore = (char) System.in.read ();//new Scanner (System.in).nextInt(); //;

            } while (ignore != '\n');
        } while (isValid ( what ));

    }                  //5

    ///////////// MainMenu//////////////////////////////////////////////////////
    public void helpOn(int what) throws java.io.IOException {
        switch (what) {

            case '1':
                helpOnShowSubscribers ( what );
                break;

            case '2':
                helpOnBalance ( what );
                break;
            case '3':
                helpOnEditSubscriberData ( what );
                break;

            case '4':
                helpOnShowSavedFileInfo ( what );
                break;

            case '5':
                helpOnServices ( what );
                break;

            default:
                break;
        }
        System.out.println ();
    }                          //Structure
    public void showMenu() {
        System.out.println ( "Главное меню:" );
        System.out.println ( " 1. Показать данные об абоненте" );
        System.out.println ( " 2. Баланс" );
        System.out.println ( " 3. Изменить данные Абонента" );
        System.out.println ( " 4. Показать абонента, сохраненного в файл\n" );
        System.out.println ( " 5. Услуги\n" );

        System.out.print ( "Bыбepитe пункт(q to quit): " );


    }                                                           //MainMenuNames

////////////////////////////////////////////////////////////////////////////////
    ////////////////// Internal Menu Methods ///////////////////////////////////

    public void readFile() {

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

        System.out.println ( "|--------- Данные из сохраненного файла -------------| \n" );
        String format = "|%1$-20s|%2$-20s|%3$-20s|%4$-20s|%5$-20s|%6$-20s|%7$-20s|%8$-20s|%9$-20s|\n";
        String format2 = "|%1$-188s|\n";
        System.out.format ( format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" );
        System.out.format ( format, "SubscriberID", "SubscriberName", "SubscriberLastName", "AgreementNumber", "SubscriberLogin", "SubscriberPassword", "StartDate", "EndDate", "FeeRate", "Paid total" );
        System.out.format ( format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" );
        System.out.format ( format, d.getSubscriberID (), d.getSubscriberName (), d.getSubscriberLastName (), d.getSubscriberAgreementNumber (), d.getSubscriberLogin (), d.getSubscriberPassword (), d.getStartDate (), d.getEndDate (), d.getFeeRate (), d.getPaid () );
        System.out.format ( format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------" );

    }                                                           // Read *.ser file

    public void LoginPassword() {
        Subscriber d2 = null;
        try {
            FileInputStream fileIn = new FileInputStream ( "subscriber.ser" );
            ObjectInputStream in = new ObjectInputStream ( fileIn );
            d2 = (Subscriber) in.readObject ();
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

        SubscriberPoint.SubscriberName = d2.getSubscriberName ();
        //  System.out.println (SubscriberPoint.SubscriberName);
        SubscriberPoint.SubscriberLastName = d2.getSubscriberLastName ();
        SubscriberPoint.SubscriberAgreementNumber = d2.getSubscriberAgreementNumber ();

        SubscriberPoint.SubscriberLogin = d2.getSubscriberLogin ();           //считанные логин и пароль передаем в SubscriberPoint, закомментить чтобы сбросить пароль по умолчанию
        //   System.out.println (SubscriberPoint.SubscriberLogin);
        SubscriberPoint.SubscriberPassword = d2.getSubscriberPassword ();
        //   System.out.println (SubscriberPoint.SubscriberPassword);

        SubscriberPoint.startDate = d2.getStartDate ();
        SubscriberPoint.endDate = d2.getEndDate ();
        SubscriberPoint.feeRate = d2.getFeeRate ();
        SubscriberPoint.paid = d2.getPaid ();

    }                                                      // Login\Password using *.ser file

    public void showServicesList (){
      //  ServicesName AllServicesName[] = ServicesName.values ();

        /*for (ServicesName a: ServicesName.values ())
            System.out.println (a + ServicesName.getPrice()); */
        for (ServicesName services: ServicesName.values ()) {
            System.out.println (services +"   " + services.getPrice());
        }


    }

    public void changeServicesFee (){
        //  ServicesName AllServicesName[] = ServicesName.values ();

        /*for (ServicesName a: ServicesName.values ())
            System.out.println (a + ServicesName.getPrice()); */
        Scanner input = new Scanner ( System.in );


        for (ServicesName services: ServicesName.values ()) {
            System.out.println ("Введите стоимость " + services +" ");
            BigDecimal valueInput = input.nextBigDecimal ();
            services.setPrice (valueInput);


            System.out.println ("Новая стоимость " + services+ "составляет " +services.getPrice () +"\n");

        }
        System.out.println ("\n");






        System.out.println ( "\tНажмите ВВОД, чтобы продолжить" );
    }


    public static boolean isValid(int ch) {

        if (ch < '1' | ch > '7' & ch != 'q') {

            System.out.println ( "вы ввели недопустимый символ \n" );
            return true;
        } else {
            return false;
        }
    }


    public boolean LoginInput() throws IOException {
        String Username;
        String Password;
        int what;

        Password = SubscriberPoint.SubscriberPassword;
        Username = SubscriberPoint.SubscriberLogin;

                    Scanner input1 = new Scanner ( System.in );
                    System.out.println ( "Введите Логин пользователя" );
                    String username = input1.next ();

                    Scanner input2 = new Scanner ( System.in );
                    System.out.println ( "Введите пароль " );
                    String password = input2.next ();
                    if (username.equals ( Username ) && password.equals ( Password )) {
                        System.out.println ( "Доступ предоставлен! Добро пожаловать!\n\n" );
                        return true;

                    } else {

                        return false;

                    }

    }                                   //Login/Password checking

}
