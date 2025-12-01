package Abrakadabra.OOP.adt;

import java.util.Stack;

public class kouzelnaDilnicka {
    public static void main(String[] args) {
        Stack<Wizard> dimension = new Stack<>();
        dimension.push(new Wizard("Pepa", 200, 15));
        dimension.push(new Wizard("Franta", 300, 10));
        dimension.push(new Wizard("Arcibald", 100, 30));
    }
}

class Wizard{
    String name;
    int mana, delkaHulky;

    public Wizard(String name, int mana, int delkaHulky) {
        this.name = name;
        this.mana = mana;
        this.delkaHulky = delkaHulky;
    }
}