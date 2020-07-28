package by.shibaev.jdbc.controller.command.impl;


import by.shibaev.jdbc.controller.command.CommandProcessor;
import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.ServiceException;
import by.shibaev.jdbc.model.service.BookLibraryService;
import by.shibaev.jdbc.model.service.impl.BookLibraryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllCommandProcessorImpl implements CommandProcessor {
    @Override
    public Map<String, List<Book>> process(Map<String, String> data) {
        BookLibraryService service = BookLibraryServiceImpl.getInstance();
        List<Book> books = new ArrayList<>();
        try {
            books = service.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Map<String, List<Book>> response = new HashMap<>();
        response.put(POSITIVE_RESPONSE, books);
        return response;
    }
}
