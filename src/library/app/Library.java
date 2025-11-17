package library.app;

import library.io.DataReader;
import library.model.Book;

public class Library {
    public static void main(String[] args) {

        final String appName = "Biblioteka v0.8";

        Book[] books = new Book[1000];
        DataReader dataReader = new DataReader();

        System.out.println(appName);
        System.out.println("Enter a new book:");
        books[1] = dataReader.readAndCreateBook();
        books[2] = dataReader.readAndCreateBook();
        System.out.println("The system can store up to " + books.length + " books");

    }
}
