package vn.funix.fx21670.java.asm03.models;

import java.time.LocalDateTime;
import java.util.List;

public class DigitalCustomer extends Customer {
    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    // rut tien
    public void withdraw(String accountNumber, double amount) {
        Account account = checkedCustomer(accountNumber);
        if(account == null) {
            System.out.println("STK không có trong hệ thống");
        } else {
            if(account instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                boolean isValid = savingsAccount.withdraw(amount);

                if(isValid) {
                    savingsAccount.log(amount);
                } else {
                    System.out.println("Rút tiền không thành công.");
                }
                account.addAccountTransactions(new Transaction(accountNumber, -amount));
            } else if(account instanceof LoanAccount) {
                LoanAccount loanAccount = (LoanAccount) account;
                boolean isValid = loanAccount.withdraw(amount);

                if(isValid) {
                    loanAccount.log(amount);
                } else {
                    System.out.println("Rút tiền không thành công.");
                }
                account.addAccountTransactions(new Transaction(accountNumber, -amount));
            }
        }
    }

    @Override
    public void displayInformation() {
        super.displayInformation();
    }
}
