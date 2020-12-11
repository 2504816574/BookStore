package servlet;

import bean.Cart;
import bean.Order;
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
import java.util.List;

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
    protected void getOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("user");
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        request.setAttribute("orders",orders);
        //请求转发到订单管理页面
        request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request, response);


    }

}
