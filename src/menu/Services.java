package menu;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


public class Services implements Serializable, SubscriberAndServices {

    String ServicesNames;

    double ServicesRate;

    public String getServicesNames() {
        return ServicesNames;
    }

    public void setServicesNames(String servicesNames) {
        ServicesNames = servicesNames;
    }

    public double getServicesRate() {
        return ServicesRate;
    }

    public void setServicesRate(double servicesRate) {
        ServicesRate = servicesRate;
    }


}

enum ServicesName {
    TarifPlan1 ( BigDecimal.valueOf ( 5 ) ), TarifPlan2 ( BigDecimal.valueOf ( 10 ) );

    public BigDecimal price;
    //double tariffRate;


    private ServicesName(BigDecimal price) {
        this.price = price;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public static void main(String[] args) {

     for (ServicesName services: ServicesName.values ()) {
            System.out.println (services +"   " + services.getPrice());
        }

       Scanner input = new Scanner ( System.in );
        //input.next();
        BigDecimal valueInput = input.nextBigDecimal ();

        TarifPlan1.setPrice (valueInput);

        System.out.println (ServicesName.TarifPlan1.getPrice ());


   /*     ArrayList<ServicesName> ServicesArrayList = new ArrayList<ServicesName> ();   // могу менять значение из BigDecimal.valueOf НО ЗАЧЕМ????? :)
        ArrayList<Services> ServList = new ArrayList<Services> (  );
        Services objServices = new Services ();

        Scanner input = new Scanner ( System.in );

        for (ServicesName services : ServicesName.values ()) {
            System.out.println ( "Введите стоимость " + services + " " );
            BigDecimal valueInput = input.nextBigDecimal ();
            services.setPrice ( valueInput );

            System.out.println ( "Новая стоимость " + services + "составляет " + services.getPrice () + "\n" );
        }

        */

}






}
