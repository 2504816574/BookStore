package servlet;

import bean.Book;
import bean.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther Ashen One
 * @Date 2020/11/24
 */
@WebServlet(name = "BookClientServlet", urlPatterns = "/BookClientServlet")
public class BookClientServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 客户端分页查询
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
        //存放到域中
        request.setAttribute("page", booksByPage);
        //跳转到book_client.jsp
        request.getRequestDispatcher("pages/client/book_client.jsp").forward(request, response);

    }

    /**
     * 客户端带价格区间分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBooksByPageAndPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        String pageNo = request.getParameter("pageNo");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        //调用service
        Page<Book> booksByPage = bookService.getBooksByPageAndPrice(pageNo, minPrice, maxPrice);
        //存放到域中
        request.setAttribute("page", booksByPage);
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        //跳转到book_client.jsp
        request.getRequestDispatcher("pages/client/book_client.jsp").forward(request, response);

    }
}
