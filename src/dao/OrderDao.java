package dao;

import bean.Order;

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
}
