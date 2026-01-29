package bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Transaction {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final TransactionType type;
    private final BigDecimal amount;
    private final String note;

    public Transaction(TransactionType type, BigDecimal amount, String note) {
        this.type = type;
        this.amount = amount;
        this.note = note;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public TransactionType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public String getNote() { return note; }

    @Override
    public String toString() {
        return timestamp + " | " + type + " | $" + amount.toPlainString() + " | " + note;
    }
}
