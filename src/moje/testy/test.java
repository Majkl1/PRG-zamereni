package moje.testy;


import fileworks.DataImport;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        DataImport di = new DataImport("movieList.txt");

        ArrayList<Movie> movies = new ArrayList<>();
        while (di.hasNext()) {
            Movie movie = parseLine(di.readLine());
            movies.add(movie);
        }
        rateFilter(movies);
        thriller(movies);
        old(movies);


        di.finishImport();
    }

    static Movie parseLine(String Line) {
        String[] dataPieces = Line.split(";");

        Movie movie = new Movie(
                dataPieces[0],
                Integer.parseInt(dataPieces[1]),
                dataPieces[2],
                Double.parseDouble(dataPieces[3])
        );

        return movie;
    }


    static void rateFilter(ArrayList<Movie> c) {
        System.out.println("Zde jsou filmy s hodnocením 9.9 a víš:");
        for (Movie m : c) {
            if (m.getRate() > 9.9) {
                System.out.println(m);
            }
        }
    }

    static void thriller(ArrayList<Movie> c) {
        int num = 0;

        for (Movie m : c) {
            if (m.getGenre().equals("Thriller")) {
                num += 1;
            }
        }
        System.out.println("Filmů s žánrem Thriller je: " + num);
    }

    static void old(ArrayList<Movie> c) {
        Movie old = c.getFirst();
        for (Movie m : c) {
            if (m.release < old.release) {
                old = m;
            }
        }
        System.out.println(old);
    }
}

class Movie {
    String name, genre;
    int release;
    double rate;

    public Movie(String name, int release, String genre, double rate) {
        this.name = name;
        this.release = release;
        this.genre = genre;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", genre='" + genre + '\'' +
                        ", release=" + release +
                        ", rate=" + rate;
    }
}
