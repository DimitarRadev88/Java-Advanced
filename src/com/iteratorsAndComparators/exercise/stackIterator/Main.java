package com.iteratorsAndComparators.exercise.stackIterator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Stack STACK = new Stack();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!"END".equals(input)) {

            parseCommand(input);

            input = scanner.nextLine();
        }

        STACK.forEach(System.out::println);
        STACK.forEach(System.out::println);
    }

    private static void parseCommand(String input) {
        String[] command = input.replaceAll(",", "").split(" ");

        switch (command[0]) {
            case "Push" -> STACK.push(Arrays.stream(command).skip(1).mapToInt(Integer::parseInt).toArray());
            case "Pop" -> {
                try {
                    STACK.pop();
                } catch (UnsupportedOperationException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
