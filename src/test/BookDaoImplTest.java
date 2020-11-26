import bean.Book;
import bean.Page;
import dao.BookDao;
import dao.impl.BookDaoImpl;
import org.junit.Test;

import javax.jws.Oneway;
import java.util.List;

/**
 * @Auther 董升
 * @Date 2020/11/20
 */
public class BookDaoImplTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void getAllBooks() {
        List<Book> allBooks = bookDao.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "书名", "作者", 100, 1000, 100, null));


    }

    @Test
    public void delBookById() {
        bookDao.delBookById("54");
    }

    @Test
    public void getBookById() {
        Book bookById = bookDao.getBookById("1");
        System.out.println(bookById);
    }

    @Test
    public void updateBook() {
        Book book = new Book(66, "t1", "作者", 100, 1000, 10000, null);
        bookDao.updateBook(book);
    }

    @Test
    public void getBooksByPage() {
        Page<Book> booksByPage = bookDao.getBooksByPage(new Page<Book>(1));
        for (Book book : booksByPage.getList()) {
            System.out.println(book);
        }
    }


    @Test
    public void getBooksByPageAndPrice() {
        Page<Book> booksByPageAndPrice = bookDao.getBooksByPageAndPrice(new Page<>(1), 10, 20);
        for (Book book : booksByPageAndPrice.getList()) {
            System.out.println(book);

        }
    }
}