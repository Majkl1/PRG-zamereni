package Abrakadabra.filing.serials;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CollectionsSerializer {
    public static void main(String[] args) {
        String serPath = "data/collections.ser";
        List<Coordinates> coords = new ArrayList<>();
        int size = 10_000;

        Random rng = new Random();

        for (int i = 0; i < size; i++) {
            coords.add(new Coordinates(rng.nextInt(), rng.nextInt(), rng.nextInt()));
        }

        try {
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serPath));
            oos.writeObject(coords);
            oos.close();

            List<Coordinates> serialisedCoords = null;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serPath));
            serialisedCoords = (List<Coordinates>) ois.readObject();

            System.out.println(coords.size() == serialisedCoords.size());
            System.out.println(serialisedCoords.get(serialisedCoords.size() - 1));
            System.out.println(coords.get(coords.size() - 1));
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
