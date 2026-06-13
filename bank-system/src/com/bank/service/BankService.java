package com.bank.service;

import com.bank.model.Account;
import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BankService {


    private Map<String, Account> accounts = new HashMap<>();


    private Map<String, List<Transaction>> history = new HashMap<>();

    private int accountCounter = 1001;


    public String createSavingsAccount(String ownerName, double initialBalance,
                                       double interestRate) {
        String accNo = "SAV" + accountCounter++;
        SavingsAccount acc = new SavingsAccount(accNo, ownerName, initialBalance, interestRate);
        accounts.put(accNo, acc);
        history.put(accNo, new ArrayList<>());
        logTransaction(accNo, Transaction.Type.DEPOSIT, initialBalance, "Account opened");
        System.out.println("✅ Savings account created: " + accNo);
        return accNo;
    }


    public String createCurrentAccount(String ownerName, double initialBalance,
                                       double overdraftLimit) {
        String accNo = "CUR" + accountCounter++;
        CurrentAccount acc = new CurrentAccount(accNo, ownerName, initialBalance, overdraftLimit);
        accounts.put(accNo, acc);
        history.put(accNo, new ArrayList<>());
        logTransaction(accNo, Transaction.Type.DEPOSIT, initialBalance, "Account opened");
        System.out.println("✅ Current account created: " + accNo);
        return accNo;
    }


    public void deposit(String accountNumber, double amount) {
        Account acc = getAccount(accountNumber);
        acc.deposit(amount);
        logTransaction(accountNumber, Transaction.Type.DEPOSIT, amount, "Deposit");
    }


    public void withdraw(String accountNumber, double amount) {
        Account acc = getAccount(accountNumber);
        acc.withdraw(amount);
        logTransaction(accountNumber, Transaction.Type.WITHDRAWAL, amount, "Withdrawal");
    }


    public void transfer(String fromAccount, String toAccount, double amount) {
        Account from = getAccount(fromAccount);
        Account to   = getAccount(toAccount);

        from.withdraw(amount);
        to.deposit(amount);

        logTransaction(fromAccount, Transaction.Type.TRANSFER, amount,
                       "Transfer to " + toAccount);
        logTransaction(toAccount, Transaction.Type.TRANSFER, amount,
                       "Transfer from " + fromAccount);

        System.out.printf("🔄 Transferred ₹%.2f from %s to %s%n", amount, fromAccount, toAccount);
    }

    public void applyInterest(String accountNumber) {
        Account acc = getAccount(accountNumber);
        if (acc instanceof SavingsAccount savings) {
            double before = savings.getBalance();
            savings.applyInterest();
            double interest = savings.getBalance() - before;
            logTransaction(accountNumber, Transaction.Type.DEPOSIT, interest, "Interest applied");
        } else {
            System.out.println("⚠️  Interest only applies to Savings Accounts.");
        }
    }


    public void printAccountDetails(String accountNumber) {
        System.out.println(getAccount(accountNumber));
    }

    public void printHistory(String accountNumber) {
        getAccount(accountNumber); // Validate account exists
        List<Transaction> txns = history.get(accountNumber);
        System.out.println("\n📋 Transaction History — " + accountNumber);
        System.out.println("─".repeat(70));
        if (txns.isEmpty()) {
            System.out.println("  No transactions yet.");
        } else {
            txns.forEach(System.out::println);
        }
        System.out.println("─".repeat(70));
    }


    public void printAllAccounts() {
        System.out.println("\n🏦 All Accounts");
        System.out.println("─".repeat(40));
        if (accounts.isEmpty()) {
            System.out.println("  No accounts found.");
        } else {
            accounts.values().forEach(System.out::println);
        }
    }

   

    private Account getAccount(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        if (acc == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return acc;
    }

    private void logTransaction(String accountNumber, Transaction.Type type,
                                 double amount, String description) {
        double balance = accounts.get(accountNumber).getBalance();
        history.get(accountNumber).add(new Transaction(type, amount, balance, description));
    }
}
