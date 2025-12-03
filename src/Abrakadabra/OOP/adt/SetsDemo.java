package Abrakadabra.OOP.adt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetsDemo {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        words.add("Hello");
        System.out.println(words);
        words.add("Hello"); //uz tam je, vrati false
        System.out.println(words);

        words.add("World");


        words.remove("Hello");
        System.out.println(words);

        words.clear();

        words.toArray();


        //vycistit
        List<String> notunigueWords = new ArrayList<>();
        notunigueWords.add("Words");
        notunigueWords.add("Words");
        notunigueWords.add("Words");
        notunigueWords.add("Word");
        notunigueWords.add("Words");
        notunigueWords.add("Words");
        notunigueWords.add("Words");
        System.out.println(notunigueWords);

        Set<String> uniqueWords = new HashSet<>(notunigueWords);
        System.out.println(uniqueWords);
        notunigueWords.clear();
        notunigueWords.addAll(uniqueWords);
        System.out.println(notunigueWords);


        Set<Integer> uniqueRandoms = new HashSet<>();
        while(uniqueRandoms.size() < 101){
            uniqueRandoms.add((int)(Math.random()*151));
        }
        System.out.println(uniqueRandoms);
        List<Integer> randoms = new ArrayList<>(uniqueRandoms);
        System.out.println(randoms);
    }
}
