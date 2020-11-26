package service;

import bean.Book;
import bean.Page;

import java.util.List;

/**
 * @Auther Ashen One
 * @Date 2020/11/20
 */
public interface BookService {
    /**
     * 查询所有图书信息
     * @return
     */
    List<Book> getAllBooks();

    /**
     * 新增书籍
     * @param book
     * @return
     */
    void addBook(Book book);

    /**
     * 删除图书
     * @param id
     */
    void delBookById(String id);

    /**
     * 通过id查书
     * @param id
     * @return
     */
    Book getBookById(String id);

    /**
     * 更新书籍信息
     * @param book
     */
    void updateBook(Book book);

    /**
     * 分页查询
     * @param pageNo
     * @return
     */
    Page<Book> getBooksByPage(String pageNo);

    /**
     * 价格分页查询
     * @param pageNo
     * @param min
     * @param max
     * @return
     */
    Page<Book> getBooksByPageAndPrice(String pageNo,String min,String max);
}
