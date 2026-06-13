package com.bank;

import com.bank.service.BankService;


public class Main {

    public static void main(String[] args) {

        BankService bank = new BankService();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Java Bank Management System 🏦     ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        
        System.out.println("── Creating Accounts ──────────────────");
        String savAcc = bank.createSavingsAccount("Ravi Kumar",  50000, 0.04);
        String curAcc = bank.createCurrentAccount("Priya Sharma", 20000, 5000);


        System.out.println("\n── Account Details ────────────────────");
        bank.printAccountDetails(savAcc);
        bank.printAccountDetails(curAcc);


        System.out.println("\n── Deposits ───────────────────────────");
        bank.deposit(savAcc, 10000);
        bank.deposit(curAcc, 5000);


        System.out.println("\n── Withdrawals ────────────────────────");
        bank.withdraw(savAcc, 8000);
        bank.withdraw(curAcc, 23000);


        System.out.println("\n── Transfer ───────────────────────────");
        bank.deposit(curAcc, 10000);
        bank.transfer(curAcc, savAcc, 7000);


        System.out.println("\n── Apply Interest ─────────────────────");
        bank.applyInterest(savAcc);
        bank.applyInterest(curAcc);

        System.out.println("\n── Final Balances ─────────────────────");
        bank.printAccountDetails(savAcc);
        bank.printAccountDetails(curAcc);


        bank.printHistory(savAcc);
        bank.printHistory(curAcc);


        System.out.println("\n── Error Handling Demo ────────────────");
        try {
            bank.withdraw(savAcc, 9999999);
        } catch (IllegalStateException e) {
            System.out.println("❌ Caught error: " + e.getMessage());
        }

        try {
            bank.deposit("INVALID999", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Caught error: " + e.getMessage());
        }
    }
}
