package by.shibaev.jdbc.model.dao;

import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.DaoException;

import java.util.List;

public interface BookListDao {
    void add(Book book) throws DaoException;

    void remove(Book book) throws DaoException;

    Book selectById(String id) throws DaoException;

    List<Book> selectByName(String name) throws DaoException;

    List<Book> selectByAuthor(String author) throws DaoException;

    List<Book> selectByPublisher(String publisher) throws DaoException;

    List<Book> selectByYear(int year) throws DaoException;

    List<Book> selectAll() throws DaoException;
}
