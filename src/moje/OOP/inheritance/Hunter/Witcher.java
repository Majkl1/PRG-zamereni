package moje.OOP.inheritance.Hunter;

public class Witcher extends Soldier {


    public Witcher(String name) {
        super(name);
        gold += 50;
    }

    public void slayMonster(){
        System.out.println(name + " is killing monster.");
        gold += 250;
    }

    @Override
    public void hunt() {
        System.out.println(name + " is hunting monster...and he´s very good at it");
        gold += 50;
    }

}
