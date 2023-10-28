package bg.sirma.ims.model.payment;

import bg.sirma.ims.model.user.User;

public class DebitCardPayment extends PaymentMethod {
    private String cardNumber;

    public DebitCardPayment(User payer, String cardNumber) {
        super(payer);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "DebitCardPayment{" +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
