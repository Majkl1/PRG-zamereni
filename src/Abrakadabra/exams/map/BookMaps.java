package Abrakadabra.exams.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class BookMaps {

    public static void printAuthorPages(List<Book> books, String name){

        // Metoda vypíše celkový počet stránek všech knížek od autora jménem "name"
        System.out.println(name + ": ");
        List<Book> filtered = books.stream()
                .filter(b -> b.getAuthor().equals(name))
                .toList();
        int totalPages = 0;
        for (Book b : filtered){
            totalPages += b.getTotalPages();
        }
        System.out.println(" - " + totalPages);

    }

    public static int returnAuthorPages(List<Book> books, String name){
        List<Book> filtered = books.stream()
                .filter(b -> b.getAuthor().equals(name))
                .toList();
        int totalPages = 0;
        for (Book b : filtered){
            totalPages += b.getTotalPages();
        }
        return totalPages;

    }

    public static void  printAuthorBooks(List<Book> books, String author){
        System.out.println(author + " - " + books.stream()
                .filter(b -> b.getAuthor().equals(author))
                .count());
    }

    public static void main(String[] args) throws IOException {
        // Dodělejte třídu Book a Chapter (gettery, settery, constructor, toString + co uznáte za vhodné)
        // Načtěte soubory books.csv a chapters.csv
        // Implementujte metodu printAuthorPages
        // Vypište, kolik knížek napsal každý autor
        // Vypište top 5 autorů dle počtu napsaných stran
        List<Book> books = Files.lines(Path.of("data/mapData/books.csv"))
                .skip(1)
                .map(line -> line.trim().split(","))
                .map(t -> new Book(
                        Integer.parseInt(t[0]),
                        t[1],
                        t[2]
                )).toList();

        List<Chapter> chapters = Files.lines(Path.of("data/mapData/chapters.csv"))
                .skip(1)
                .map(line -> line.trim().split(","))
                .map(t -> new Chapter(
                        Integer.parseInt(t[1]),
                        t[2],
                        Integer.parseInt(t[3])
                )).toList();

        HashMap<Integer, Book> refermap = new HashMap<>();
        books.forEach(b -> refermap.put(b.getBookId(), b));
        chapters.forEach(ch -> refermap.get(ch.getBookID()).getChapters().add(ch));

        List<String> authors = books.stream()
                .map(b -> b.getAuthor())
                .distinct()
                .toList();

        printAuthorPages(books, "William Mitchell");
        authors.forEach(a -> printAuthorBooks(books, a));

        Map<String, Integer> authorPages = new HashMap<>();
        for (String a : authors){
            authorPages.put(a, returnAuthorPages(books,a));
        }


        List<Author> authorsList = new ArrayList<>();
        for (String a : authors){
            authorsList.add(new Author(a, returnAuthorPages(books,a)));
        }

        List<Author> filteredAuthorList = authorsList.stream()
                .sorted(Comparator.comparingInt(Author::getPages).reversed())
                .toList();

        for (int i = 0; i < 5; i++) {
            System.out.println(filteredAuthorList.get(i));
        }
    }
}

class Author{
    private String name;
    private int pages;

    public Author(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                '}' +"\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

class Book{
    private int bookId;
    private String title;
    private String author;
    private List<Chapter> chapters = new ArrayList<>();
    public int getTotalPages(){
        int allPages = 0;
        for (Chapter ch : chapters){
            allPages += ch.getPages();
        }
        return allPages;
    }

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}' + "\n";
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}

class Chapter{
    private int bookID;
    private String title;
    private int pages;

    public Chapter(int bookID, String title, int pages) {
        this.bookID = bookID;
        this.title = title;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                '}' + "\n";
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}