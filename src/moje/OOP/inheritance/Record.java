package moje.OOP.inheritance;

public class Record {
    String name;
    int time;
    void play(){
        System.out.println("Hraju " + name);
    }
}

class Mp3 extends Record{
    boolean isCompressed;
    void compression(){
        System.out.println("Komprese probíhá");
    }
}
class Vinyl extends Record{
    int speed;

}
class WAV extends Record{
    boolean broken = true;
}
