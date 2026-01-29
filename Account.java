package bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private final String accountId;
    private final String ownerName;
    protected BigDecimal balance;
    private final List<Transaction> history = new ArrayList<>();

    protected Account(String accountId, String ownerName, BigDecimal openingDeposit) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        this.accountId = accountId;
        this.ownerName = ownerName.trim();
        this.balance = money(openingDeposit);

        if (this.balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening deposit cannot be negative.");
        }
        if (this.balance.compareTo(BigDecimal.ZERO) > 0) {
            history.add(new Transaction(TransactionType.DEPOSIT, this.balance, "Opening deposit"));
        }
    }

    public String getAccountId() { return accountId; }
    public String getOwnerName() { return ownerName; }
    public BigDecimal getBalance() { return balance; }
    public List<Transaction> getHistory() { return List.copyOf(history); }

    public void deposit(BigDecimal amount, String note) {
        BigDecimal a = money(amount);
        if (a.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit must be > 0.");
        }
        balance = balance.add(a);
        history.add(new Transaction(TransactionType.DEPOSIT, a, noteOrDefault(note, "Deposit")));
    }

    public void withdraw(BigDecimal amount, String note) {
        BigDecimal a = money(amount);
        if (a.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw must be > 0.");
        }
        if (!canWithdraw(a)) {
            throw new IllegalArgumentException("Insufficient funds or withdrawal not allowed.");
        }
        balance = balance.subtract(a);
        history.add(new Transaction(TransactionType.WITHDRAW, a, noteOrDefault(note, "Withdraw")));
    }

    public void transferTo(Account other, BigDecimal amount) {
        if (other == null) throw new IllegalArgumentException("Target account cannot be null.");
        BigDecimal a = money(amount);
        if (a.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Transfer must be > 0.");
        if (this == other) throw new IllegalArgumentException("Cannot transfer to same account.");

        // Withdraw first to ensure rules are applied
        this.withdraw(a, "Transfer to " + other.accountId);
        other.deposit(a, "Transfer from " + this.accountId);

        history.add(new Transaction(TransactionType.TRANSFER_OUT, a, "To " + other.accountId));
        other.history.add(new Transaction(TransactionType.TRANSFER_IN, a, "From " + this.accountId));
    }

    protected abstract boolean canWithdraw(BigDecimal amount);

    protected static BigDecimal money(BigDecimal x) {
        if (x == null) throw new IllegalArgumentException("Amount cannot be null.");
        return x.setScale(2, RoundingMode.HALF_UP);
    }

    private static String noteOrDefault(String note, String fallback) {
        if (note == null || note.trim().isEmpty()) return fallback;
        return note.trim();
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | $%s",
                accountId, ownerName, getAccountType(), balance.toPlainString());
    }

    public abstract String getAccountType();
}
