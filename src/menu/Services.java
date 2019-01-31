package menu;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Scanner;


public class Services implements Serializable, SubscriberAndServices {


}
enum ServicesName {TarifPlan1(BigDecimal.valueOf ( 5 )), TarifPlan2(BigDecimal.valueOf ( 10 ));

  private BigDecimal price;
  //double tariffRate;


    ServicesName(BigDecimal price) {this.price=price;}
    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price=price;}


    public static void main(String[] args) {

     /*   for (ServicesName services: ServicesName.values ()) {
            System.out.println (services +"   " + services.getPrice());
        } */


        Scanner input = new Scanner ( System.in );
        //input.next();
        BigDecimal valueInput = input.nextBigDecimal ();

        TarifPlan1.setPrice (valueInput);

        System.out.println (ServicesName.TarifPlan1.getPrice ());

    }

}
