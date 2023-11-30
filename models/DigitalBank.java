package vn.funix.fx21670.java.asm03.models;


import java.util.List;
import java.util.ListResourceBundle;

public class DigitalBank extends Bank {
    public DigitalBank() {

    }

    // Check CCCD của khách hàng
    public DigitalCustomer getCustomerById(String customerId) {
        List<DigitalCustomer> digitalCustomers = getCustomers();
        for (DigitalCustomer digitalCustomer : digitalCustomers) {
            if (digitalCustomer.getCustomerId().equals(customerId)) {
                return digitalCustomer;
            }
        }
        return null;
    }

    // Thêm khách hàng mới
    public void addCustomer(String customerId, String name) {
        DigitalCustomer newCustomer = new DigitalCustomer(name, customerId);
        List<DigitalCustomer> customers = getCustomers();
        customers.add(newCustomer);
    }

    // Thực hiện giao dịch rút tiền
    public void withdraw(String customerId, String accountNumber, double amount) {
        DigitalCustomer digitalCustomer = isCustomerIdExisted(customerId);
        if (digitalCustomer == null) {
            System.out.println("Khách hàng không có trong hệ thống.");
        } else {
            digitalCustomer.withdraw(accountNumber, amount);
        }
    }
}
