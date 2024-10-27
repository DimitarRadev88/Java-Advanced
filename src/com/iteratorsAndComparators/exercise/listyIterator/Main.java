package com.iteratorsAndComparators.exercise.listyIterator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static ListyIterator iterator = new ListyIterator();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!"END".equals(input)) {

            parseCommand(input);

            input = scanner.nextLine();
        }
    }

    private static void parseCommand(String input) {
        String[] command = input.split(" ");

        switch (command[0]) {
            case "Create" -> iterator = new ListyIterator(Arrays.stream(command).skip(1).toArray(String[]::new));
            case "Move" -> System.out.println(iterator.move());
            case "Print" -> {
                try {
                    iterator.print();
                } catch (UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
            case "PrintAll" -> {
                iterator.forEach(e -> System.out.print(e + " "));
                System.out.println();
            }
            case "HasNext" -> System.out.println(iterator.hasNext());
        }
    }
}
