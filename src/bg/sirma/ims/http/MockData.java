package bg.sirma.ims.http;

import java.math.BigDecimal;

public class MockData {
    private final Bank bank;
    private final PayPal payPal;

    public MockData() {
        this.bank = new Bank();
        this.payPal = new PayPal();
    }

    public MockData mock() {
        BankAccount bankAccount = new BankAccount("1234123412341234", "1234");
        bankAccount.increaseBalance(BigDecimal.valueOf(1000));
        bank.addAccount(bankAccount);
        PayPalAccountRemote payPalAccountRemote = new PayPalAccountRemote("ivan", "1234");
        payPalAccountRemote.increaseBalance(BigDecimal.valueOf(1000));
        payPal.addAccount(payPalAccountRemote);
        return this;
    }

    public Bank getBank() {
        return bank;
    }

    public PayPal getPayPal() {
        return payPal;
    }
}
