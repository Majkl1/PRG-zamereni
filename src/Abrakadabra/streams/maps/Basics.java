package Abrakadabra.streams.maps;

import java.util.HashMap;

public class Basics {
    public static void main(String[] args) {
        HashMap<String, String> capitals = new HashMap<>();
        capitals.put("Czech Republic", "Prague");
        capitals.put("Germany", "Berlin");
        capitals.put("Austria", "Vienna");


        System.out.println(capitals.get("Germany"));

        System.out.println(capitals);

        //projet vše
        for (String country : capitals.keySet()){
            System.out.println(capitals.get(country));
        }

        capitals.remove("Germany");
        System.out.println(capitals);

        capitals.put("Austria", "Sydney");
        System.out.println(capitals);

        capitals.replace("Austria", "Vienna");
        System.out.println(capitals);

        capitals.putIfAbsent("Austria", "neco");
        System.out.println(capitals);
    }
}
