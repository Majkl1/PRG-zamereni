package Abrakadabra.OOP.inheritance.procviceni.jedlici;

public class Beginner {
    String name;
    int weight = 70;

    public void eatSausage(){
        System.out.println(name + " papá páreček.");
        weight += 5;
    }

    public Beginner(String name) {
        this.name = name;
    }
}
