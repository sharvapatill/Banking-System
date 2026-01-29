package bank;

import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {
    private static final AtomicInteger SEQ = new AtomicInteger(1000);

    private IdGenerator() {}

    public static String nextAccountId() {
        return "ACCT-" + SEQ.getAndIncrement();
    }
}
