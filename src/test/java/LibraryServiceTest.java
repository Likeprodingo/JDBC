import by.shibaev.jdbc.model.entity.Book;
import by.shibaev.jdbc.model.exception.ServiceException;
import by.shibaev.jdbc.model.service.BookLibraryService;
import by.shibaev.jdbc.model.service.impl.BookLibraryServiceImpl;
import by.shibaev.jdbc.model.util.DataParameter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LibraryServiceTest {
    @Test
    public void addingTest() throws ServiceException {
        BookLibraryService bookLibraryService = BookLibraryServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(DataParameter.NAME,"Misha");
        parameters.put(DataParameter.PUBLISHER,"Viva");
        parameters.put(DataParameter.YEAR,"2005");
        parameters.put("author1","Vova");
        parameters.put("author2","Mira");
        bookLibraryService.add(parameters);
        List<Book> expected  = new ArrayList<Book>();
        List<String> authors = new ArrayList<String>();
        authors.add("Vova");
        authors.add("Mira");
        BookLibrary bookLibrary = BookLibrary.getInstance();
        assertTrue(bookLibrary.contains(new Book("Misha",authors,"Viva",2005)));
    }
}
