package library.app;

import library.io.DataReader;
import library.model.Book;
import library.model.Library;

public class LibraryControl {

    private final int exit = 0;
    private final int addBook = 1;
    private final int printBooks = 2;

    private DataReader dataReader = new DataReader();

    private Library library = new Library();

    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case addBook:
                    addBook();
                    break;
                case printBooks:
                    printBooks();
                    break;
                case exit:
                    exit();
                    break;
                default:
                    System.out.println("There is no such option, please enter it again: ");
            }
        } while(option != exit);
    }

    private void printOptions() {
        System.out.println("Choose an option: ");
        System.out.println(exit + " - exit the program");
        System.out.println(addBook + " - add a new book");
        System.out.println(printBooks + " - display available books");
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printBooks() {
        library.printBooks();
    }

    private void exit() {
        System.out.println("\nEnd of the program, bye!");
    }

}
