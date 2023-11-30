package vn.funix.fx21670.java.asm03.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final String accountNumber;
    private double amount;
    private LocalDateTime time;
    private boolean status;

    public Transaction (String accountNumber, double amount) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
