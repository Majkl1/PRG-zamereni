package moje.Parse;

import fileworks.DataImport;

import java.util.ArrayList;

public class ParseFile {
    public static void main(String[] args) {
        DataImport di = new DataImport("countries.txt");
        //di.printFile();
        /*System.out.println("první zeme:");
        String countryData = di.readLine();
        Country firstCountry = parseLine(countryData);

        System.out.println(firstCountry);*/

        ArrayList<Country> countries = new ArrayList<>();
        while (di.hasNext()){
            Country country = parseLine(di.readLine());
            countries.add(country);
        }
        filterByContinent(countries, "europe");
        maxAndMin(countries);
        prumVek(countries);

        di.finishImport();
    }

    static Country parseLine(String Line){
        String[] dataPieces = Line.split(";");

        Country Country =  new Country(
                dataPieces[0],
                dataPieces[1],
                Long.parseLong(dataPieces[2]),
                Double.parseDouble(dataPieces[3])
        );

        return Country;
    }

    static void filterByContinent(ArrayList<Country> c, String filter){
        int totalCount = 0;
        for (Country prvek: c) {
            if (prvek.getContinent().equalsIgnoreCase(filter)){
                System.out.println(prvek);
                totalCount++;
            }
        }
        System.out.printf("Celkový počet zemí v %s je %d\n", filter, totalCount);
    }

    static void maxAndMin(ArrayList<Country> c){
        Country min = c.getFirst();
        Country max = c.getFirst();
        for (Country prvek: c) {
            if (prvek.getSomething() < min.getSomething()){
                min = prvek;
            }
            if (prvek.getSomething() > max.getSomething()){
                max = prvek;
            }
        }
        System.out.println("max: " + max);
        System.out.println("min: " + min);
    }

    static void prumVek(ArrayList<Country> c){
        double prumer = 0;
        for (Country prvek: c){
            prumer += prvek.getAge();
        }
        prumer = prumer / c.size();
        System.out.println("průměr: " + prumer);
    }
}
class Country {
    public String name, continent;
    public long something;
    public double age;

    public Country( String name, String continent,long something, double age) {
        this.something = something;
        this.name = name;
        this.continent = continent;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setSomething(long something) {
        this.something = something;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public long getSomething() {
        return something;
    }

    public double getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Název: %s | Kontinent: %s | Populace: %,d | Dožití: %f", name, continent, something, age);
    }
}