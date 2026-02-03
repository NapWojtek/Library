package library.app;

import library.exception.*;
import library.io.ConsolePrinter;
import library.io.DataReader;
import library.io.file.FileManager;
import library.io.file.FileManagerBuilder;
import library.model.*;
import library.model.comparator.AlphabeticalTitleComparator;

import java.util.Arrays;
import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Imported data from file");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("A new database has been initialized.");
            library = new Library();
        }
    }

    public void controlLoop() {
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZINE:
                    deleteMagazine();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("There is no such option, please enter it again: ");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", please enter again:");
            } catch (InputMismatchException ignored) {
                printer.printLine("A value that is not a number was entered, please try again:");
            }
        }

        return option;
    }

    private void printOptions() {
        printer.printLine("Choose an option: ");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }

    private void addUser() {
        LibraryUser libraryUser = dataReader.createLibraryUser();
        try {
            library.addUser(libraryUser);
        } catch (UserAlreadyExistsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create the book, invalid data provided");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Capacity limit reached, cannot add another book");
        }
    }

    private void printBooks() {
        printer.printBooks(library.getPublications().values());
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create the magazine, invalid data provided");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Capacity limit reached, cannot add another magazine");
        }
    }

    private void printMagazines() {
        printer.printMagazines(library.getPublications().values());
    }

    private void printUsers() {
        printer.printUsers(library.getUsers().values());
    }

    /*private Publication[] getSortedPublications() {
        Publication[] publications = library.getPublications();
        Arrays.sort(publications, new AlphabeticalTitleComparator());
        return publications;
    }*/

    private void deleteMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            if (library.removePublication(magazine))
                printer.printLine("Magazine deleted.");
            else
                printer.printLine("The specified magazine does not exist.");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create magazine, invalid data.");
        }
    }

    private void deleteBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book))
                printer.printLine("Book deleted.");
            else
                printer.printLine("The specified book does not exist.");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create book, invalid data.");
        }
    }

    private void exit() {
        try{
            fileManager.exportData(library);
            printer.printLine("Exporting data to a file completed");
        }
        catch (DataExportException e)
        {
            printer.printLine(e.getMessage());
        }
        printer.printLine("\nEnd of the program, bye!");
        dataReader.close();
    }
    private enum Option {
        EXIT(0, "Exit the program"),
        ADD_BOOK(1, "Add a new bok"),
        ADD_MAGAZINE(2,"Add a new magazine"),
        PRINT_BOOKS(3, "Display available books"),
        PRINT_MAGAZINES(4, "Display available magazines"),
        DELETE_BOOK(5, "Delete a book"),
        DELETE_MAGAZINE(6, "Delete a magazine");

        private int value;
        private String description;

        Option(int value, String desc) {
            this.value = value;
            this.description = desc;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option  createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Option with this ID not found " + option);
            }
        }
    }
}
