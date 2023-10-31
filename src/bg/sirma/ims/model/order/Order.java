package bg.sirma.ims.model.order;

import bg.sirma.ims.model.payment.PaymentMethod;
import bg.sirma.ims.temp.Cart;

public class Order {
    private long id;
    private final PaymentMethod payment;
    private final Cart cart;

    public Order(PaymentMethod payment, Cart cart) {
        this.payment = payment;
        this.cart = cart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Order{" +
                "payment=" + payment +
                "cart=" + cart +
                '}';
    }
}
