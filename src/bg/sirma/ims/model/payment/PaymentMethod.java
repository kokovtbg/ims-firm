package bg.sirma.ims.model.payment;

import bg.sirma.ims.model.user.User;

public abstract class PaymentMethod {
    private long id;
    private final User payer;

    public PaymentMethod(User payer) {
        this.payer = payer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPayer() {
        return payer;
    }

    @Override
    public String toString() {
        return "PaymentMethod{}";
    }
}
