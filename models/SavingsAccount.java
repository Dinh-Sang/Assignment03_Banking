package vn.funix.fx21670.java.asm03.models;


import java.time.LocalDateTime;

// Tài khoản ATM
public class SavingsAccount extends Account implements ReportService, Withdraw {
    private static final double  SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000;

    public SavingsAccount(String accountNumber, String accountType, double balance) {
        super(accountNumber, accountType,  balance);
    }

    @Override
    public void log(double amount) {
        LocalDateTime time = LocalDateTime.now();

        System.out.println("+----------+--------------------------+");
        System.out.printf("%30s%n", "BIÊN LAI GIAO DỊCH SAVINGS");
        System.out.printf("NGÀY G/D: %28s%n", Utils.formatTime(time));
        System.out.printf("ATM ID:  %29s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SỐ TK: %31s%n", getAccountNumber());
        System.out.printf("SỐ TIỀN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHÍ + VAT: %27s%n", "0đ");
    }

    @Override
    public boolean withdraw(double amount) {
        // Nếu tài khoản là Premium thì ko giới hạn, nếu là thường thì ngược lại
        if(!isPremiumAccount()) {
            if(!isAccepted(amount)) {
                return false;
            }
        }

        if(amount > getBalance()) {
            System.out.println("Số dư trong tài khoản không đủ!");
            return false;

        } else if(amount % 10_000 != 0) {
            System.out.println("Số tiền rút phải chia hết cho 10.000đ.");
            return false;

        } else if(getBalance() - amount < 50_000){
            System.out.println("Số dư tài khoản sau khi rút không được ít hơn 50.000đ.");
            return false;

        } else {
            setBalance(getBalance() - amount);
            return true;
        }
    }

    @Override
    public boolean isAccepted(double amount) {
        if(amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW) {
            return true;
        } else {
            System.out.println("Tài khoản rút không được vượt quá 5.000.000đ.");
            return false;
        }
    }
}
