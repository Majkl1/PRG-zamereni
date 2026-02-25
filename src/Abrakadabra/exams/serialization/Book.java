package Abrakadabra.exams.serialization;

// TODO: make it serializable
// Add getters/setters/constructor/toString

import java.io.Serial;
import java.io.Serializable;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public String name;
    public String author;

    public int numberOfPages;
    public int yearOfRelease;

    public double rating; // 1.0-5.0

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", yearOfRelease=" + yearOfRelease +
                ", rating=" + rating +
                '}' + '\n';
    }

    public Book(String name, String author, int numberOfPages, int yearOfRelease, double rating) {
        this.name = name;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
