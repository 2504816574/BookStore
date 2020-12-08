package dao.impl;

import bean.OrderItem;
import dao.BaseDao;
import dao.OrderItemDao;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public void insertOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_items(`count`,amount,title,author,price,img_path,order_id) VALUES(?,?,?,?,?,?,?)";
        this.update(sql, orderItem.getCount(), orderItem.getAmount(), orderItem.getTitle(), orderItem.getAuthor(), orderItem.getPrice(), orderItem.getImgPath(), orderItem.getOrderId());
    }

}
