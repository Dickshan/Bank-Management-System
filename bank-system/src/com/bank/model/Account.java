package com.bank.model;


public abstract class Account {

    private String accountNumber;
    private String ownerName;
    private double balance;


    public Account(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName     = ownerName;
        this.balance       = initialBalance;
    }


    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.printf("✅ Deposited ₹%.2f | New Balance: ₹%.2f%n", amount, balance);
    }


    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds.");
        }
        balance -= amount;
        System.out.printf("✅ Withdrawn ₹%.2f | New Balance: ₹%.2f%n", amount, balance);
    }

    public abstract String getAccountType();

    // ─── Getters ─────────────────────────────────────────────────
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName()     { return ownerName; }
    public double getBalance()       { return balance; }

    // Used internally by subclasses (e.g., overdraft logic)
    protected void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format(
            "┌─────────────────────────────┐%n" +
            "  Account : %s%n" +
            "  Type    : %s%n" +
            "  Owner   : %s%n" +
            "  Balance : ₹%.2f%n" +
            "└─────────────────────────────┘",
            accountNumber, getAccountType(), ownerName, balance
        );
    }
}
