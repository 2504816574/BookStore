import bean.OrderItem;
import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public class OrderItemDaoImplTest {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();

    @Test
    public void insertOrderItem() {
        orderItemDao.insertOrderItem(new OrderItem(null, 1, 150, "testt", "testa", 150, "testt", "test001"));
    }
}