package bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther Ashen One
 * @Date 2020/12/2
 */
public class CartItem implements Serializable {

    /**
     *
     */
    private Book book;
    private int count;
    private double amount;    //amount = book.price*count


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 计算购物项金额
     *
     * @return
     */
    public double getAmount() {
        BigDecimal price = new BigDecimal(book.getPrice() + "");
        BigDecimal c = new BigDecimal(count + "");
        return price.multiply(c).doubleValue();
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CartItem(Book book, int count, double amount) {
        super();
        this.book = book;
        this.count = count;
        this.amount = amount;
    }

    public CartItem() {
        super();
    }

    @Override
    public String toString() {
        return "CartItem [book=" + book + ", count=" + count + ", amount=" + amount + "]";
    }


}
