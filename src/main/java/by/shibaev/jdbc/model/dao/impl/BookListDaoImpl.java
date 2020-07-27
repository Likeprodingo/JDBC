package by.shibaev.jdbc.model.dao.impl;

import by.shibaev.jdbc.model.dao.BookListDao;
import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.DaoException;
import by.shibaev.jdbc.model.validator.BookLibraryValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookListDaoImpl implements BookListDao {
    public void add(Book book) throws DaoException {

    }

    public void remove(Book book) throws DaoException {

    }

    public Book find(String id) throws DaoException {
        return null;
    }

    public List<Book> findByName(String name) {
        return null;
    }

    public List<Book> findByAuthor(String author) {
        return null;
    }

    public List<Book> findByPublisher(String publisher) {
        return null;
    }

    public List<Book> findByYear(int year) {
        return null;
    }

    public List<Book> findAll() {
        return null;
    }

    public List<Book> sortByName() {
        return null;
    }

    public List<Book> sortById() {
        return null;
    }

    public List<Book> sortByAuthor() {
        return null;
    }

    public List<Book> sortByPublisher() {
        return null;
    }

    public List<Book> sortByYear() {
        return null;
    }
}
