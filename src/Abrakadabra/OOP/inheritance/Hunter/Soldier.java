package Abrakadabra.OOP.inheritance.Hunter;

public class Soldier extends Hunter{


    public Soldier(String name) {
        super(name);
        gold += 40;
    }

    public void killBandits(){
        System.out.println(name + " is going against bandits.");
        gold += 100;
    }
}
