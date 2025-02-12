package com.example.models;

// inheriting from User
public class Customer extends User {

    private double walletBalance; 

    public Customer(String userId, String name, double walletBalance) {
        super(userId, name);
        this.walletBalance = walletBalance;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public boolean hasSufficientBalance(double amount) {
        return walletBalance >= amount;
    }

    public boolean makePayment(double amount) {
        if (hasSufficientBalance(amount)) {
            walletBalance -= amount;
            System.out.println(String.format("%s has paid $%.2f. Remaining Balance: $%.2f", name, amount, walletBalance));
            return true;
        } else {
            System.out.println(String.format("%s does not have enough balance to pay $%.2f.", name, amount));
            return false;
        }
    }
}
