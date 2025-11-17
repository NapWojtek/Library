package library.app;
import library.model.Book;

public class Library {
    public static void main(String[] args) {

        final String appName = "Biblioteka v0.7";

        Book[] books = new Book[1000];
        books [1] = new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", 2010, 296, "Greg", "9788373271890");
        books [2] = new Book("Java. Efektywne programowanie. Wydanie II", "Joshua Bloch", 2009, 352, "Helion",
                "9788324620845");
        books [3] = new Book("SCJP Sun Certified Programmer for Java 6 Study Guide", "Bert Bates, Katherine Sierra",
                2008, 851, "McGraw-Hill Osborne Media");

        System.out.println(appName);
        System.out.println("Books available in the library:");
        books [1].printInfo();
        books [2].printInfo();
        books [3].printInfo();
        System.out.println("The system can store up to " + books.length + " books");

    }
}
