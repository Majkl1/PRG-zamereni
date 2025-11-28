package moje.adt;

import fileworks.DataImport;

public class Donators {
    public static void main(String[] args) {
        DataImport di = new DataImport("data/blood.txt");

        while (di.hasNext()){
            String[] parts = di.readLine().trim().split(",");
            boolean n = true;
            if (parts[2].equals("Male")){
                n = false;
            }
            Donator d = new Donator(parts[0], parts[1], n, Integer.parseInt(parts[3]));
        }
    }
}

class Donator{
    String name , bloodType;
    boolean isWomen;
    int age;

    public Donator(String name, String bloodType, boolean isWomen, int age) {
        this.name = name;
        this.bloodType = bloodType;
        this.isWomen = isWomen;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Donator{" +
                "name='" + name + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", isWomen=" + isWomen +
                ", age=" + age +
                '}';
    }
}
