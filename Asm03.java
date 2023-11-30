package vn.funix.fx21670.java.asm03;


import vn.funix.fx21670.java.asm03.models.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Asm03 {

    // Info customer
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final DigitalBank activeBank = new DigitalBank();
    public static final String CUSTOMER_ID = "075123456789";
    public static final String CUSTOMER_NAME = "SANG";

    // Bank version and author
    private static final String AUTHOR = "FX21670";
    private static final String VERSION = "V3.0.0";

    private static final Bank bank = new Bank();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("+----------+--------------------------+");
        System.out.println("| NGÂN HÀNG SỐ |" + AUTHOR + "-" + VERSION + "        |");
        System.out.println("+----------+--------------------------+");
        // Thêm tài khoản khách hàng
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);

        boolean function = true;
        while (function) {
            System.out.println("1. Thông tin khách hàng");
            System.out.println("2. Thêm tài khoản ATM");
            System.out.println("3. Thêm tài khoản tín dụng");
            System.out.println("4. Rút tiền");
            System.out.println("5. Lịch sử giao dịch");
            System.out.println("0. Thoát");
            System.out.println("+----------+--------------------------+");
            System.out.print("Chức năng: ");

            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        viewInfoCustomer();
                        break;
                    case 2:
                        addATM();
                        break;
                    case 3:
                        addCreditAccount();
                        break;
                    case 4:
                        withdrawMoney();
                        break;
                    case 5:
                        transactionHistory();
                        break;
                    case 0:
                        System.out.println("Bạn đã thoát khỏi chương trình");
                        function = false;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng lựa chọn lại");
            }
            System.out.println("+----------+--------------------------+");
        }
    }

    // Thông tin khách hàng (1)
    private static void viewInfoCustomer() {
        DigitalCustomer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        }
    }

    // Thêm tài khoản ATM (2)
    private static void addATM() {
        sc.nextLine();
        DigitalCustomer customerATM = activeBank.isCustomerIdExisted(CUSTOMER_ID);
        String accountNumber;
        while (true) {
            System.out.print("Nhập mã STK gồm 6 chữ số: ");
            accountNumber = sc.nextLine();
            if (accountNumber.length() != 6) {
                System.out.println("Vui lòng nhập STK gồm 6 chữ số!");
            } else if (customerATM.checkedCustomer(accountNumber) != null) {
                System.out.println("STK đã tồn tại. Vui lòng nhập STK khác!");
            } else {
                break;
            }
        }

        double balance;
        while (true) {
            System.out.print("Nhập số dư: ");
            balance = sc.nextDouble();
            if (balance >= 50_000) {
                break;
            } else {
                System.out.println("Số dư tài khoản không được nhỏ hơn 50_000VNĐ");
            }
        }

        SavingsAccount newAccount = new SavingsAccount(accountNumber, "SAVINGS", balance);
        activeBank.addAccount(CUSTOMER_ID, newAccount);

        Account account = customerATM.checkedCustomer(accountNumber);
        account.addAccountTransactions(new Transaction(accountNumber, balance));
    }

    // Thêm tài khoản tín dụng (3)
    private static void addCreditAccount() {
        sc.nextLine();
        DigitalCustomer customerATM = activeBank.isCustomerIdExisted(CUSTOMER_ID);

        String accountNumber;
        while (true) {
            System.out.print("Nhập mã STK gồm 6 chữ số: ");
            accountNumber = sc.nextLine();
            if (accountNumber.length() != 6) {
                System.out.println("Vui lòng nhập STK gồm 6 chữ số!");
            } else if (customerATM.checkedCustomer(accountNumber) != null) {
                System.out.println("STK đã tồn tại. Vui lòng nhập STK khác!");
            } else {
                break;
            }
        }

        LoanAccount newAccount = new LoanAccount(accountNumber, "LOAN", 10000000.0);
        activeBank.addAccount(CUSTOMER_ID, newAccount);

        Account account = customerATM.checkedCustomer(accountNumber);
        account.addAccountTransactions(new Transaction(accountNumber, 10000000.0));
    }

    //  Rút tiền (4)
    private static void withdrawMoney() {
        sc.nextLine();
        DigitalCustomer customerATM = activeBank.isCustomerIdExisted(CUSTOMER_ID);

        String accountNumber;
        boolean exist = false;
        System.out.print("Nhập mã STK gồm 6 chữ số: ");
        do {
            accountNumber = sc.nextLine();
            if (accountNumber.equals("0")) {
                return;
            }

            if (accountNumber.length() != 6) {
                System.out.println("Mã STK gồm 6 chữ số. Vui lòng nhập lại.");
                System.out.print("Nhập mã STK gồm 6 chữ số hoặc nhập 0 để thoát: ");
            } else if (customerATM.checkedCustomer(accountNumber) == null) {
                System.out.println("Mã STK không tồn tại. Vui lòng nhập lại.");
                System.out.print("Nhập mã STK gồm 6 chữ số hoặc nhập 0 để thoát: ");
            } else {
                exist = true;
            }
        } while (!exist);
        if (!exist) {
            System.out.println();
        }

        System.out.print("Nhập số tiền cần rút: ");
        double amount = sc.nextDouble();
        activeBank.withdraw(CUSTOMER_ID, accountNumber, amount);
    }

    // Lịch sử giao dịch (5)
    private static void transactionHistory() {
        DigitalCustomer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            List<Account> accounts = customer.getAccounts();
            customer.displayInformation();
            System.out.println();
            System.out.printf("%12s |", "Account");
            System.out.printf("%12s | ", "Amount");
            System.out.printf("%20s | ", "Time");
            System.out.printf("%20s%n", "Transaction ID");
            boolean isValid = false;

            for (Account account : accounts) {
                List<Transaction> transactions = account.getAccountTransactions();
                for (Transaction transaction : transactions) {
                    if (!transactions.isEmpty()) {
                        isValid = true;
                        System.out.printf("[GD] %7s | ", transaction.getAccountNumber());
                        System.out.printf("%-10sđ | ", transaction.getAmount());
                        System.out.printf("%20s | ", Utils.formatTime(transaction.getTime()));
                        System.out.printf("%20s%n", transaction.getId());
                    }
                }
            }

            if (!isValid) {
                System.out.println("Không có lịch sử giao dịch nào");
            }
        }
    }
}
