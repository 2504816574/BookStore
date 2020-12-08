package dao.impl;

import bean.Order;
import dao.BaseDao;
import dao.OrderDao;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public void insertOrder(Order order) {
        String sql = "INSERT INTO orders(id,order_time,total_count,total_amount,state,user_id) VALUES(?,?,?,?,?,?)";
        this.update(sql, order.getId(), order.getOrderTime(), order.getTotalCount(), order.getTotalAmount(), order.getState(), order.getUserId());
    }
}
