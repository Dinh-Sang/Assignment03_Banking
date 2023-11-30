package vn.funix.fx21670.java.asm03.models;

import java.time.LocalDateTime;

// Tài khoản vay
public class LoanAccount extends Account implements ReportService, Withdraw {
    private final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private final double LOAN_ACCOUNT_MAX_BALANCE = 10_000_000;

    public LoanAccount(String accountNumber, String accountType,  double balance) {
        super(accountNumber, accountType,  balance);

    }

    @Override
    public void log(double amount) {
        LocalDateTime time = LocalDateTime.now();
        double transactionFee = isPremiumAccount() ? (LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE * amount) : (LOAN_ACCOUNT_WITHDRAW_FEE * amount);

        System.out.println("+----------+--------------------------+");
        System.out.printf("%30s%n", "BIÊN LAI GIAO DỊCH LOAN");
        System.out.printf("NGÀY G/D: %28s%n", Utils.formatTime(time));
        System.out.printf("ATM ID:  %29s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SỐ TK: %31s%n", getAccountNumber());
        System.out.printf("SỐ TIỀN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHÍ + VAT: %27s%n", transactionFee);
    }

    @Override
    public boolean withdraw(double amount) {
        if(isAccepted(amount)) {
            // Phí giao dịch của tài khoản pre & nor
            double transactionFee;
            if(isPremiumAccount()) {
                transactionFee = amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
            } else {
                transactionFee = amount * LOAN_ACCOUNT_WITHDRAW_FEE;
            }

            if(amount > getBalance()) {
                System.out.println("Số dư trong tài khoản không đủ!");
                return false;

            } else if(getBalance() - amount < 50_000){
                System.out.println("Số dư tài khoản sau khi rút không được ít hơn 50.000đ.");
                return false;

            } else {
                setBalance(getBalance() - (transactionFee + amount));
                return true;
            }
        } else {
            System.out.println("Tài khoản không đủ điều kiện để rút tiền");
            return false;
        }
    }

    @Override
    public boolean isAccepted(double amount) {
        if(amount <= LOAN_ACCOUNT_MAX_BALANCE) {
            return true;
        } else {
            System.out.println("Hạn mức không được vượt quá giới hạn 10.000.000đ.");
            return false;
        }
    }
}
