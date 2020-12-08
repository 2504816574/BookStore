package service.impl;

import bean.*;
import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import service.OrderService;

import java.util.List;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    /**
     * 去结账
     * 1. 生成订单
     * 2. 生成订单详情
     * 3. 更改相应book的库存和销量
     * 4. 清空购物车
     *
     * @param cart
     * @param user
     * @return
     */
    @Override
    public String createOrder(Cart cart, User user) {
        //1. 生成订单
        //orderId=时间戳+userid
        String orderId = System.currentTimeMillis() + "" + user.getId();
        orderDao.insertOrder(new Order(orderId, null, cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId()));
        //获取所有购物项
        List<CartItem> cartItems = cart.getCartItems();
        //遍历购物项，添加到订单详情
        for (CartItem cartItem : cartItems) {
            //2.生成订单详情
            Book book = cartItem.getBook();
            int count = cartItem.getCount();
            orderItemDao.insertOrderItem(new OrderItem(null, count, cartItem.getAmount(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getImgPath(), orderId));
            //3. 更改相应book的库存和销量
            int stock = book.getStock() - count;    //计算最终的库存
            int sales = book.getSales() + count;  //计算最终的销量
            bookDao.updateBook(stock, sales, book.getId());
        }
        //4. 清空购物车
        cart.emptyCart();
        return orderId;

    }
}
