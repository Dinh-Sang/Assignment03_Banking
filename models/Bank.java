package vn.funix.fx21670.java.asm03.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private final String id;
    private final List<DigitalCustomer> customers;

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<DigitalCustomer> getCustomers() {
        return customers;
    }

    // Thêm mới khách hàng
    public void addCustomer(DigitalCustomer newCustomer) {
        if(isCustomerIdExisted(newCustomer.getCustomerId()) == null) {
            customers.add(newCustomer);
        }
    }

    // Thêm tài khoản khách hàng
    public void addAccount(String customerId, Account account) {
        for(DigitalCustomer customer : customers) {
            if(customer.getCustomerId().equals(customerId)) {
                customer.addAccount(account);
            }
        }
    }

    // Kiểm tra khách hàng đã tồn tại chưa
    public DigitalCustomer isCustomerIdExisted(String customerId) {
        for(DigitalCustomer customer : customers) {
            if(customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }

        return null;
    }
}
