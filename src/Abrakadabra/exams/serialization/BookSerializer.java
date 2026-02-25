package Abrakadabra.exams.serialization;

import java.io.*;
import java.util.*;

public class BookSerializer {

    static Comparator<Book> BY_RATING = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
    };

    public static void main(String[] args) {
        // načíst soubor
        // serializovat BookList
        // vypsat deserializovaný soubor

        String path = "data/books.txt";
        List<Book> books = new ArrayList<>();
        File f = new File(path);

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(";");

                books.add(new Book(parts[0], parts[1], Integer.parseInt(parts[3]), Integer.parseInt(parts[2]), Double.parseDouble(parts[4].replace(",","."))));
            }

            books.sort(BY_RATING);
            Collections.reverse(books);
            System.out.println(books);

            List<Book> bestBooks = new ArrayList<>();
            Double avrage = 0.0;
            for (int i = 0; i < 10; i++) {
                avrage += books.get(i).getRating();
                bestBooks.add(books.get(i));
            }
            avrage = avrage / 10;

            BookList bookSer = new BookList(bestBooks, avrage);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.ser"));
            oos.writeObject(bookSer);
            oos.close();

        } catch (FileNotFoundException e) {
            System.out.println("Soubor nenalezen.");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }

        deserializeBookList("books.ser");

    }

    /**
     * Metoda deserializuje soubor a vypíše jeho obsah
     * @param path cesta k souboru
     */
    public static void deserializeBookList(String path){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            BookList toRead;
            toRead = (BookList) ois.readObject();
            System.out.println(toRead.getBooks());
            System.out.println("průměr: " + toRead.getAvgRating());


        } catch (IOException e) {
            System.out.println("Something went wrong with reading data" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong class");
        }
    }
}
