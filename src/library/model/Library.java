package library.model;

public class Library {

    private static final int MAX_PUBLICATONS = 2000;
    private int publicationsNumber;
    private Publication[] publications = new Publication[MAX_PUBLICATONS];

    public void addBook(Book book) {
        if (publicationsNumber < MAX_PUBLICATONS) {
            publications[publicationsNumber] = book;
            publicationsNumber++;
        } else {
            System.out.println("\nThe maximum number of books has been reached\n");
        }

    }

    public void printBooks() {
        int countBooks = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Book) {
                publications[i].printInfo();
                countBooks++;
            }
        }
        if (countBooks == 0) {
            System.out.println("\nNo books in the library\n");
        }
    }

    public void addMagazine(Magazine magazine) {
        if (publicationsNumber < MAX_PUBLICATONS) {
            publications[publicationsNumber] = magazine;
            publicationsNumber++;
        } else {
            System.out.println("\nThe maximum number of magazines has been reached\n");
        }

    }

    public void printMagazines() {
        int countMagazines = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine) {
                publications[i].printInfo();
                countMagazines++;
            }
        }
        if (countMagazines == 0) {
            System.out.println("\nNo magazines in the library\n");
        }
    }

}
