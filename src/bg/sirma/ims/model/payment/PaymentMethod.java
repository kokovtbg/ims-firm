package bg.sirma.ims.model.payment;

import bg.sirma.ims.model.user.User;

public abstract class PaymentMethod {
    private long id;
    private final User payer;
    private String type;

    public PaymentMethod(User payer) {
        this.payer = payer;
        this.type = "PaymentMethod";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PaymentMethod{}";
    }
}
