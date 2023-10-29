package bg.sirma.ims.http;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PayPal {
    private final Set<PayPalAccountRemote> accounts;

    public PayPal() {
        this.accounts = new HashSet<>();
    }

    public Set<PayPalAccountRemote> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public void addAccount(PayPalAccountRemote account) {
        this.accounts.add(account);
    }
}
