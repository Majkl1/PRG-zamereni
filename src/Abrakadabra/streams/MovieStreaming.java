package Abrakadabra.streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieStreaming {

    static ArrayList<Movie> parseMovies(String filePath){
        File f = new File(filePath);
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            ArrayList<Movie> movies = new ArrayList<>();
            String[] tokens;
            for (String line : lines){
                tokens = line.split(";");
                movies.add(new Movie(
                        tokens[0],
                        Integer.parseInt(tokens[1]),
                        Double.parseDouble(tokens[2]),
                        Integer.parseInt(tokens[3])
                ));
            }
            return movies;
        } catch (IOException e) {
            System.out.println("something went wrong" + e.getMessage());
            return null;
        }


    }
    public static void main(String[] args) {
        ArrayList<Movie> movies = parseMovies("data/Movies.txt");
        movies.stream()
                .filter(m -> m.getYear() >= 2000)
                .forEach(System.out::println);
        System.out.println("====================");

        movies.stream()
                .filter(m -> m.getYear() >= 2000 && m.getRating() > 7.0)
                .forEach(System.out::println);
        System.out.println("==================");

        //ulozit pro dalsi praci
        List<Movie> oldies = movies.stream()
                .filter(movie -> movie.getYear() < 2000)
                .toList();

        //prumerny rating filmu  po roce 2010
        double avgRating = movies.stream()
                .filter(m -> m.getYear() > 2010)
                .mapToDouble(Movie::getRating)// nebo .mapToDouble(m -> m.rating) nebo .mapToDouble(m -> m.getRating())
                .average()
                .orElse(0);


        //celkova doba trvani filmu z roku 1995
        long total = movies.stream()
                .filter(m -> m.getYear() == 1995)
                .mapToInt(Movie::getDuration)
                .sum();

    }
}

class Movie{
    String name;
    int year;
    double rating;
    int duration;

    public Movie(String name, int year, double rating, int duration) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", duration=" + duration +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
