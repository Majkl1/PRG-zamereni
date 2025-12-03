package Abrakadabra.OOP.interfaces;


import fileworks.DataImport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Movie implements Comparable<Movie>{
    String name;
    int year;
    double rating;
    int length;


    public Movie(String name, int year, double rating, int length) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.length = length;
    }

    final static Comparator<Movie> BY_YEAR = new Comparator<Movie>() {
        @Override
        public int compare(Movie o1, Movie o2) {
            return Integer.compare(o1.year, o2.year);
        }
    };

    final static Comparator<Movie> BY_RATING = new Comparator<Movie>() {
        @Override
        public int compare(Movie o1, Movie o2) {
            return Double.compare(o1.rating,o2.rating);
        }
    };

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", length=" + length +
                '}' + "\n";
    }

    @Override
    public int compareTo(Movie o) {
        return this.name.compareTo(o.name);
    }
}



public class exam {
    public static void main(String[] args) {
        DataImport di = new DataImport("Movies.txt");
        ArrayList<Movie> movies = new ArrayList<>();

        while (di.hasNext()){
            String[] parts = di.readLine().split(";");

            Movie m = new Movie(parts[0],Integer.parseInt(parts[1]), Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
            movies.add(m);
        }
        Collections.sort(movies);
        System.out.println(movies);

        movies.sort(Movie.BY_YEAR.reversed());
        System.out.println(movies);

        movies.sort(Movie.BY_RATING.reversed());

        for (int i = 0; i < 10; i++) {
            System.out.println(movies.get(i));
        }


        di.finishImport();
    }
}
