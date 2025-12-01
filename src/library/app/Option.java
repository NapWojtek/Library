package library.app;

public enum Option {
    EXIT(0, "Exit the program"),
    ADD_BOOK(1, "Add a new bok"),
    ADD_MAGAZINE(2,"Add a new magazine"),
    PRINT_BOOKS(3, "Display available books"),
    PRINT_MAGAZINES(4, "Display available magazines");

    private int value;
    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    Option(int value, String desc) {
        this.value = value;
        this.description = desc;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    static Option createFromInt(int option) {
        return Option.values()[option];
    }
}