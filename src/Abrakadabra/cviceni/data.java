package Abrakadabra.cviceni;

import fileworks.DataExport;
import fileworks.DataImport;

public class data {
    public static void main(String[] args) {
        DataImport di = new DataImport("tracks.txt");
        DataExport de = new DataExport("skladby.txt");
        DataExport top = new DataExport("Top_10.txt");

        int count = 0;

        while (di.hasNext()){
            String[] dataPieces = di.readLine().split(";");

            Track track = new Track(
                    dataPieces[0],
                    Integer.parseInt(dataPieces[1]),
                    Double.parseDouble(dataPieces[2]),
                    Integer.parseInt(dataPieces[3])
            );

            de.writeLine(String.valueOf(track));


            if (8.5 <= track.rate && count < 10){
                top.writeLine(String.valueOf(track));
                count++;
            }

        }
        di.finishImport();
        de.finishExport();
        top.finishExport();
    }
}

class Track {
    String name;
    int year;
    double rate;
    int time;

    public Track(String name, int year, double rate, int time) {
        this.name = name;
        this.year = year;
        this.rate = rate;
        this.time = time;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", rate=" + rate +
                ", time=" + time/60 + ":" + time%60 +
                '}';
    }
}
