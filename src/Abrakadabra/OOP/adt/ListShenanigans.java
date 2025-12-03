package Abrakadabra.OOP.adt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListShenanigans {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> alsoNumbers = new LinkedList<>();

//     Arraylist je pomalý na úpravu dat
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            numbers.add(i);
        }
        long end = System.currentTimeMillis();

        System.out.println((end-start) / 1_000d);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alsoNumbers.add(i);
        }
        end = System.currentTimeMillis();

        System.out.println((end-start) / 1_000d);


        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            numbers.remove(i);
        }
        end = System.currentTimeMillis();

        System.out.println((end-start) / 1_000d);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            alsoNumbers.remove(i);
        }
        end = System.currentTimeMillis();

        System.out.println((end-start) / 1_000d);


    }
}
