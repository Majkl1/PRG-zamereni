package moje.OOP.inheritance.procviceni.jedlici;

public class MegaEater extends Eater{
    public MegaEater(String name) {
        super(name);
        weight = 100;
    }

    public void eatPizza(){
        System.out.println(name + " žere pizzu.");
        weight += 15;
    }

    @Override
    public void eatSausage() {
        super.eatSausage();
        weight += 5;
    }
}
