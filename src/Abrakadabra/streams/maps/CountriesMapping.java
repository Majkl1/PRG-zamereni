package Abrakadabra.streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CountriesMapping {
    public static void main(String[] args) {
        String dataPath = "data/countries.txt";

        try {
            List<Country> countries = Files.lines(Path.of(dataPath))
                    .map(line -> line.trim().split(";"))
                    .map(parts -> new Country(parts[0],
                            parts[1],
                            Long.parseLong(parts[2]),
                            Double.parseDouble(parts[3])
                    ))
                    .toList();

            //countries.forEach(System.out::println);


            Map<String, Long> countByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent, Collectors.counting()));

            for (String continent : countByContinent.keySet()){
                System.out.println(continent + " --- " + countByContinent.get(continent));
            }


            Map<String, List<Country>> countriesByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent));

            for (String continent : countriesByContinent.keySet()){
                System.out.println(continent + ": ");
                for (Country country : countriesByContinent.get(continent)){
                    System.out.println(" --- " + country.getName());
                }
            }


            Map<String, Long> populationByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent, Collectors.summingLong(Country::getPopulation)));

            for (String continent : populationByContinent.keySet()){
                System.out.println(continent + " --- " + populationByContinent.get(continent));
            }

            Map<String, Double> avgAgeByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent, Collectors.averagingDouble(Country::getLifeExpectancy)));

            for (String continent : avgAgeByContinent.keySet()){
                System.out.println(continent + " --- " + avgAgeByContinent.get(continent));
            }

            //nejlidnatější zemi v kontinentu
            Map<String, Optional<Country>> mostPopulatedCountryByContinent = countries.stream()
                    .collect(Collectors.groupingBy(Country::getContinent, Collectors.maxBy(Comparator.comparing(Country::getPopulation))));

            Optional<Country> max = mostPopulatedCountryByContinent.get("Africa");
            Country m = max.get();
            System.out.println(m);



            Map<String, Country> mostPopulatedCountryByContinent2 = countries.stream()
                    .collect(Collectors.groupingBy(
                            Country::getContinent,
                            Collectors.collectingAndThen(
                                    Collectors.maxBy(Comparator.comparing(Country::getPopulation)),
                                    Optional::get
                            )
                    ));



            List<Country> filtered = countries.stream()
                    .filter(country -> country.getLifeExpectancy() > 42.0)
                    .toList();


            for (String continent : countriesByContinent.keySet()){
                System.out.println(continent);
                for (Country c : countriesByContinent.get(continent)){
                    if (c.getLifeExpectancy() > 77.0){
                        System.out.println(" --- " + c.getName() + " " + c.getLifeExpectancy());
                    }
                }
            }


        } catch (IOException e) {
            System.err.println("Something went wrong!");
        }
    }
}

class Country {
    String name;
    String continent;
    long population;
    double lifeExpectancy;


    public Country(String name, String continent, long population, double lifeExpectancy) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                ", lifeExpectancy=" + lifeExpectancy +
                '}' + "\n";
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

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
}