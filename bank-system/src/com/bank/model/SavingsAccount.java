package com.bank.model;


public class SavingsAccount extends Account {

    private double interestRate; // e.g., 0.04 = 4% per year

    public SavingsAccount(String accountNumber, String ownerName,
                          double initialBalance, double interestRate) {
        super(accountNumber, ownerName, initialBalance);
        this.interestRate = interestRate;
    }


    public void applyInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.printf("💰 Interest applied at %.0f%% → ₹%.2f added%n",
                          interestRate * 100, interest);
    }

    @Override
    public String getAccountType() {
        return "Savings Account (" + (int)(interestRate * 100) + "% interest)";
    }
}
