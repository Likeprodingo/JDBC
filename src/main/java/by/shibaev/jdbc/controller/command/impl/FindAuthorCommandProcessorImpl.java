package by.shibaev.jdbc.controller.command.impl;


import by.shibaev.jdbc.controller.command.CommandProcessor;
import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.DaoException;
import by.shibaev.jdbc.model.exception.ServiceException;
import by.shibaev.jdbc.model.service.BookLibraryService;
import by.shibaev.jdbc.model.service.impl.BookLibraryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAuthorCommandProcessorImpl implements CommandProcessor {
    @Override
    public Map<String, List<Book>> process(Map<String, String> data) {
        BookLibraryService service = BookLibraryServiceImpl.getInstance();
        List<Book> books = new ArrayList<>();
        String responseMessage = POSITIVE_RESPONSE;
        Map<String, List<Book>> response = new HashMap<>();
        try {
            service.findByAuthor(data);
        } catch (ServiceException e) {
            responseMessage = NEGATIVE_RESPONSE;
        }
        response.put(responseMessage, books);
        return response;
    }
}
