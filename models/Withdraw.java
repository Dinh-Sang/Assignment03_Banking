package vn.funix.fx21670.java.asm03.models;

public interface Withdraw {
    // Rút tiền
    boolean withdraw(double amount);
    boolean isAccepted(double amount);      // điều kiện rút tiền
}
