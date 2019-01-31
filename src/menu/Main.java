package menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class Main implements Serializable {


    public static void main(String[] args) throws java.io.IOException {
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


    }


}
