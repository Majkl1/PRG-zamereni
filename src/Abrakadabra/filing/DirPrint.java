package Abrakadabra.filing;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DirPrint {
    public static  void printDirSorted(String dirPath, String fileType){
        ArrayList<File> files = new ArrayList<>();
        File directory = new File(dirPath);
        if (!directory.exists() || !directory.isDirectory()){
            System.out.println("You did not provide directory!");
            return;
        }
        for (File f : directory.listFiles()){
            String[] tokens = f.getName().split("\\.");
            if (tokens[tokens.length-1].equals(fileType)){
                files.add(f);
            }
        }
        System.out.println(files);
        System.out.println(files.size());

        Comparator<File> fileSizeComparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Long.compare(o1.length(), o2.length());
            }
        };
        files.sort(fileSizeComparator);

        System.out.println("============================");
        for (File f : files){
            System.out.println(f.getName() + ": " + (f.length() / 1024) + "kb");
        }

    }

    public static void main(String[] args) {
        printDirSorted("data", "txt");
    }
}
