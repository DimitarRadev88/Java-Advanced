package com.definingClasses.lab.bankAccount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        BankAccountManager manager = new BankAccountManager();
        while (!"End".equals(input)) {
            String[] command = input.split(" ");

            parseCommand(command, manager);

            input = scanner.nextLine();
        }
    }

    private static void parseCommand(String[] command, BankAccountManager manager) {
        switch (command[0]) {
            case "Create" ->
                    System.out.println(manager.create());
            case "Deposit" ->
                    System.out.println(manager.deposit(Integer.parseInt(command[1]), Double.parseDouble(command[2])));
            case "SetInterest" ->
                    manager.setInterest(Double.parseDouble(command[1]));
            case "GetInterest" ->
                    System.out.println(manager.getInterest(Integer.parseInt(command[1]), Integer.parseInt(command[2])));
        }
    }
}
