package service.impl;

import bean.Book;
import bean.Page;
import dao.BookDao;
import dao.impl.BookDaoImpl;
import service.BookService;

import java.util.List;

/**
 * @Auther Ashen One
 * @Date 2020/11/20
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void delBookById(String id) {
        bookDao.delBookById(id);
    }

    @Override
    public Book getBookById(String id) {
        return bookDao.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Page<Book> getBooksByPage(String pageNo) {
        return bookDao.getBooksByPage(new Page<>(pageNo == null ? 1 : Integer.parseInt(pageNo)));

    }

    @Override
    public Page<Book> getBooksByPageAndPrice(String pageNo, String min, String max) {
        int pNo = 1;
        try {
            pNo = Integer.parseInt(pageNo);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        //处理min，max
        double minEnd = 0;
        double maxEnd = Double.MAX_VALUE;
        try {
            minEnd = Double.parseDouble(min);
            maxEnd = Double.parseDouble(max);
            //交换值 用户输入数据前大后小
            double temp = 0;
            if (minEnd > maxEnd) {
                temp = minEnd;
                minEnd = maxEnd;
                maxEnd = temp;
            }
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return bookDao.getBooksByPageAndPrice(new Page<>(pNo), minEnd, maxEnd);
    }


}
