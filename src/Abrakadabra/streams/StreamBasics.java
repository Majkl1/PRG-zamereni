package Abrakadabra.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Stream;

public class StreamBasics {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random rnd = new Random(22);

        for (int i = 0; i < 100; i++) {
            numbers.add(rnd.nextInt(150));
        }
        System.out.println(numbers);

        System.out.println(numbers.size());

        int size = (int) numbers.stream()
                .count();
        System.out.println(size);

        //unikat
        HashSet<Integer> uniques = new HashSet<>(numbers);
        System.out.println(uniques.size());

        //stream:
        int uniquesAmount = (int) numbers.stream()
                .distinct()
                .count();

        System.out.println(uniquesAmount);

        //unikatni vetsi nez 50
        int uniquesUpper = (int) numbers.stream()
                .distinct()
                .filter(num -> num > 50)
                .count();
        System.out.println(uniquesUpper);


        //vypis vsechna suda cisla
        numbers.stream()
                .filter(num -> num % 2 == 0)
                .forEach(num -> System.out.print(num + " "));

        System.out.println();

        //vypis pole seřazene
        numbers.stream()
                .sorted((num1, num2) -> Integer.compare(num1,num2))
                .forEach(num -> System.out.print(num + " "));

        numbers.stream()
                .sorted(Integer::compareTo)
                .forEach(System.out::print);

        System.out.println();
         //vypis prumer pole
        double avg = numbers.stream()
                .mapToDouble(Integer::doubleValue) //v tride Integer najdi metodu doubleValue
                .average()
                .orElse(0);

        System.out.println(avg);

        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        String[] names = {"Lakatosh", "Pankrac", "Toshiba", "Konstantin", "Poli", "Veverka"};
        Stream.of(names)
                .sorted((name1, name2) -> name2.length() - name1.length())
                .forEach(System.out::println);

    }
}
