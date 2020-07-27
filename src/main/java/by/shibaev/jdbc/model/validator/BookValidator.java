package by.shibaev.jdbc.model.validator;

import by.shibaev.jdbc.model.util.DataParameter;

import java.util.Map;
import java.util.regex.Pattern;

public class BookValidator {
    private static final String WORD_FORMAT = "\\w{3,20}";
    private static final int MAX_YEAR = 2020;
    private static final int MIN_YEAR = 1;

    private static BookValidator instance;

    private BookValidator() {
    }

    public static BookValidator getInstance() {
        if (instance == null) {
            instance = new BookValidator();
        }
        return instance;
    }

    public boolean isWordValid(String word) {
        boolean result = true;
        if (!Pattern.matches(WORD_FORMAT, word)) {
            result = false;
        }
        return result;
    }

    public boolean isYearValid(String strYear) {
        boolean result = true;
        int year = -1;
        try {
            year = Integer.parseInt(strYear);
        } catch (NumberFormatException e) {
            result = false;
        }
        if (year < MIN_YEAR || year > MAX_YEAR) {
            result = false;
        }
        return result;
    }

    public boolean isDataValid(Map<String, String> data) {
        boolean result = true;
        String name = data.get(DataParameter.NAME);
        String publisher = data.get(DataParameter.PUBLISHER);
        String year = data.get(DataParameter.YEAR);
        if (name == null || publisher == null || !isWordValid(name) || !isWordValid(publisher) || !isYearValid(year)) {
            result = false;
        }
        for (String key : data.keySet()) {
            if (Pattern.matches(key, DataParameter.AUTHOR_PATTERN) && !isWordValid(data.get(key))) {
                result = false;
            }
        }
        return result;
    }
}
