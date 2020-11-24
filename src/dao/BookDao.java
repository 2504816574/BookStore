package dao;

import bean.Book;
import bean.Page;

import java.util.List;

/**
 * @Auther 董升
 * @Date 2020/11/20
 */
public interface BookDao {
    /**
     * 查询所有图书信息
     * String sql ="SELECT id ,title ,author,price,sales,stock,img_path FROM books";
     *
     * @return
     */
    List<Book> getAllBooks();

    /**
     * 新增图书
     * String sql ="insert into books values(null,?,?,?,?,?,?)";
     *
     * @param book
     * @return
     */
    void addBook(Book book);

    /**
     * 通过id删除书籍
     *
     * @param id
     */
    void delBookById(String id);

    /**
     * String sql ="SELECT id ,title ,author,price,sales,stock,img_path FROM books where id = ? ";
     * 通过id查书
     *
     * @param id
     */
    Book getBookById(String id);

    /**
     * update
     *
     * @param book
     */
    void updateBook(Book book);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Book> getBooksByPage(Page<Book> page);
}