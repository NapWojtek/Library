package library.app;

import library.exception.DataExportException;
import library.exception.DataImportException;
import library.exception.InvalidDataException;
import library.exception.NoSuchOptionException;
import library.io.ConsolePrinter;
import library.io.DataReader;
import library.io.file.FileManager;
import library.io.file.FileManagerBuilder;
import library.model.Book;
import library.model.Library;
import library.model.Magazine;
import library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Library library = new Library();

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
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
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
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
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
        PRINT_MAGAZINES(4, "Display available magazines");

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
