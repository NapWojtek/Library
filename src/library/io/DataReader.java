package library.io;

import library.model.Book;
import library.model.Magazine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {

    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void close() {
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            throw e;
        } finally {
            sc.nextLine();
        }
    }

    public Book readAndCreateBook() {
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("Author: ");
        String author = sc.nextLine();
        System.out.println("Publishing house: ");
        String publisher = sc.nextLine();
        System.out.println("ISBN: ");
        String isbn = sc.nextLine();
        System.out.println("Year of publication: ");
        int releaseDate = sc.nextInt();
        sc.nextLine();
        System.out.println("Number of pages: ");
        int pages = sc.nextInt();
        sc.nextLine();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("Author: ");
        String publisher = sc.nextLine();
        System.out.println("Language: ");
        String language = sc.nextLine();
        System.out.println("Year of publication: ");
        int year = getInt();
        System.out.println("Month: ");
        int month = getInt();
        System.out.println("Day: ");
        int day = getInt();

        return new Magazine(title, publisher, language, year, month, day);
    }

}
