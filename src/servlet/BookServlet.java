package servlet;

import bean.Book;
import bean.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Auther Ashen One
 * @Date 2020/11/20
 *
 * 使客户端页面和Book分离
 */
@WebServlet(name = "BookServlet", urlPatterns = "/BookServlet")
public class BookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();

    /**
     * 查询所有图书信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service
        List<Book> allBooks = bookService.getAllBooks();
        //request域赋值
        request.setAttribute("books", allBooks);
        //请求转发到图书管理页面
        request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        String pageNo = request.getParameter("pageNo");
        //调用service
        Page<Book> booksByPage = bookService.getBooksByPage(pageNo);
        //request域赋值
        request.setAttribute("page", booksByPage);
        //请求转发到图书管理页面
        request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request, response);
    }

    /**
     * 新增图书
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
//    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        //取值
//        String title = new String(request.getParameter("title").getBytes("iso-8859-1"), "utf-8");
//        String author = new String(request.getParameter("author").getBytes("iso-8859-1"), "utf-8");
//        String price = request.getParameter("price");
//        String sales = request.getParameter("sales");
//        String stock = request.getParameter("stock");
//        //调用service
//        bookService.addBook(new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock), null));
//        getAllBooks(request, response);
//        //response.sendRedirect();
//
//    }
    protected void delBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        String id = request.getParameter("bookId");
        //调用service
        bookService.delBookById(id);
        getBooksByPage(request, response);
        //response.sendRedirect();

    }

    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //取值
        String id = request.getParameter("bookId");
        //调用service查到Book
        Book bookById = bookService.getBookById(id);
        //request域赋值
        request.setAttribute("book", bookById);
        //请求转发到图书管理页面
        request.getRequestDispatcher("pages/manager/book_edit.jsp").forward(request, response);

    }

    protected void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //取值
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        if (id == null || "".equals(id)) {//通过id判断添加还是修改
            bookService.addBook(new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock), null));

        } else {
            bookService.updateBook(new Book(Integer.parseInt(id), title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock), null));
        }
        //跳转到图书管理页面
        getBooksByPage(request, response);
    }


}
