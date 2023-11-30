package vn.funix.fx21670.java.asm03.models;

import vn.funix.fx21670.java.asm01.Validator;

public class User {
    private String name;
    private String customerId;

    public User(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        if (isValid(customerId)) {
            this.customerId = customerId;
        } else {
            throw new IllegalArgumentException("Số CCCD không hợp lệ,");
        }
    }

    // Tính hợp lệ CCCD
    private boolean isValid(String customerId) {
        if (customerId.length() != 12) {
            return false;
        }

        for (int i = 0; i < customerId.length(); i++) {
            if (!Character.isDigit(customerId.charAt(i))) {
                return false;
            }
        }

        String provinceCode = customerId.substring(0, 3);
        if (!Validator.validProvinceCode(provinceCode)) {
            return false;
        }

        return true;
    }
}
