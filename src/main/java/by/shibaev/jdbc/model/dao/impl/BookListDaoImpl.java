package by.shibaev.jdbc.model.dao.impl;

import by.shibaev.jdbc.model.dao.BookListDao;
import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.DaoException;
import by.shibaev.jdbc.model.service.DataParameter;

import java.sql.*;
import java.util.*;

public class BookListDaoImpl implements BookListDao {

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String ADD_REQUEST = "insert into books(id, name, publisher,year,author) values (?,?,?,?,?)";
    private static final String SELECT_BY = "select (id,name,publisher,year,author) from books where ? = ?";
    private static final String SELECT_ALL = "select id,name,publisher,year,author from books";
    private static final String DELETE = "delete from books where id = ?";

    private Properties getProperties() throws DaoException {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DaoException("Connection error");
        }
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "root");
        properties.put("autoReconnect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("useUnicode", "true");
        return properties;
    }

    public void add(Book book) throws DaoException {
        StringBuilder authors = new StringBuilder("");
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(ADD_REQUEST);
            statement.setString(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getPublishYear());
            statement.setString(5, book.getAuthors().toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Book book) throws DaoException {
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setString(1,book.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Connection exception", e);
        }
    }

    public Book selectById(String id) throws DaoException {
        Book book;
        String name;
        int year;
        String publisher;
        List<String> authors;
        String authorsString;
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY);
            statement.setString(1, DataParameter.ID);
            statement.setString(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString(2);
                publisher = resultSet.getString(3);
                year = resultSet.getInt(4);
                authorsString = resultSet.getString(5);
                authors = Arrays.asList(authorsString.split(", "));
                book = new Book(id, name, authors, publisher, year);
            } else {
                throw new DaoException("That book doesn't exists");
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception", e);
        }
        return book;
    }

    public List<Book> selectByName(String name) throws DaoException {
        List<Book> books = new ArrayList<>();
        String id;
        int year;
        String publisher;
        List<String> authors;
        String authorsString;
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY);
            statement.setString(1, DataParameter.NAME);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getString(1);
                publisher = resultSet.getString(3);
                year = resultSet.getInt(4);
                authorsString = resultSet.getString(5);
                authors = Arrays.asList(authorsString.split(", "));
                books.add(new Book(id, name, authors, publisher, year));
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception", e);
        }
        return books;
    }

    public List<Book> selectByAuthor(String author) throws DaoException {
        List<Book> books = new ArrayList<>();
        String id;
        String name;
        int year;
        String publisher;
        List<String> authors;
        String authorsString;
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authorsString = resultSet.getString(5);
                authors = Arrays.asList(authorsString.split(", "));
                if (authors.contains(author)) {
                    id = resultSet.getString(1);
                    name = resultSet.getString(2);
                    publisher = resultSet.getString(3);
                    year = resultSet.getInt(4);
                    books.add(new Book(id, name, authors, publisher, year));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception", e);
        }
        return books;
    }

    public List<Book> selectByPublisher(String publisher) throws DaoException {
        List<Book> books = new ArrayList<>();
        String id;
        String name;
        int year;
        List<String> authors;
        String authorsString;
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY);
            statement.setString(1, DataParameter.PUBLISHER);
            statement.setString(2, publisher);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                year = resultSet.getInt(4);
                authorsString = resultSet.getString(5);
                authors = Arrays.asList(authorsString.split(", "));
                books.add(new Book(id, name, authors, publisher, year));
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception", e);
        }
        return books;
    }

    public List<Book> selectByYear(int year) throws DaoException {
        List<Book> books = new ArrayList<>();
        String id;
        String name;
        String publisher;
        List<String> authors;
        String authorsString;
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY);
            statement.setString(1, DataParameter.YEAR);
            statement.setInt(2, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                publisher = resultSet.getString(3);
                authorsString = resultSet.getString(5);
                authors = Arrays.asList(authorsString.split(", "));
                books.add(new Book(id, name, authors, publisher, year));
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception", e);
        }
        return books;
    }

    public List<Book> selectAll() throws DaoException {
        List<Book> books = new ArrayList<>();
        String id;
        String name;
        String publisher;
        List<String> authors;
        String authorsString;
        int year;
        Properties properties = getProperties();
        try (Connection connection = DriverManager.getConnection(URL, properties)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getString(1);
                name = resultSet.getString(2);
                publisher = resultSet.getString(3);
                year = resultSet.getInt(4);
                authorsString = resultSet.getString(5);
                authors = Arrays.asList(authorsString.replace("[", "")
                        .replace("]", "").split(", "));
                books.add(new Book(id, name, authors, publisher, year));
                for(String a: authors){
                    System.out.println(a);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Connection exception", e);
        }
        return books;
    }
}
