package moje.OOP.interfaces;

import fileworks.DataImport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Book1 implements Comparable<Book1>{
    String name;
    String author;
    int year;
    int pages;

    public Book1(String name, String author, int year, int pages) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    final static Comparator<Book1> BY_PAGES = new Comparator<Book1>() {
        @Override
        public int compare(Book1 o1, Book1 o2) {
            return Integer.compare(o1.pages,o2.pages);
        }
    };

    final static Comparator<Book1> BY_YEAR = new Comparator<Book1>() {
        @Override
        public int compare(Book1 o1, Book1 o2) {
            return Integer.compare(o1.year,o2.year);
        }
    };


    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}' + "\n";
    }

    @Override
    public int compareTo(Book1 o) {
        return this.name.compareTo(o.name);
    }

    public static void main(String[] args) {
        DataImport di = new DataImport("books.txt");
        ArrayList<Book1> books = new ArrayList<>();


        while (di.hasNext()){
            String[] parts = di.readLine().split(";");


            Book1 b = new Book1(parts[0], parts[1],Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            books.add(b);
        }
        System.out.println(books);


        Collections.sort(books);
        System.out.println(books);

        books.sort(Book1.BY_PAGES);
        System.out.println(books);

        books.sort(Book1.BY_YEAR);
        System.out.println(books);

        di.finishImport();
    }
}
