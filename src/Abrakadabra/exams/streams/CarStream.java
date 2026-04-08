package Abrakadabra.exams.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * 1. Načtěte soubor data/cars.csv
 *    - přeskočte 1. řádek souboru
 *    - všechny záznamy uložte do List<Car> cars
 * 2. Implementujte všechny metody
 */
public class CarStream {
    public static void main(String[] args) {
        String path = "data/cars.csv";
        List<Car> cars = loadCars(path);


        // Vypište průměrnou maximální rychlost aut značky Porsche
        System.out.println(getAverageTopSpeed(cars, "Porsche"));

        // Vypište všechny auta levnější než 8000 USD
        System.out.println(filterByPrice(cars, 8000));

        // Vypište statistiky o autech na základě jejich "Performance"
        printStatistics(cars);

        // Vypište všechny auta značky Bugatti nad 420 HP
        System.out.println(getCarsAbovePowerByBrand(cars, "Bugatti", 420));

        // Vypište, kolik procent aut je značky Nissan
        System.out.println(getBrandPercentage(cars, "Nissan"));


    }

    /**
     * Vypíše základní statistiky o autech pomocí {@code summaryStatistics}
     * nad hodnotou {@code performance}.
     *
     * @param cars seznam aut
     */
    private static void printStatistics(List<Car> cars) {
        DoubleSummaryStatistics statistics = cars.stream()
                .mapToDouble(car -> car.getPerformence())
                .summaryStatistics();

        System.out.println(statistics);
    }

    /**
     * Vrátí nový vyfiltrovaný seznam aut.
     *
     * @param cars seznam aut
     * @param price maximální cena (včetně), kterou mohou mít auta v novém seznamu
     * @return nový vyfiltrovaný seznam aut
     */
    private static List<Car> filterByPrice(List<Car> cars, int price) {
        List<Car> toReturn = cars.stream()
                .filter(car -> car.getPrice() >= price)
                .toList();

        return toReturn;
    }

    /**
     * Vrátí průměrnou maximální rychlost aut od značky {@code brand}.
     *
     * @param cars vstupní seznam aut
     * @param brand název značky auta (například Škoda)
     * @return průměrná maximální rychlost aut dané značky
     */
    private static double getAverageTopSpeed(List<Car> cars, String brand) {
        Double d = cars.stream()
                .filter(car -> car.getCompany().equalsIgnoreCase(brand))
                .mapToDouble(car -> car.getTotalSpeed())
                .average()
                .orElse(0);

        return d;
    }

    /**
     * Načte všechny řádky souboru kromě prvního a vrátí je jako seznam aut.
     *
     * @param path cesta k souboru
     * @return seznam aut
     */
    public static List<Car> loadCars(String path){
        List<Car> cars = new ArrayList<>();
        try {
            List<String> lines =  Files.readAllLines(Path.of(path));
            for (String line : lines) {
                if (!line.contains("Company Name")) {
                    String[] parts = line.trim().split(",");
                    cars.add(new Car(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), Integer.parseInt(parts[5])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    /**
     * Vrátí seznam aut dané značky, jejichž výkon je větší než zadaná mez.
     *
     * @param cars vstupní seznam aut
     * @param brand název značky auta
     * @param threshold minimální výkon, který musí auto překročit
     * @return seznam aut dané značky s výkonem větším než {@code threshold}
     */
    public static List<Car> getCarsAbovePowerByBrand(List<Car> cars, String brand, int threshold) {

        List<Car> toReturn =  cars.stream()
                .filter(car -> car.getCompany().equalsIgnoreCase(brand))
                .filter(car -> car.getHorsepower() >= threshold)
                .toList();

        return toReturn;
    }

    /**
     * Vrátí procentuální zastoupení aut dané značky v seznamu.
     * Výsledek je vrácen v procentech, například {@code 24.5}, nikoli {@code 0.245}.
     *
     * @param cars vstupní seznam aut
     * @param brand název značky auta
     * @return procentuální zastoupení aut dané značky v seznamu
     */
    public static double getBrandPercentage(List<Car> cars, String brand) {
        double n = cars.stream()
                .filter(car -> car.getCompany().equalsIgnoreCase(brand))
                .count();

        double f = cars.size();

        double toReturn = (n/f)*100;

        return toReturn;
    }
}

class Car{
    String company;
    String name;
    int horsepower;
    int totalSpeed;
    double performence;
    int price;

    public Car(String company, String name, int horsepower, int totalSpeed, double performence, int price) {
        this.company = company;
        this.name = name;
        this.horsepower = horsepower;
        this.totalSpeed = totalSpeed;
        this.performence = performence;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", horsepower=" + horsepower +
                ", totalSpeed=" + totalSpeed +
                ", performence=" + performence +
                ", price=" + price +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getTotalSpeed() {
        return totalSpeed;
    }

    public void setTotalSpeed(int totalSpeed) {
        this.totalSpeed = totalSpeed;
    }

    public double getPerformence() {
        return performence;
    }

    public void setPerformence(double performence) {
        this.performence = performence;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}