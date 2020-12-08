package dao;

import bean.OrderItem;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public interface OrderItemDao {
    /**
     * 添加订单项
     * @param orderItem
     */
    void insertOrderItem(OrderItem orderItem);
}
