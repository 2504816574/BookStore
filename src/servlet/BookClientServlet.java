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
 * @Auther 董升
 * @Date 2020/11/24
 */
@WebServlet(name = "BookClientServlet",urlPatterns = "/BookClientServlet")
public class BookClientServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        String pageNo = request.getParameter("pageNo");
        //调用service
        Page<Book> booksByPage = bookService.getBooksByPage(pageNo);
        //存放到域中
        request.setAttribute("page", booksByPage);
        //跳转到book_client.jsp
        request.getRequestDispatcher("client/book_client.jsp").forward(request, response);

    }
}
