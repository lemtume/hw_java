package menu;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Subscriber implements Serializable  {
    private static final long serialVersionUID = -5250997439565143459L;

    String SubscriberID;
    String SubscriberName;
    String SubscriberLastName;
    String SubscriberAgreementNumber;
    String SubscriberLogin;
    String SubscriberPassword;
    String startDate;
    String endDate;
    double feeRate;
    double paid;

    public Subscriber(ArrayList<Subscriber> mySubscriber) throws IOException, ClassNotFoundException {
        loadSubscriber(mySubscriber);
    }

    private  void loadSubscriber (ArrayList<Subscriber> myContacts) throws IOException, ClassNotFoundException {

    }

    public String getSubscriberID() {
        return SubscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        SubscriberID = subscriberID;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(double feeRate) {
        this.feeRate = feeRate;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public Subscriber(String SubscriberID, String SubscriberName, String SubscriberLastName, String SubscriberAgreementNumber, String SubscriberLogin,
                      String SubscriberPassword, String startDate, String endDate, double feeRate, double paid) {

        this.SubscriberID=SubscriberID;
        this.SubscriberName = SubscriberName;
        this.SubscriberLastName = SubscriberLastName;
        this.SubscriberAgreementNumber = SubscriberAgreementNumber;
        this.SubscriberLogin = SubscriberLogin;
        this.SubscriberPassword = SubscriberPassword;
        this.startDate=startDate;
        this.endDate=endDate;
        this.feeRate= feeRate;
        this.paid= paid;
    }


    public String getSubscriberName() {
        return SubscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        SubscriberName = subscriberName;
    }

    public String getSubscriberLastName() {
        return SubscriberLastName;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        SubscriberLastName = subscriberLastName;
    }

    public String getSubscriberAgreementNumber() {
        return SubscriberAgreementNumber;
    }

    public void setSubscriberAgreementNumber(String subscriberAgreementNumber) {
        SubscriberAgreementNumber = subscriberAgreementNumber;
    }

    public String getSubscriberLogin() {
        return SubscriberLogin;
    }

    public void setSubscriberLogin(String subscriberLogin) {
        SubscriberLogin = subscriberLogin;
    }

    public String getSubscriberPassword() {
        return SubscriberPassword;
    }

    public void setSubscriberPassword(String subscriberPassword) {
        SubscriberPassword = subscriberPassword;
    }

    public void showSubscriberInfo() {

        String format = "|%1$-20s|%2$-20s|%3$-20s|%4$-20s|%5$-20s|%6$-20s|%7$-20s|%8$-20s|%9$-20s|\n";
        String format2 = "|%1$-188s|\n";
        System.out.format(format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format(format, "SubscriberID","SubscriberName", "SubscriberLastName", "AgreementNumber", "SubscriberLogin", "SubscriberPassword", "StartDate", "EndDate", "FeeRate", "Paid total");
        System.out.format(format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format ( format, SubscriberID, SubscriberName, SubscriberLastName, SubscriberAgreementNumber, SubscriberLogin, SubscriberPassword, startDate, endDate, feeRate, paid);
        System.out.format(format2, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void showSubscriberBalance() {

      /*  System.out.println ( SubscriberName + ' ' + SubscriberLastName + ' ' + SubscriberAgreementNumber );

        SimpleDateFormat format = new SimpleDateFormat ("dd.MM.yyyy");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(endDate);
            date2 = format.parse(startDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        long difference = date1.getTime() - date2.getTime();
        long days = difference / (24 * 60 * 60 * 1000); */

        double days=showSubscriberTime();
        double balance = paid- (Math.round ( days/30)*feeRate);
        String checkMinus=null;


        System.out.println("Тариф в месяц " + feeRate);
        System.out.println("Количество месяцев использования " + Math.round ( (int)days/30 ) + " //Количество дней: " + (int)days);

        if (balance<0) {
            checkMinus="Задолженность";
            System.out.println ( "Общая задолженность составляет:" + Math.abs ( balance ) );
            }
        else
            {
                checkMinus="Баланс";
                System.out.println ( "Баланс составляет:" + balance );
            }


        System.out.println ( "|--------- Информация по балансу -------------| \n" );
        String format = "|%1$-20s|%2$-20s|%3$-30s|%4$-20s|%5$-20s|%6$-20s|\n";
        String format2 = "|%1$-132s|\n";
        System.out.format ( format2, "---------------------------------------------------------------------------------------------------------------------------------------" );
        System.out.format ( format, checkMinus, "Тариф в месяц", "Оплачено за все время", "Договор", "StartDate", "EndDate" );
        System.out.format ( format2, "---------------------------------------------------------------------------------------------------------------------------------------" );
        System.out.format ( format, balance, feeRate, paid, SubscriberAgreementNumber, startDate, endDate );
        System.out.format ( format2, "---------------------------------------------------------------------------------------------------------------------------------------" );
    }


    public double showSubscriberTime() {

        System.out.println ( SubscriberName + ' ' + SubscriberLastName + ' ' + SubscriberAgreementNumber );

        SimpleDateFormat format = new SimpleDateFormat ("dd.MM.yyyy");
        Date date1 = null;
        Date date2 = null;
        Date date3 = new Date();

        //System.out.println (date3);

        try {
            date1 = format.parse(endDate);
            date2 = format.parse(startDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        long difference = date3.getTime() - date2.getTime();
        long days = difference / (24 * 60 * 60 * 1000);
        System.out.println("Дней оказания услуг:" + days);

        return days;
    }

}
