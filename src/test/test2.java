package test;
import java.io.*;
import java.util.ArrayList;

public class test2 {
    public static void main(String args[]) throws IOException,    ClassNotFoundException {

     /*   ArrayList<Human> arrayList = new ArrayList<>();
        Human human = new Human("Artyom",12);
        Human human2 = new Human ("Art",17);
        FileOutputStream fos = new FileOutputStream("temp.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        arrayList.add(human);
        arrayList.add(human2);
        oos.writeObject(arrayList);
        oos.flush();
        oos.close(); */

        FileInputStream fis = new FileInputStream("temp.txt");
        ObjectInputStream oin = new ObjectInputStream(fis);
        ArrayList<Human> arrayList2 = new ArrayList<>();

        arrayList2  = (ArrayList<Human>) oin.readObject();

        for(int i = 0 ; i< arrayList2.size() ;i++) {
            System.out.println("Name:" + arrayList2.get(i).getName() + "Year:" +    arrayList2.get(i).getYear());
        }
    }
}

class Human implements Serializable {
    private String name;
    private int year;

    public Human(String s1, int y1)
    {
        this.name = s1;
        this.year = y1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
} 