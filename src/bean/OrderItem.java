package bean;

/**
 * @Auther Ashen One
 * @Date 2020/12/8
 */
public class OrderItem {


    private Integer id;            // 订单项id
    private int count;                // 买了多少本图书
    private double amount;            // 买了count本图书花费的钱数
    private String title;            // 所买图书的书名
    private String author;            // 图书的作者
    private double price;            // 图书的价格
    private String imgPath;        // 图书封面
    private String orderId;        // 订单项所属的订单


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderItem(Integer id, int count, double amount, String title, String author, double price, String imgPath,
                     String orderId) {
        super();
        this.id = id;
        this.count = count;
        this.amount = amount;
        this.title = title;
        this.author = author;
        this.price = price;
        this.imgPath = imgPath;
        this.orderId = orderId;
    }

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem [id=" + id + ", count=" + count + ", amount=" + amount + ", title=" + title + ", author="
                + author + ", price=" + price + ", imgPath=" + imgPath + ", orderId=" + orderId + "]";
    }


}
