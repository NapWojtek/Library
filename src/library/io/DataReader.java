package library.io;

import library.model.Book;

import java.util.Scanner;

public class DataReader {

    private Scanner sc = new Scanner(System.in);

    public int getInt() {
        int number = sc.nextInt();
        sc.nextLine();
        return number;
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

}
