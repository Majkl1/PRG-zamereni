package Abrakadabra.filing;

import java.io.*;
import java.util.Random;

public class Appendix {
    public static void main(String[] args) {
        //try-with resources
        Random r = new Random();
        String path = "added.txt";


        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            for (int i = 0; i < 20; i++) {
                pw.println(r.nextInt(100, 1000000));
            }
            pw.close();

            pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            pw.println("test");
            pw.close();

        } catch (IOException e) {
            System.out.println("Chyba při práci se souborem: " + e.getMessage());
        }
    }
}
