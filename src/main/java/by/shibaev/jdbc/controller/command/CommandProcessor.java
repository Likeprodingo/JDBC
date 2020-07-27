package by.shibaev.jdbc.controller.command;


import by.shibaev.jdbc.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface CommandProcessor {
    String POSITIVE_RESPONSE = "complete";
    String NEGATIVE_RESPONSE = "fail";

    Map<String, List<Book>> process(Map<String, String> data);
}
