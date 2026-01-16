package Abrakadabra.filing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MultipleFiles {
    public static void main(String[] args) {
        String path = "data/znamky.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("prumer.txt")));
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");
                String name = parts[0];
                int pocetZnamek = parts.length-1;
                double h = 0;
                for (int i = 1; i <= pocetZnamek; i++) {
                    h += Double.parseDouble(parts[i]);
                }
                double prumer = h/pocetZnamek;

                System.out.println(name + ", " + prumer);
                pw.println(name + ", " + prumer);
            }

            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }

    }
}

