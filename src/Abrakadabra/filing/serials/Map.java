package Abrakadabra.filing.serials;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public static List<MapCoordinate> readcsv (String path){
        List<MapCoordinate> toReturn = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));

            for (String line : lines){
                String parts[] = line.trim().split(",");
                toReturn.add(new MapCoordinate(Double.parseDouble(parts[0]), Double.parseDouble(parts[1])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return toReturn;
    }


    public static void main(String[] args) throws ClassNotFoundException {
        String c1 = "data/countries/country1.csv";
        String c2 = "data/countries/country2.csv";
        String c3 = "data/countries/country3.csv";

        List<MapCoordinate> c1l = readcsv(c1);
        List<MapCoordinate> c2l = readcsv(c2);
        List<MapCoordinate> c3l = readcsv(c3);

        List<MapCoordinate> combined = new ArrayList<>();
        combined.addAll(c1l);
        combined.addAll(c2l);
        combined.addAll(c3l);


        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/countries/working.ser"));
            oos.writeObject(combined);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String path = "data/countries/working.ser";
        ObjectInputStream ois =null;

        List<MapCoordinate> coords = null;

        {
            try {
                ois = new ObjectInputStream(new FileInputStream(path));
                coords = (List<MapCoordinate>) ois.readObject();
                ois.close();

                for (MapCoordinate c : coords){
                    System.out.println(c);
                }
            } catch (IOException e) {
                System.out.println("Something is wrong with file.");
            }
        }
    }
}

class MapCoordinate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    double longitude;
    double latitude;

    public MapCoordinate(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return String.format("%f,%f", longitude, latitude);
    }
}