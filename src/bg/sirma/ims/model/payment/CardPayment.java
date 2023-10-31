package bg.sirma.ims.model.payment;

import bg.sirma.ims.model.user.User;

public class CardPayment extends PaymentMethod {
    private String cardNumber;

    public CardPayment(User payer, String cardNumber) {
        super(payer);
        this.cardNumber = cardNumber;
        setType("CardPayment");
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "id=" + getId() +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
