package Abrakadabra.exams;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class test {

    public static List<File> fileFilter(File[] files, String end){
        List<File> filtered = new ArrayList<>();
        for (File f : files){
            if (f.getName().contains(end) && f.length() < 2000000){
                filtered.add(f);
            }
        }
        return filtered;
    }

    static Comparator<File> BY_SIZE = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return Long.compare(o1.length(), o2.length()) ;
        }
    };


    public static void main(String[] args) {
        File dir = new File("data/poetry");
        File[] files = dir.listFiles();
        List<File> filteredFiles = fileFilter(files, ".txt");
        filteredFiles.sort(BY_SIZE);


        for (File f : filteredFiles){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null){
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Soubor nebyl nalezen");
            } catch (IOException e) {
                System.out.println("Něco se nepovedlo");
            }
        }
    }
}
