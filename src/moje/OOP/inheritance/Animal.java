package moje.OOP.inheritance;

public class Animal {
    int delkaNosu;
    int poxetNohou;
    int pocetHlav;

    String narodnost;
    double prumnernaDelkaPrstu;
    boolean nazivu;

    void eat(){}

    public static void main(String[] args) {
        Animal an = new Animal();
        Dog Tim = new Dog();
        Pig prase = new Pig();
    }

}

class Dog extends Animal{
    String plemeno;
    void bark(){}
}

class Pig extends Animal{
    boolean budeZNejSekana;
}
