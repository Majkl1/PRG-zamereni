package Abrakadabra.streams.maps.magic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MagicShop {
    public static void main(String[] args) throws IOException {
        List<MagicWorkshop> workshops = Files.lines(Path.of("data/magic/kouzelne_dilny.csv"))
                .skip(1)
                .map(line -> line.trim().split(","))
                .map(t -> new MagicWorkshop(
                        Integer.parseInt(t[0]),
                        t[1],
                        t[2],
                        Integer.parseInt(t[3]),
                        t[4],
                        t[5]
                )).toList();

        List<MagicWand> wands = Files.lines(Path.of("data/magic/kouzelne_hulky.csv"))
                .skip(1)
                .map(line -> line.trim().split(","))
                .map(t -> new MagicWand(
                        Integer.parseInt(t[0]),
                        Integer.parseInt(t[1]),
                        LocalDate.parse(t[2]),
                        t[3],
                        t[4],
                        Integer.parseInt(t[5]),
                        t[6],
                        Integer.parseInt(t[7]),
                        t[8].equals("ano")
                )).toList();

        Map<Integer, MagicWorkshop> referMap = new HashMap<>();
        workshops.forEach(w -> referMap.put(w.id,w));
        wands.forEach(w -> referMap.get(w.getWorkshopID()).getWands().add(w));

        Map<MagicWorkshop, List<MagicWand>> fullMap = wands.stream()
                .collect(Collectors.groupingBy(
                        wand -> workshops.stream()
                                .filter(w -> w.getId() == wand.getWorkshopID())
                                .findFirst()
                                .orElseThrow()
                ));


        //cv1 - core == Dračí struna
        workshops.stream()
                .forEach(workshop -> workshop.getWands().stream()
                        .filter(w -> w.getCore().equals("Dračí struna"))
                        .forEach(System.out::println)
        );
        System.out.println("---------------------------------------------------");

        //cv2 - sold && wood == dub
        workshops.stream()
                .forEach(workshop -> workshop.getWands().stream()
                        .filter(w -> w.getWood().equals("Dub") && w.sold)
                        .forEach(System.out::println)
                );

        System.out.println("---------------------------------------------------");

        //cv3 - height > 33cm
        workshops.stream()
                .forEach(workshop -> workshop.getWands().stream()
                        .filter(w -> w.getHeight() > 33)
                        .forEach(System.out::println)
                );

        System.out.println("---------------------------------------------------");

        //cv4 - workshop -> specialization == bojové hůlky
        workshops.stream()
                .filter(w -> w.getSpecialization().equals("Bojové hůlky"))
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        //cv5 - price > 1000
        workshops.stream()
                .forEach(workshop -> workshop.getWands().stream()
                        .filter(w -> w.getPrice() > 1000)
                        .forEach(System.out::println)
                );

        System.out.println("---------------------------------------------------");

        //cv6 - vypsat názvy dílen sídlící v praze
        workshops.stream()
                .filter(w -> w.getCity().equals("Praha"))
                .map(w -> w.getName())
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        //cv7 - Vypsat všechny hůlky od nejdražší po nejlevnější !!!!!!nefunguje!!!!!! (idk proč)
        workshops.stream()
                .forEach(workshop -> workshop.getWands().stream()
                        .sorted(Comparator.comparingInt(MagicWand::getPrice).reversed())
                        .forEach(System.out::println)
                );

        //cv11 - hůlky seskupené podle dřeva
        Map<String, List<MagicWand>> wandsByWoodd = wands.stream()
                .collect(Collectors.groupingBy(MagicWand::getWood));
    }
}

class MagicWorkshop{
    int id;
    String name;
    String city;
    int year;
    String owner;
    String specialization;
    List<MagicWand> wands;

    public MagicWorkshop(int id, String name, String city, int year, String owner, String specialization) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.year = year;
        this.owner = owner;
        this.specialization = specialization;
        this.wands = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "MagicWorkshop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", year=" + year +
                ", owner='" + owner + '\'' +
                ", specialization='" + specialization + '\'' +
                '}' + "\n";
    }

    public List<MagicWand> getWands() {
        return wands;
    }

    public void setWands(List<MagicWand> wands) {
        this.wands = wands;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

class MagicWand{
    int id;
    int workshopID;
    LocalDate year;
    String wood;
    String core;
    int height;
    String flexibility;
    int price;
    boolean sold;


    public MagicWand(int id, int workshopID, LocalDate year, String wood, String core, int height, String flexibility, int price, boolean sold) {
        this.id = id;
        this.workshopID = workshopID;
        this.year = year;
        this.wood = wood;
        this.core = core;
        this.height = height;
        this.flexibility = flexibility;
        this.price = price;
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "MagicWand{" +
                "id=" + id +
                ", workshopID=" + workshopID +
                ", year=" + year +
                ", wood='" + wood + '\'' +
                ", core='" + core + '\'' +
                ", height=" + height +
                ", flexibility='" + flexibility + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MagicWand magicWand = (MagicWand) o;
        return price == magicWand.price;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkshopID() {
        return workshopID;
    }

    public void setWorkshopID(int workshopID) {
        this.workshopID = workshopID;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getWood() {
        return wood;
    }

    public void setWood(String wood) {
        this.wood = wood;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFlexibility() {
        return flexibility;
    }

    public void setFlexibility(String flexibility) {
        this.flexibility = flexibility;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}