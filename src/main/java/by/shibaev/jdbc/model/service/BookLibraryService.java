package by.shibaev.jdbc.model.service;

import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.DaoException;
import by.shibaev.jdbc.model.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface BookLibraryService {
    void add(Map<String, String> bookData) throws ServiceException;

    void remove(Map<String, String> bookData) throws ServiceException;

    Book find(Map<String, String> bookData) throws ServiceException;

    List<Book> findByName(Map<String, String> bookData) throws ServiceException;

    List<Book> findByAuthor(Map<String, String> bookData) throws ServiceException;

    List<Book> findByPublisher(Map<String, String> bookData) throws ServiceException;

    List<Book> findByYear(Map<String, String> bookData) throws ServiceException;

    List<Book> findAll() throws ServiceException;

}
