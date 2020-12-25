import bean.Order;
import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public class OrderDaoImplTest {
    OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void insertOrder() {
        orderDao.insertOrder(new Order("111",null,100,200,1,1200));
    }

    @Test
    public void getOrderByUserId() {
        List<Order> orderByUserId = orderDao.getOrdersByUserId(1);
        System.out.println(Arrays.toString(orderByUserId.toArray()));
    }
}