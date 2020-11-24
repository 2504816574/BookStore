package service;

import bean.Book;
import bean.Page;

import java.util.List;

/**
 * @Auther 董升
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

    Page<Book> getBooksByPage(String pageNo);
}
