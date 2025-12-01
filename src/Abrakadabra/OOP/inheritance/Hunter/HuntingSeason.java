package Abrakadabra.OOP.inheritance.Hunter;

import fileworks.DataImport;

import java.util.Random;

public class HuntingSeason {
    static void doRandomJob(Hunter hunter) {
        Random r = new Random();
        switch (r.nextInt(1, 4)) {
            case 1:
                hunter.hunt();
                break;
            case 2:
                if (hunter instanceof Soldier) {
                    ((Soldier) hunter).killBandits();
                } else {
                    hunter.gold -= 100;
                }
                break;
            case 3:
                if (hunter instanceof Witcher) {
                    ((Witcher) hunter).slayMonster();
                } else {
                    hunter.gold -= 100;
                }
                break;
        }

        //Také Funkční řešení:
//        if (hunter instanceof Hunter){
//            hunter.hunt();
//        } else if (hunter instanceof Soldier){
//            ((Soldier) hunter).killBandits();
//        } else if (hunter instanceof Witcher) {
//            ((Witcher) hunter).slayMonster();
//        }

    }

    static void doJob(Hunter hunter, String role) {
        switch (role) {
            case "H":
                hunter.hunt();
                break;
            case "K":
                if (hunter instanceof Soldier) {
                    ((Soldier) hunter).killBandits();
                } else {
                    hunter.gold -= 100;
                }
                break;
            case "M":
                if (hunter instanceof Witcher) {
                    ((Witcher) hunter).slayMonster();
                } else {
                    hunter.gold -= 100;
                }
                break;
        }
    }

    public static void main(String[] args) {
//        Hunter hunter = new Hunter("Theodore Roosvelt");
//        hunter.hunt();
//        System.out.println(hunter.name+ " má " + hunter.gold);
//
//        Soldier soldier = new Soldier("Solid Snake");
//        soldier.hunt();
//        soldier.killBandits();
//        System.out.println(soldier.name + " má " + soldier.gold);
//
//        Witcher witcher = new Witcher("Vesemir");
//        witcher.hunt();
//        witcher.killBandits();
//        witcher.slayMonster();
//        System.out.println(witcher.name+ " má " + witcher.gold);

//        Hunter randomak = new Witcher("Eskel");
//        randomak.hunt();
//
//        ((Witcher) randomak).slayMonster();

        DataImport di = new DataImport("hunters.txt");

        Hunter hunter1;

        while (di.hasNext()) {
            String line = di.readLine();
            String[] dataPieces = line.split(",");

            if (dataPieces[1].equals("Hunter")) {
                hunter1 = new Hunter(dataPieces[0]);

            } else if (dataPieces[1].equals("Soldier")) {
                hunter1 = new Soldier(dataPieces[0]);

            } else {
                hunter1 = new Witcher(dataPieces[0]);
            }

            for (int i = 2; i < dataPieces.length; i++) {
                doJob(hunter1, dataPieces[i]);
                //doRandomJob(hunter1);
            }

            System.out.println(hunter1.name + " si vydělal " + hunter1.gold);
        }

        di.finishImport();
    }

}
