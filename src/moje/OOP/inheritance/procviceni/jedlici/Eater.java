package moje.OOP.inheritance.procviceni.jedlici;

public class Eater extends Beginner{


    public Eater(String name) {
        super(name);
        weight = 85;
    }


    public void eatHamburger() {
        System.out.println(name + " papá hambáček.");
        weight += 10;
    }
}
