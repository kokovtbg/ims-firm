package bg.sirma.ims.http;

import bg.sirma.ims.model.payment.PayPalAccount;

import java.math.BigDecimal;

public class BankAccount {
    private String cardNumber;
    private String pin;
    private BigDecimal balance;

    public BankAccount(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = BigDecimal.ZERO;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void increaseBalance(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void decreaseBalance(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }
}
