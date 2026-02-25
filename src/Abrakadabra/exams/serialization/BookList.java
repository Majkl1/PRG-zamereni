package Abrakadabra.exams.serialization;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;

// TODO: make it serializable
public class BookList implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Book> books;
    private double avgRating;

    public BookList(List<Book> books, double avgRating) {
        this.books = books;
        this.avgRating = avgRating;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}
