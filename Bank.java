package bank;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    public CheckingAccount createChecking(String owner, BigDecimal openingDeposit) {
        String id = IdGenerator.nextAccountId();
        CheckingAccount acct = new CheckingAccount(id, owner, openingDeposit);
        accounts.put(id, acct);
        return acct;
    }

    public SavingsAccount createSavings(String owner, BigDecimal openingDeposit, BigDecimal annualInterestRate) {
        String id = IdGenerator.nextAccountId();
        SavingsAccount acct = new SavingsAccount(id, owner, openingDeposit, annualInterestRate);
        accounts.put(id, acct);
        return acct;
    }

    public Account getAccount(String id) {
        Account acct = accounts.get(id);
        if (acct == null) throw new IllegalArgumentException("Account not found: " + id);
        return acct;
    }

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }
}
