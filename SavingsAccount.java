package bank;

import java.math.BigDecimal;

public final class SavingsAccount extends Account {
    private final BigDecimal annualInterestRate; // e.g. 0.03 for 3%

    public SavingsAccount(String accountId, String ownerName, BigDecimal openingDeposit, BigDecimal annualInterestRate) {
        super(accountId, ownerName, openingDeposit);
        if (annualInterestRate == null || annualInterestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Interest rate must be >= 0.");
        }
        this.annualInterestRate = annualInterestRate;
    }

    public void applyMonthlyInterest() {
        // monthly = annual / 12
        BigDecimal monthlyRate = annualInterestRate.divide(new BigDecimal("12.0"), 10, BigDecimal.ROUND_HALF_UP);
        BigDecimal interest = balance.multiply(monthlyRate);
        if (interest.compareTo(BigDecimal.ZERO) > 0) {
            deposit(interest, "Monthly interest");
        }
    }

    @Override
    protected boolean canWithdraw(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}
