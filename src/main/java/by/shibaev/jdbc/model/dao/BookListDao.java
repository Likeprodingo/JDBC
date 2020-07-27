package by.shibaev.jdbc.model.dao;

import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.DaoException;

import java.util.List;

public interface BookListDao {
    void add(Book book) throws DaoException;

    void remove(Book book) throws DaoException;

    Book find(String id) throws DaoException;

    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByPublisher(String publisher);

    List<Book> findByYear(int year);

    List<Book> findAll();

    List<Book> sortByName();

    List<Book> sortById();

    List<Book> sortByAuthor();

    List<Book> sortByPublisher();

    List<Book> sortByYear();
}
