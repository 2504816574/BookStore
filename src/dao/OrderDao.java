package dao;

import bean.Order;

import java.util.List;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public interface OrderDao {
    /**
     *添加订单
     * @param order
     */
    void insertOrder(Order order);

    /**
     * 通过UserId查订单
     * @param id
     * @return
     */
    List<Order> getOrdersByUserId(int id);
}
