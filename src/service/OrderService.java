package service;

import bean.Cart;
import bean.Order;
import bean.User;

import java.util.List;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public interface OrderService {
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
    String createOrder(Cart cart, User user);

    /**
     * 通过UserId查订单
     * @param id
     * @return
     */
    List<Order> getOrdersByUserId(int id);

}
