package moje.old;

import fileworks.DataExport;

public class filework {
    public static void main(String[] args) {
        /*
        DataImport dataImport = new DataImport("input.txt");

        dataImport.printFile();

        while (dataImport.hasNext()){
            String line = dataImport.readLine();
            System.out.println(line);
        }
        dataImport.finishImport();*/

        DataExport dataExport = new DataExport("output.txt");

        dataExport.writeLine("At the beginning there was hamburger.");


        dataExport.finishExport();
    }
}
