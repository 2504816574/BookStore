package servlet;

import bean.Book;
import bean.Cart;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther Ashen One
 * @Date 2020/12/2
 */
@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //获取bookId
        String bookId = request.getParameter("bookId");
        //通过bookId获取Book(BookService)
        Book book = bookService.getBookById(bookId);
        //调用Cart中的addBookToCart
        //Cart存放到session中
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            //存放到Session域中
            session.setAttribute("cart", cart);
        }
        cart.addBookToCart(book);
        //验证库存
        Integer stock = book.getStock();
        int count = cart.getMap().get(bookId + "").getCount();
        if (count > stock) {
            //库存不足
            session.setAttribute("msg", "库存不足，只剩" + stock + "件商品!");
            //将购买商品的数量，设置为最大库存。
            cart.getMap().get(book.getId() + "").setCount(stock);
        } else {
            //将titile存放到session域中
            session.setAttribute("title", book.getTitle());
        }
        //book的title放到session中
        session.setAttribute("title", book.getTitle());
        //获取Referer：跳转
        String url = request.getHeader("Referer");
        //跳转
        response.sendRedirect(url);
    }

    /**
     * 更新购物车数量
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCartItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //取bookId,count值
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        //调用Cart
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.updateCartItemCount(bookId, count);
        }
        //跳转
        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
    }

    /**
     * 删除购物项
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //取bookid值
        String bookId = request.getParameter("bookId");
        //调用Cart
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart != null) {
            cart.delCartItem(bookId);
        }
        //跳转
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart != null) {
            cart.emptyCart();
        }
        //跳转（1.首页，2.购物车）
//		response.sendRedirect(request.getContextPath()+"/index.jsp");
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }

}
