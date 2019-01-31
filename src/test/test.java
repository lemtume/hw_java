package test;

import java.math.BigDecimal;


    enum Apple {
        Jonathan( BigDecimal.valueOf(10.5)),
        GoldenDel(BigDecimal.valueOf(9.5)), RedDel(BigDecimal.valueOf(5)),
        Winesap(BigDecimal.valueOf(15)), Cortland(BigDecimal.valueOf(8.5));
        private BigDecimal price;// цена яблока каждого сорта
        // Конструктор
        Apple(BigDecimal рrice) {this.price = рrice;}
        BigDecimal getPrice() {return price;}
    }
    class EnumDemo3 {
        public static void main(String args[]) {
// вывести цену яблок сорта Winesap
            System.out.println("Яблoкo сорта Winesap стоит " +
                    Apple.Winesap.getPrice()+ " рублей.");
// вывести цены всех сортов яблок
            System.out.println("Цeны на все сорта яблок:");
            for (Apple apple : Apple.values()) {
                System.out.println("Килограмм яблок сорта \""+apple + "\"cтоит " + apple.getPrice() + " рублей.");
            }}}


