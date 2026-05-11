package Abrakadabra.streams.maps.magic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MagicShop {
    public static void main(String[] args) throws IOException {
        //dodělej to debile!!!!!
        Files.lines(Path.of("data/magic/kouzelne_dilny.csv"))
                .map(line -> line.trim().split(","))
                .map(t -> new MagicWorkshop(
                        Integer.parseInt(t[0]),
                        t[1],
                        t[2],
                        Integer.parseInt(t[3]),
                        t[4],
                        t[5]
                ));
    }
}

class MagicWorkshop{
    int id;
    String name;
    String city;
    int year;
    String owner;
    String specialization;

    public MagicWorkshop(int id, String name, String city, int year, String owner, String specialization) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.year = year;
        this.owner = owner;
        this.specialization = specialization;
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
    int year;
    String wood;
    String core;
    int height;
    String flexibility;
    int price;
    boolean sold;

    public MagicWand(int id, int workshopID, int year, String wood, String core, int height, String flexibility, int price, boolean sold) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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