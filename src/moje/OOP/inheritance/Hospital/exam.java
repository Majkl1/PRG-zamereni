package moje.OOP.inheritance.Hospital;

import fileworks.DataExport;
import fileworks.DataImport;

public class exam {

    public static void operace(Doctor doc, String ch) {
        int penalty = 50000;


        switch (ch){
            case "D":
                doc.diagnose();
                break;

            case "S":
                if (doc instanceof Surgeon){
                    ((Surgeon)doc).surgery();
                } else {
                    doc.salary -= penalty;
                }
                break;

            case "C":
                if (doc instanceof CardiacSurgeon){
                    ((CardiacSurgeon)doc).cardiacsurgery();
                } else {
                    doc.salary -= penalty;
                }
                break;
        }
    }

    public static void main(String[] args) {
        DataImport di = new DataImport("hospitalData.txt");
        DataExport de = new DataExport("DoctorsTest2.txt");

        Doctor BadDoc = new Doctor("Subjekt1");
        Doctor GoodDoc = new Doctor("Subjekt2");
        BadDoc.salary = Integer.MAX_VALUE;
        GoodDoc.salary = Integer.MIN_VALUE;

 //       ArrayList<Doctor> doctors = new ArrayList<>();

// Nefunguje - arraylist nenacpe 1000 doktorů do sebe
// nacpe ale mám to zaciklené

//        while (di.hasNext()){
//            if (dataPices[1].equals("Doctor")){
//                doctors.add(new Doctor(dataPices[0]));
//            } else if (dataPices[1].equals("Surgeon")) {
//                doctors.add(new Surgeon(dataPices[0]));
//            } else {
//                doctors.add(new CardiacSurgeon(dataPices[0]));
//            }
//        }
//        for (Doctor doc: doctors){
//            for (int i = 2; i < dataPices.length; i++) {
//                operace(doc,dataPices[i]);
//            }
//        }
//
//        for (Doctor doc: doctors){
//            if (doc.salary > GoodDoc.salary){
//                de.writeLine(doc.name + ";" + doc.salary);
//            }
//        }

        while (di.hasNext()){
            Doctor doc;
            String[] dataPices = di.readLine().split(";");

            if (dataPices[1].equals("Doctor")){
                doc = new Doctor(dataPices[0]);
            } else if (dataPices[1].equals("Surgeon")) {
                doc = new Surgeon(dataPices[0]);
            } else {
                doc = new CardiacSurgeon(dataPices[0]);
            }

            for (int i = 2; i < dataPices.length; i++) {
                operace(doc,dataPices[i]);
            }

            de.writeLine(doc.name + ";" + doc.salary);


            if (doc.salary < BadDoc.salary){
                BadDoc = doc;
            }

            if (doc.salary > GoodDoc.salary){
                GoodDoc = doc;
            }

        }

        System.out.println("Nejvíce výdělečný doktor: " + GoodDoc.name + ";" + GoodDoc.salary);
        System.out.println("Nejméně výdělečný doktor: " + BadDoc.name + ";" + BadDoc.salary);


        di.finishImport();
        de.finishExport();
    }
}
