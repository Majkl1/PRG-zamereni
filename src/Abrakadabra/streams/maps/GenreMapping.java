package Abrakadabra.streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenreMapping {
    public static void main(String[] args) throws IOException {
        List<Movie> movies = Files.lines(Path.of("data/movieList.txt"))
                .map(line -> line.split(";"))
                .map(tokens -> new Movie(tokens[0], Integer.parseInt(tokens[1]), tokens[2], Double.parseDouble(tokens[3])))
                .toList();

        System.out.println(movies);

        HashMap<String, List<Movie>> genreMap = new HashMap<>();

        for (Movie movie : movies){
            //žánr už v mapě je
            if (genreMap.containsKey(movie.getGenre())){
                genreMap.get(movie.getGenre()).add(movie);
            }
            else {
                genreMap.put(movie.getGenre(), new ArrayList<>());
                genreMap.get(movie.getGenre()).add(movie);
            }
        }
        //vypis output v podobě:
        //zanr
        //-film1
        //-film2

        for (String genre : genreMap.keySet()){
            System.out.println("Genre: " + genre);
            for (Movie m : genreMap.get(genre)){
                System.out.println("--" + m.getName());
            }
        }

        //ziskání ratingu dle žánru:
        for (String genre : genreMap.keySet()){
            System.out.println("Genre: " + genre);
            double avgRating = genreMap.get(genre).stream()
                    .mapToDouble(movie -> movie.getRating())
                    .average()
                    .orElse(0);
            System.out.println("Průměr: " + avgRating);
        }

        //tohle udělá stejnou hasmapu
        Map<String, List<Movie>> alsoGenreMap = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre));

        //Mapa zanr:prumer
        Map<String, Double> avgMap = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getRating)));
        System.out.println(avgMap);

        //Mapa Zanr:PocetFilmu
        Map<String, Long> countMap = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.counting()));
        System.out.println(countMap);

    }
}

class Movie{
    String name;
    int year;
    String genre;
    double rating;

    public Movie(String name, int year, String genre, double rating) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}' + "\n";
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}