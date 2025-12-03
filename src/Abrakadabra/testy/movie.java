package Abrakadabra.testy;


import fileworks.DataImport;

import java.util.Comparator;

import java.util.*;

public class movie {
    public static void main(String[] args) {
        DataImport di = new DataImport("data/movieList.txt");
        Set<Film> filmy = new HashSet<>();

        while (di.hasNext()){
            String[] parts = di.readLine().trim().split(";");
            Film m = new Film(parts[0],Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]));
            filmy.add(m);
        }
        List<Film> films = new ArrayList<>(filmy);
        films.sort(Film.BY_YEAR);
        System.out.println(films);

        System.out.println("Horror: " + unique(films,"Horror").size());
        System.out.println("Action: " + unique(films,"Action").size());
        System.out.println("Thriller: " + unique(films,"Thriller").size());
        System.out.println("Fantasy: " + unique(films,"Fantasy").size());
        System.out.println("Romance: " + unique(films,"Romance").size());
        System.out.println("Mystery: " + unique(films,"Mystery").size());
        System.out.println("Comedy: " + unique(films,"Comedy").size());
        System.out.println("Adventure: " + unique(films,"Adventure").size());
        System.out.println("Sci-Fi: " + unique(films,"Sci-Fi").size());
        System.out.println("Drama: " + unique(films,"Drama").size());
        System.out.println("Science Fiction: " + unique(films,"Science Fiction").size());
        System.out.println("Musical " + unique(films,"Musical").size());
        System.out.println("Crime " + unique(films,"Crime").size());

        printGenre(films, "Horror");
        System.out.println();

    }

    public static void printGenre(List<Film> films, String genre){
        System.out.println(genre + ": ");
        for (Film film: films){
            if (film.genre.equals(genre)){
                System.out.println("|- " + film.name);
            }
        }
    }


    public static List<Film> unique(List<Film> films, String genre){
        List<Film> done = new ArrayList<>();

        for (Film film: films){
            if (film.genre.equals(genre)){
                done.add(film);
            }
        }
        return done;
    }
}

class Film implements Comparable<Film>{
    String name;
    int year;
    String genre;
    double rating;

    public Film(String name, int year, String genre, double rating) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return year == film.year && Double.compare(rating, film.rating) == 0 && Objects.equals(name, film.name) && Objects.equals(genre, film.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, genre, rating);
    }

    final static Comparator<Film> BY_YEAR = new Comparator<Film>() {
        @Override
        public int compare(Film o1, Film o2) {
            return Integer.compare(o1.year,o2.year);
        }
    };

    @Override
    public int compareTo(Film o) {
        return this.name.compareTo(o.name);
    }
}