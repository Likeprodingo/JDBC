package by.shibaev.jdbc.model.service.impl;

import by.shibaev.jdbc.model.dao.BookListDao;
import by.shibaev.jdbc.model.dao.factory.DaoFactory;
import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.DaoException;
import by.shibaev.jdbc.model.exception.ServiceException;
import by.shibaev.jdbc.model.service.BookLibraryService;
import by.shibaev.jdbc.model.service.DataParameter;
import by.shibaev.jdbc.model.validator.BookValidator;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class BookLibraryServiceImpl implements BookLibraryService {

    private static BookLibraryServiceImpl instance;

    private BookLibraryServiceImpl() {
    }

    public static BookLibraryServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookLibraryServiceImpl();
        }
        return instance;
    }

    public void add(Map<String, String> bookData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookValidator bookValidator = BookValidator.getInstance();
        List<String> authors = new ArrayList<String>();
        if (!bookValidator.isDataValid(bookData)) {
            throw new ServiceException("Wrong data");
        }
        for (String key : bookData.keySet()) {
            if (Pattern.matches(DataParameter.AUTHOR_PATTERN,key)) {
                authors.add(bookData.get(key));
            }
        }
        int year = Integer.parseInt(bookData.get(DataParameter.YEAR));
        String name = bookData.get(DataParameter.NAME);
        String publisher = bookData.get(DataParameter.PUBLISHER);
        Book book = new Book(name, authors, publisher, year);
        try {
            bookListDao.add(book);
        } catch (DaoException e) {
            throw new ServiceException("Adding exception", e);
        }
    }

    public void remove(Map<String, String> bookData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookValidator bookValidator = BookValidator.getInstance();
        List<String> authors = new ArrayList<String>();
        if (!bookValidator.isDataValid(bookData)) {
            throw new ServiceException("Wrong data");
        }
        for (String key : bookData.keySet()) {
            if (Pattern.matches(key, DataParameter.AUTHOR_PATTERN)) {
                authors.add(bookData.get(key));
            }
        }
        int year = Integer.parseInt(bookData.get(DataParameter.YEAR));
        String name = bookData.get(DataParameter.NAME);
        String publisher = bookData.get(DataParameter.PUBLISHER);
        Book book = new Book(name, authors, publisher, year);
        try {
            bookListDao.remove(book);
        } catch (DaoException e) {
            throw new ServiceException("Removing exception", e);
        }
    }

    public Book find(Map<String, String> bookData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        String id = bookData.get(DataParameter.ID);
        Book book;
        if (id == null) {
            throw new ServiceException("Wrong data");
        }
        try {
            book = bookListDao.selectById(id);
        } catch (DaoException e) {
            throw new ServiceException("Doesn't exists", e);
        }
        return book;
    }

    public List<Book> findByName(Map<String, String> bookData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookValidator bookValidator = BookValidator.getInstance();
        String name = bookData.get(DataParameter.NAME);
        List<Book> books;
        if (name == null) {
            throw new ServiceException("Wrong data");
        }
        if (!bookValidator.isWordValid(name)) {
            throw new ServiceException("Wrong parameter");
        }
        try {
            books = bookListDao.selectByName(name);

        } catch (DaoException e) {
            throw new ServiceException("Doesn't exists", e);
        }
        return books;
    }

    public List<Book> findByAuthor(Map<String, String> bookData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookValidator bookValidator = BookValidator.getInstance();
        String author = bookData.get(DataParameter.AUTHOR);
        List<Book> books;
        if (author == null) {
            throw new ServiceException("Wrong data");
        }
        if (!bookValidator.isWordValid(author)) {
            throw new ServiceException("Wrong parameter");
        }
        try {
            books = bookListDao.selectByAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException("Doesn't exists", e);
        }
        return books;
    }

    public List<Book> findByPublisher(Map<String, String> bookData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookValidator bookValidator = BookValidator.getInstance();
        String publisher = bookData.get(DataParameter.PUBLISHER);
        List<Book> books;
        if (publisher == null) {
            throw new ServiceException("Wrong data");
        }
        if (!bookValidator.isWordValid(publisher)) {
            throw new ServiceException("Wrong parameter");
        }
        try {
            books = bookListDao.selectByPublisher(publisher);
        } catch (DaoException e) {
            throw new ServiceException("Doesn't exists", e);
        }
        return books;
    }

    public List<Book> findByYear(Map<String, String> bookData) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        BookValidator bookValidator = BookValidator.getInstance();
        String year = bookData.get(DataParameter.YEAR);
        List<Book> books;
        int yearVal;
        if (year == null) {
            throw new ServiceException("Wrong data");
        }
        if (!bookValidator.isYearValid(year)) {
            throw new ServiceException("Wrong parameter");
        }
        try {
            books = bookListDao.selectByYear(Integer.parseInt(year));
        } catch (DaoException e) {
            throw new ServiceException("Doesn't exists", e);
        }
        return books;
    }

    public List<Book> findAll() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookListDao bookListDao = daoFactory.getBookListDao();
        List<Book> books;
        try {
            books = bookListDao.selectAll();
        } catch (DaoException e) {
            throw new ServiceException("Doesn't exists", e);
        }
        return books;
    }
}
