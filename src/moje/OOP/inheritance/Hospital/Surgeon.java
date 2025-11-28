package moje.OOP.inheritance.Hospital;

public class Surgeon extends Doctor{

    final static int SURGEON_BONUS = 30000;

    public Surgeon(String name) {
        //omezení: když to od něčeho dědím ma konstruktor
        // já musím mít minimalne stejnej
        super(name);
        salary += SURGEON_BONUS;
    }

    void surgery(){
        System.out.println("Cutting open...");
        salary += 45000;
    }
}
