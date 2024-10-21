package com.definingClasses.lab.bankAccount;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BankAccountManager {
    private List<BankAccount> bankAccounts;

    public BankAccountManager() {
        this.bankAccounts = new ArrayList<>();
    }

    public String create() {
        BankAccount bankAccount = new BankAccount();
        bankAccounts.add(bankAccount);
        return String.format("Account ID%s created", bankAccount.getId());
    }

    public String deposit(int id, double amount) {
        BankAccount bankAccount = getAccount(id);
        if (bankAccount == null) {
            return "Account does not exist";
        }

        bankAccount.deposit(amount);
        return String.format("Deposited %s to ID%d", new DecimalFormat("#.####").format(amount), id);
    }

    public void setInterest(double interest) {
        BankAccount.setInterestRate(interest);
    }

    public String getInterest(int id, int years) {
        BankAccount bankAccount = getAccount(id);
        if (bankAccount == null) {
            return "Account does not exist";
        }

        return String.format("%.2f", bankAccount.getInterest(years));
    }

    public BankAccount getAccount(int id) {
        return bankAccounts.stream().filter(bankAccount -> bankAccount.getId() == id).findFirst().orElse(null);
    }
}
