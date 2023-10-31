package bg.sirma.ims.model.payment;

import bg.sirma.ims.model.user.User;

public class PayPalPayment extends PaymentMethod {
    private PayPalAccount paypalAccount;

    public PayPalPayment(User payer, PayPalAccount paypalAccount) {
        super(payer);
        this.paypalAccount = paypalAccount;
        setType("PayPalPayment");
    }

    public PayPalAccount getPaypalAccount() {
        return paypalAccount;
    }

    public void setPaypalAccount(PayPalAccount paypalAccount) {
        this.paypalAccount = paypalAccount;
    }

    @Override
    public String toString() {
        return "PayPalPayment{" +
                "paypalAccount=" + paypalAccount +
                '}';
    }
}
