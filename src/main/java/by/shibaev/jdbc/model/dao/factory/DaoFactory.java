package by.shibaev.jdbc.model.dao.factory;

import by.shibaev.jdbc.model.dao.BookListDao;
import by.shibaev.jdbc.model.dao.impl.BookListDaoImpl;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final BookListDao bookListDAOImpl = new BookListDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public BookListDao getBookListDao() {
        return bookListDAOImpl;
    }
}
