package Abrakadabra.exams.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShipmentMapping {

    public static String delivery(Shipment s){
//        deliveryDays <= 2       -> "Fast delivery"
//        deliveryDays <= 5       -> "Normal delivery"
//        deliveryDays > 5        -> "Slow delivery"
        if (s.getDeliveryDays() <= 2) return "Fast delivery";
        if (s.getDeliveryDays() <= 5) return "Normal Delivery";
        return "Slow delivery";
    }


    public static String byDate(Shipment s){
//        poslední dva roky (2026,2025)   -> "Current"
//        3-5 let				-> "Recent"
//        5+  let 	  		-> "Historic"
        LocalDate help = LocalDate.parse("01.01.2025", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate help2 = LocalDate.parse("01.01.2021", DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        if (s.getDate().isAfter(help)) return "Current";
        if (s.getDate().isAfter(help2)) return "Recent";
        return "Historic";
    }


    public static String priceCatgory(Shipment s){
        //    Rozdělení s vlastní kategorií 'pricePerKm'. Tato kategorie počítá, jak drahá byla cesta. Spočítá se jako podíl price a distanceKm:
//    pricePerKm < 20       -> "Cheap route"
//    pricePerKm <= 50      -> "Normal route"
//    pricePerKm > 50       -> "Expensive route"
//    Nad každou z těchto kategorií spočtěte průměrnou cenu. To uložte jako mapu.

        double price = s.getPrice() / s.getDistance();
        if (price < 20) return "Cheap route";
        if (price <= 50) return "Normal route";
        return "Expenisive route";

    }

    public static double fullPrice(Shipment s){
        //    Rozdělení s vlastní kategorií 'pricePerKm'. Tato kategorie počítá, jak drahá byla cesta. Spočítá se jako podíl price a distanceKm:
//    pricePerKm < 20       -> "Cheap route"
//    pricePerKm <= 50      -> "Normal route"
//    pricePerKm > 50       -> "Expensive route"
//    Nad každou z těchto kategorií spočtěte průměrnou cenu. To uložte jako mapu.

        return s.getPrice() / s.getDistance();
    }



    public static void main(String[] args) throws IOException {
        //nacist data:
        List<Shipment> shipments = Files.lines(Path.of("data/cargo_transport.csv"))
                .skip(1)
                .map(line -> line.trim().split(";"))
                .map(t -> new Shipment(
                        t[0],
                        Double.parseDouble(t[4]),
                        Double.parseDouble(t[5]),
                        Integer.parseInt(t[6]),
                        LocalDate.parse(t[8], DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                ))
                .toList();

        //jednotlive ukoly:


    //Var B:
        //1
        Map<String, List<Shipment>> byDelivery = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::delivery));

        byDelivery.forEach(
                (d, s) -> System.out.println(d + " \n- " + s)
        );

        //2
        Map<String, Long> byDate = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::byDate, Collectors.counting()));

        byDate.forEach(
                (d, s) -> System.out.println(d + " - " + s)
        );


        //3
        Map<String, Double> byPrice = shipments.stream()
                .collect(Collectors.groupingBy(ShipmentMapping::priceCatgory, Collectors.averagingDouble(ShipmentMapping::fullPrice)));

        byPrice.forEach(
                (d, s) -> System.out.println(d + " - " + s)
        );

    }
}
class Shipment {
    String company;
    double distance;
    double price;
    int deliveryDays;
    LocalDate date;

    public Shipment(String company, double distance, double price, int deliveryDays, LocalDate date) {
        this.company = company;
        this.distance = distance;
        this.price = price;
        this.deliveryDays = deliveryDays;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "company='" + company + '\'' +
                ", distance=" + distance +
                ", price=" + price +
                ", deliveryDays=" + deliveryDays +
                ", date=" + date +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }
}
