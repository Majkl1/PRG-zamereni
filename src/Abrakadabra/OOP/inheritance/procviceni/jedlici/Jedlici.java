package Abrakadabra.OOP.inheritance.procviceni.jedlici;

import fileworks.DataImport;

public class Jedlici {

    public static void main(String[] args) {
        DataImport di = new DataImport("Eaters.txt");

        while (di.hasNext()){
            String[] dataPieces = di.readLine().split(",");

            Beginner jedlik;

            if (dataPieces[1].equals("Beginner")){
                 jedlik = new Beginner(dataPieces[0]);
            } else if (dataPieces[1].equals("Eater")) {
                jedlik = new Eater(dataPieces[0]);
            } else {
                jedlik = new MegaEater(dataPieces[0]);
            }

            for (int i = 2; i < dataPieces.length; i++) {
                soutez(jedlik,dataPieces[i]);
            }


        }



        di.finishImport();
    }

    public static void soutez(Beginner jed, String ch){
        switch (ch){
            case "S":
                jed.eatSausage();
                break;
            case "H":
                if (jed instanceof Eater){
                    ((Eater)jed).eatHamburger();
                }
                break;
            case "P":
                if (jed instanceof MegaEater){
                    ((MegaEater)jed).eatPizza();
                }
                break;
        }
    }
}
