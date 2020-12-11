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
        //OrderItem的二维参数
        Object[][] orderItemParams = new Object[cartItems.size()][];
        Object[][] bookParams = new Object[cartItems.size()][];
        //遍历购物项，添加到订单详情
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            //2.生成订单详情
            Book book = cartItem.getBook();
            int count = cartItem.getCount();
            // orderItemDao.insertOrderItem(new OrderItem(null, count, cartItem.getAmount(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getImgPath(), orderId));
            //orderItemParams第二维赋值
            //`count`,amount,title,author,price,img_path,order_id
            orderItemParams[i] = new Object[]{count, cartItem.getAmount(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getImgPath(), orderId};

            //3. 更改相应book的库存和销量
            int stock = book.getStock() - count;    //计算最终的库存
            int sales = book.getSales() + count;  //计算最终的销量
            bookParams[i] = new Object[]{sales, stock, book.getId()};

            // bookDao.updateBook(stock, sales, book.getId());
        }

        orderItemDao.insertOrderItem(orderItemParams);
        bookDao.updateBook(bookParams);

        //4. 清空购物车
        cart.emptyCart();
        return orderId;

    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return orderDao.getOrdersByUserId(id);

    }
}
