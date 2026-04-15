package Abrakadabra.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class songs {
    public static void main(String[] args) {
        String path = "data/tracks.txt";
        List<Track> tracks;

        try {
            tracks = Files.lines(Path.of(path))
                    .map(line -> line.split(";"))
                    .map(parts -> new Track(parts[0],
                            Integer.parseInt(parts[1]),
                            Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]))
                    )
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tracks.stream()
                .filter(track -> track.getRating() >= 8.5)
                .sorted(Comparator.comparingDouble(Track::getRating).reversed())
                .limit(10)
                .forEach(System.out::println);

    }
}

class Track{
    String name;
    int year;
    double rating;
    int length;

    public Track(String name, int year, double rating, int length) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", length=" + length +
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
