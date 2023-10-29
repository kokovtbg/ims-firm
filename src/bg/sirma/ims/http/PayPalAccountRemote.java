package bg.sirma.ims.http;

import java.math.BigDecimal;

public class PayPalAccountRemote {
    private String username;
    private String password;
    private BigDecimal balance;

    public PayPalAccountRemote(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = BigDecimal.ZERO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
