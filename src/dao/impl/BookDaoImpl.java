package dao.impl;

import bean.Book;
import bean.Page;
import dao.BaseDao;
import dao.BookDao;

import java.util.List;

/**
 * @Auther 董升
 * @Date 2020/11/20
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> getAllBooks() {
        String sql = "SELECT id ,title ,author,price,sales,stock,img_path FROM books";
        return this.getBeanList(sql);
    }

    @Override
    public void addBook(Book book) {
        String sql = "insert into books values(null,?,?,?,?,?,?)";
        this.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());


    }

    @Override
    public void delBookById(String id) {
        String sql = "DELETE FROM books WHERE id = ?";
        this.update(sql, id);
    }

    @Override
    public Book getBookById(String id) {
        String sql = "SELECT id ,title ,author,price,sales,stock,img_path FROM books where id = ?";
        return this.getBean(sql, id);

    }

    @Override
    public void updateBook(Book book) {
        String sql = "update books SET title=?,author =?,price=?,sales=?,stock=? WHERE id=?";
        this.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
    }

    @Override
    public void updateBook(int stock, int sales, int id) {
        String sql = "update books SET sales=?,stock=? WHERE id=?";
        this.update(sql, sales, stock, id);
    }

    @Override
    public Page<Book> getBooksByPage(Page<Book> page) {
        String sqlCount = "select count(*) from books";
        int count = Integer.parseInt(this.getSingleValue(sqlCount) + "");//因为当遇到oeject为null时，调用toString()方法会报nullpointexception异常，而通过+”“则不会抛出异常
        page.setTotalRecord(count);//总条数
        String sqlData = "SELECT id,title,author,price,sales,stock,img_path FROM books limit ? ,?";
        List<Book> bookList = this.getBeanList(sqlData, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        //list放入Page中的List<Book>中
        page.setList(bookList);
        return page;
    }

    @Override
    public Page<Book> getBooksByPageAndPrice(Page<Book> page, double min, double max) {
        String sqlCount = "select count(*) from books WHERE price between ? AND ? ";
        int count = Integer.parseInt(this.getSingleValue(sqlCount, min, max) + "");//因为当遇到oeject为null时，调用toString()方法会报nullpointexception异常，而通过+”“则不会抛出异常
        page.setTotalRecord(count);//总条数
        String sqlData = "SELECT id,title,author,price,sales,stock,img_path FROM books WHERE price between ? AND ? LIMIT ? ,?";
        List<Book> bookList = this.getBeanList(sqlData, min, max, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        //list放入Page中的List<Book>中
        page.setList(bookList);
        return page;
    }
}
