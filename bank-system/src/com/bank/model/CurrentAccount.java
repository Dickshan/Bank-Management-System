package com.bank.model;

public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String ownerName,
                          double initialBalance, double overdraftLimit) {
        super(accountNumber, ownerName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

 
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > getBalance() + overdraftLimit) {
            throw new IllegalStateException(
                String.format("Exceeds overdraft limit. Max you can withdraw: ₹%.2f",
                              getBalance() + overdraftLimit)
            );
        }
        setBalance(getBalance() - amount);
        System.out.printf("✅ Withdrawn ₹%.2f | New Balance: ₹%.2f%n", amount, getBalance());

        if (getBalance() < 0) {
            System.out.printf("⚠️  Account is in overdraft by ₹%.2f%n", Math.abs(getBalance()));
        }
    }

    @Override
    public String getAccountType() {
        return String.format("Current Account (Overdraft limit: ₹%.0f)", overdraftLimit);
    }
}
