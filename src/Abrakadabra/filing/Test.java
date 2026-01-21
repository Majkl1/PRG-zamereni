package Abrakadabra.filing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String path = "data/textInput.txt";
        try {
            int zn = 0;
            int lines = 0;
            int vety = 0;
            FileReader fr = new FileReader(path);

            int input;
            while ((input = fr.read()) != -1){
                String h = String.valueOf((char) input);
                if (h.equals("?") || h.equals(".") || h.equals("!")){
                    vety++;
                }
                zn++;
            }
            System.out.println("Znaky: " + zn);


            BufferedReader bw = new BufferedReader(new FileReader(path));
            String line;
            while((line = bw.readLine()) != null){
                lines++;
            }
            System.out.println("řádky: " + lines);
            System.out.println("vety: " + vety);


            fr.close();
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
