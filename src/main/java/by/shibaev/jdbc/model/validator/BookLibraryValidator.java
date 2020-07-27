package by.shibaev.jdbc.model.validator;

public class BookLibraryValidator {
    private static final int MAX_SIZE = 100;
    private static BookLibraryValidator instance;

    private BookLibraryValidator(){}

    public static BookLibraryValidator getInstance() {
        if (instance == null) {
            instance = new BookLibraryValidator();
        }
        return instance;
    }

    public boolean validateLibrary(BookLibrary library) {
        boolean result = true;
        if (library.size() >= MAX_SIZE) {
            result = false;
        }
        return result;
    }
}
