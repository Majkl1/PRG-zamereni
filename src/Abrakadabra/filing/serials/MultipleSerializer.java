package Abrakadabra.filing.serials;

import java.io.*;
import java.util.Arrays;

public class MultipleSerializer {
    public static void main(String[] args) {
        String serPath = "data/multiple.ser";

        Coordinates c1 = new Coordinates(1,2,3);
        Coordinates c2 = new Coordinates(4,5,6);
        Coordinates c3 = new Coordinates(7,8,9);


        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serPath));

            oos.writeObject(c1);
            oos.writeObject(c2);
            oos.writeObject(c3);

            oos.close();

            Coordinates d1 = null;
            Coordinates d2 = null;
            Coordinates d3 = null;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serPath));
            d1 = (Coordinates) ois.readObject();
            d2 = (Coordinates) ois.readObject();
            d3 = (Coordinates) ois.readObject();

            System.out.println(d1);
            System.out.println(d2);
            System.out.println(d3);
            ois.close();

        } catch (IOException e) {
            System.err.println("Something went wrong with file.");
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (ClassNotFoundException e) {
            System.err.println("Class is not compatible");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}

