package moje.OOP.interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Comparing {
    public static void main(String[] args) {
        ArrayList<Track> songs = new ArrayList<>();
        songs.add(new Track("Dream on", 1973, 8.9, 205));
        songs.add(new Track("Jozin z bazin", 1978, 7.0, 167));
        songs.add(new Track("Tadada", 2005, 6.54,123));

        Collections.shuffle(songs);
        System.out.println(songs);
        Collections.sort(songs);
        System.out.println(songs);



        songs.sort(Track.BY_DURATION);
        System.out.println(songs);

        //   :)
        songs.sort(Track.BY_DURATION.reversed());
        System.out.println(songs);


        songs.sort(Track.BY_YEAR);
        System.out.println(songs);

        songs.sort(Track.BY_NAME);
        System.out.println(songs);
    }
}

class Track implements Comparable<Track> {
    String name;
    int year;
    double rating;
    int length;

    final static Comparator<Track> BY_YEAR = new Comparator<Track>() {
        @Override
        public int compare(Track o1, Track o2) {
            return Integer.compare(o1.year,o2.year);
        }
    };

    final static Comparator<Track> BY_DURATION = new Comparator<Track>() {
        @Override
        public int compare(Track o1, Track o2) {
            return Integer.compare(o1.length, o2.length);
        }
    };

    final static Comparator<Track> BY_RATING = new Comparator<Track>() {
        @Override
        public int compare(Track o1, Track o2) {
            return Double.compare(o1.rating, o2.rating);
        }
    };

    final static Comparator<Track> BY_NAME = new Comparator<Track>() {
        @Override
        public int compare(Track o1, Track o2) {
            return o1.name.compareTo(o2.name);
        }
    };

    public Track(String name, int year, double rating, int length) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.length = length;
    }

    @Override
    public String toString() {
        return name + " (" + year + ")" + ", rated: " + rating + " length: " + length + "\n";
    }

    @Override
    public int compareTo(Track o) {

        //return  (int)(o.rating - this.rating);
        return this.name.compareTo(o.name);
    }
}
