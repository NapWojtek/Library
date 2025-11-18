package library.app;

import library.io.DataReader;
import library.model.Book;
import library.model.Library;

public class LibraryControl {

    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int PRINT_BOOKS = 2;

    private DataReader dataReader = new DataReader();

    private Library library = new Library();

    public void controlLoop() {
        int option;

        do {
            printOptions();
            option = dataReader.getInt();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("There is no such option, please enter it again: ");
            }
        } while(option != EXIT);
    }

    private void printOptions() {
        System.out.println("Choose an option: ");
        System.out.println(EXIT + " - exit the program");
        System.out.println(ADD_BOOK + " - add a new book");
        System.out.println(PRINT_BOOKS + " - display available books");
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
