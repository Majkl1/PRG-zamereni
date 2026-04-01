package Abrakadabra.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

public class CountriesStreaming {
    public static long num(List<Country> countries, String continent){
            long n = countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .count();

        return n;
    }


    public static void main(String[] args) {
        String path = "data/countries.txt";
        List<Country> countries = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(path));


            for (String line : lines){
                String[] parts = line.trim().split(";");
                countries.add(new Country(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long suma = countries.stream()
                .mapToLong(country -> country.getPopulation())
                .sum();

        System.out.println("Celková populace: " + suma);

        double avg = countries.stream()
                .mapToDouble(country -> country.getPopulation())
                .average()
                .orElse(0);

        System.out.println("Avg populace: " + avg);

        IntSummaryStatistics statistics = countries.stream()
                .mapToInt(country -> country.getPopulation())
                .summaryStatistics();

        System.out.println(statistics);

        int totalPopulationNA = countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase("North America"))
                .mapToInt(country -> country.getPopulation())
                .sum();
        System.out.println(totalPopulationNA);

        double avgAge = countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase("Asia"))
                .mapToDouble(country -> country.getAvgLife())
                .average()
                .orElse(0);
        System.out.println(avgAge);

        System.out.println(num(countries, "North America"));
        System.out.println(num(countries, "South America"));
        System.out.println(num(countries, "Europe"));
        System.out.println(num(countries, "Asia"));
        System.out.println(num(countries, "Africa"));
        System.out.println(num(countries, "Oceania"));

    }
}

class Country{
    String name;
    String continent;
    int population;
    double avgLife;

    public Country(String name, String continent, int population, double avgLife) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.avgLife = avgLife;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                ", avgLife=" + avgLife +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getAvgLife() {
        return avgLife;
    }

    public void setAvgLife(double avgLife) {
        this.avgLife = avgLife;
    }
}