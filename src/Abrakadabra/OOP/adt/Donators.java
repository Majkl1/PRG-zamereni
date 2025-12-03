package Abrakadabra.OOP.adt;

import fileworks.DataImport;

import java.util.*;

public class Donators {
    public static void main(String[] args) {
        DataImport di = new DataImport("data/blood.txt");

        List<Donator> valuableResources = new ArrayList<>();

        while (di.hasNext()){
            String[] parts = di.readLine().trim().split(",");
            Donator d = new Donator(parts[0], parts[1], parts[2].equals("Female"), Integer.parseInt(parts[3]));
            valuableResources.add(d);
        }

        System.out.println(valuableResources.size());
        System.out.println(filterByBloodtype(valuableResources, "O+").size());


        System.out.println("Unikátní lidi u krevních typů");
        System.out.println("A+: " + uniqueDonators(valuableResources, "A+").size());
        System.out.println("A-: " + uniqueDonators(valuableResources, "A-").size());
        System.out.println("B+: " + uniqueDonators(valuableResources, "B+").size());
        System.out.println("B-: " + uniqueDonators(valuableResources, "B-").size());
        System.out.println("AB+: " + uniqueDonators(valuableResources, "AB+").size());
        System.out.println("AB-: " + uniqueDonators(valuableResources, "AB-").size());
        System.out.println("O+: " + uniqueDonators(valuableResources, "O+").size());
        System.out.println("O-: " + uniqueDonators(valuableResources, "O-").size());

        di.finishImport();
    }


    public static List<Donator> filterByBloodtype(List<Donator> people, String bt){
        List<Donator> filtered = new ArrayList<>();
        for (Donator karel: people){
            if (karel.bloodType.equals(bt)){
                filtered.add(karel);
            }
        }
        return filtered;
    }

    public static Set<Donator> uniqueDonators(List<Donator> people, String bt){
        Set<Donator> filtered = new HashSet<>(filterByBloodtype(people, bt));
        return filtered;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Donator donator = (Donator) o;
        return isWomen == donator.isWomen && age == donator.age && Objects.equals(name, donator.name) && Objects.equals(bloodType, donator.bloodType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bloodType, isWomen, age);
    }
}
