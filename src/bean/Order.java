package bean;

import java.util.Date;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */

public class Order {

    /**
     * createOrder
     * 1. 生成订单
     * 2. 生成订单详情
     * 3. 更改相应book的库存和销量
     * 4. 清空购物车
     */
    private String id;                // 订单号
    private Date orderTime;        // 生成订单的时间
    private int totalCount;        // 商品总数量
    private double totalAmount;    // 商品总金额
    private int state;                // 订单状态 0：未发货 1：已发货 2：交易完成
    private int userId;            // 订单所属的用户


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Order(String id, Date orderTime, int totalCount, double totalAmount, int state, int userId) {
        super();
        this.id = id;
        this.orderTime = orderTime;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.state = state;
        this.userId = userId;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderTime=" + orderTime + ", totalCount=" + totalCount + ", totalAmount="
                + totalAmount + ", state=" + state + ", userId=" + userId + "]";
    }


}
