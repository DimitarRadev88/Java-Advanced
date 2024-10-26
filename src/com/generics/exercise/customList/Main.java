package com.generics.exercise.customList;

import java.util.Scanner;

public class Main {
    private static final CustomList<String> LIST = new SmartArray<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!"END".equals(input)) {
            String[] command = input.split(" ");

            parseCommand(command);

            input = scanner.nextLine();
        }
    }

    private static void parseCommand(String[] command) {
        switch (command[0]) {
            case "Add" -> LIST.add(command[1]);
            case "Remove" -> LIST.remove(Integer.parseInt(command[1]));
            case "Contains" -> System.out.println(LIST.contains(command[1]));
            case "Swap" -> LIST.swap(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
            case "Greater" -> System.out.println(LIST.countGreaterThan(command[1]));
            case "Sort" -> Sorter.sort(LIST);
            case "Max" -> System.out.println(LIST.getMax());
            case "Min" -> System.out.println(LIST.getMin());
            case "Print" -> LIST.forEach(System.out::println);
        }
    }
}
