package bank;

import java.math.BigDecimal;

public final class CheckingAccount extends Account {
    public CheckingAccount(String accountId, String ownerName, BigDecimal openingDeposit) {
        super(accountId, ownerName, openingDeposit);
    }

    @Override
    protected boolean canWithdraw(BigDecimal amount) {
        return balance.compareTo(amount) >= 0; // no overdraft in this simple version
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }
}
