package servlet;

import bean.Cart;
import bean.User;
import dao.OrderDao;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        //调用service
        String orderId = orderService.createOrder(cart, user);
        session.setAttribute("orderId", orderId);
        //跳转
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");

    }

}
