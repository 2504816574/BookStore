package dao;

import bean.Book;
import bean.Page;

import java.util.List;

/**
 * @Auther Ashen One
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
     * 修改销量库存
     *
     * @param stock 库存
     * @param sales 销量
     * @param id
     */
    void updateBook(int stock, int sales, int id);

    /**
     * 批处理更新
     * @param params
     */
    void updateBook(Object[][] params);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<Book> getBooksByPage(Page<Book> page);

    /**
     * 价格分页查询
     *
     * @param page
     * @param min
     * @param max
     * @return
     */
    Page<Book> getBooksByPageAndPrice(Page<Book> page, double min, double max);
}