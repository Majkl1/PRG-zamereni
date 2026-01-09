package Abrakadabra.filing;

import fileworks.DataImport;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ReadingDemo {
    public static void main(String[] args) {
        String path = "data/books.txt";

        //znamý způsob (způsob 0):
        /*DataImport di = new DataImport(path);
        while (di.hasNext()){
            System.out.print(di.readChar());
        }
        di.finishImport();
        */

        //způsob 0.5:
        Scanner sc = new Scanner("12.5,222,55,Arcibald,555,712.87,666");
        sc.useLocale(Locale.US);
        sc.useDelimiter(",");

        Double d = sc.nextDouble();
//        while (sc.hasNext()){
//            System.out.print(sc.next());
//        }
//        sc.close();

        try {
            File f = new File(path);

            //způsob 1:
            FileReader fr = new FileReader(path);

            if (!f.isFile()){
                return;
            }
            FileReader fr2 = new FileReader(f);
            int input;
            while ((input = fr2.read()) != -1){
                System.out.print((char) input);
            }
            System.out.println("----");


            //způsob 2:

            //BufferedReader br = new BufferedReader(new FileReader(new File("data/books.txt)));
            BufferedReader br = new BufferedReader(fr2);
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
            System.out.println("----");

            fr2.close();
            br.close();

            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String l: lines){
                System.out.println(l);
            }

        } catch (FileNotFoundException e) {
            System.out.println("FIle does not exist!" + e.getMessage());
        } catch (IOException io) {
            System.out.println("Something wrong!" + io.getMessage());
        }
    }
}
