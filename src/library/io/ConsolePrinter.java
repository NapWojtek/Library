package library.io;

import library.model.Book;
import library.model.Magazine;
import library.model.Publication;

public class ConsolePrinter {
    public void printBooks(Publication[] publications) {
        int counter = 0;
        for (Publication publication : publications) {
            if(publication instanceof Book) {
                printLine(publication.toString());
                counter++;
            }
        }
        if (counter == 0)
            printLine("\nNo books in the library\n");
    }

    public void printMagazines(Publication[] publications) {
        int counter = 0;
        for (Publication publication : publications) {
            if(publication instanceof Magazine) {
                printLine(publication.toString());
                counter++;
            }
        }
        if (counter == 0)
            printLine("\nNo magazines in the library\n");
    }

    public void printLine(String text) {
        System.out.println(text);
    }
}
