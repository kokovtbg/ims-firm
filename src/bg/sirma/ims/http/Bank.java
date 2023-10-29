package bg.sirma.ims.http;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Bank {
    private final Set<BankAccount> accounts;

    public Bank() {
        this.accounts = new HashSet<>();
    }

    public Set<BankAccount> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public void addAccount(BankAccount account) {
        this.accounts.add(account);
    }
}
