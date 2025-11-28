package moje.adt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Listing {
    public static void main(String[] args) {
        //List z java.util
        List<Integer> my;

        //přiáme jeden
        List<Integer> intergers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            intergers.add(i);
        }

        //více najednou
        List<Integer> toAdd = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            toAdd.add(i);
        }
        intergers.addAll(toAdd);

        //vypsání
        System.out.println(intergers);

        //horší
        System.out.println(intergers.get(0));

        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        System.out.println(words.get(0));

        //odstranění
        //přes index
        intergers.remove(0);
        words.remove(0);
        //hledá equals hodnoty
        words.remove("hello");

        //velikost
        System.out.println(intergers.size());
        System.out.println(intergers.get(intergers.size()-1));

        //upravy
        intergers.set(4,6); //nachylne na blbost (ArrayindexOutOfBounds)
        intergers.add(2, 42); //nesmaže jen posune

        //gimmicks
        Collections.shuffle(intergers);
        System.out.println(intergers);

        Collections.min(intergers);
        Collections.max(intergers);
        // intergrers.sort();


        //prvek je v listu prave jednou
        words.add("hello");
        words.add("world");
        words.add("helo");
        words.add("world");

        System.out.println(isOnlyOnce(words, "hello"));
        System.out.println(isOnlyOnce(words, "world"));
    }


    static boolean isOnlyOnce(List<String> words, String word){
        if (!words.contains(word)){
            System.out.println(word + " není v kolekci vubec");
            return false;
        }
        return words.indexOf(word) == words.lastIndexOf(word);
    }
}
