package com.definingClasses.lab.bankAccount;

public class BankAccount {
    private static final double DEFAULT_INTEREST_RATE = 0.02;
    private static double interestRate = DEFAULT_INTEREST_RATE;
    private static int bankAccountCount = 1;
    private int id;
    private double balance;

    public BankAccount() {
        this.balance = 0;
        this.id = bankAccountCount++;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double getInterest(int years) {
        return interestRate * years * this.balance;
    }

    public static void setInterestRate(double interest) {
        interestRate = interest;
    }

    public int getId() {
        return this.id;
    }
}
