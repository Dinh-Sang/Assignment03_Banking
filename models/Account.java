package vn.funix.fx21670.java.asm03.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> accountTransactions;


    private String accountType;

    public Account(String accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.accountTransactions = new ArrayList<>();
    }

    // Lấy danh sách thông tin
    public List<Transaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void addAccountTransactions(Transaction transaction) {
        transaction.setTime(LocalDateTime.now());
        accountTransactions.add(transaction);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Kiểm điều kiện để trở thành Premium
    public boolean isPremiumAccount() {
        return balance >= 10_000_000;
    }

    @Override
    public String toString() {
        return String.format("%10s | %8s | %,18.0fđ", getAccountNumber(), getAccountType(), getBalance());
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


}
