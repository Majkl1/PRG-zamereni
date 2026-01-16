package Abrakadabra.filing;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipleFiles {
    public static void main(String[] args) {
        String path = "data/znamky.txt";
        String outPath = "data/znamky/";
        String fileFormat = ".txt";

        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            File adresar = new File("data/znamky");
            if (!adresar.exists()){
                adresar.mkdir();
            }

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
                int znamka = (int) Math.round(prumer);
                students.add(new Student(name,prumer));

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
        Collections.sort(students);
        PrintWriter pw = null;



        for (Student s : students){
            String fileName = Long.toString(Math.round(s.prumer));
            String lastName = "";
            if (!fileName.equals(lastName)){
                pw.close();
                try {
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(outPath + fileName + fileFormat)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                pw.println(s.toString());
            }
            lastName = fileName;

        }

        pw.close();
    }
}

class Student implements Comparable<Student>{
    String name;
    double prumer;

    public Student(String name, double prumer) {
        this.name = name;
        this.prumer = prumer;
    }

    @Override
    public String toString() {
        return name + ";" + prumer;
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(this.prumer, o.prumer);
    }
}
