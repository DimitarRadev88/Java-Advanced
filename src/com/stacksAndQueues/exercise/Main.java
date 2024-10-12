package com.stacksAndQueues.exercise;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        reverseNumbersWithAStack();
//        basicStackOperations();
//        maximumElement();
//        basicQueueOperations();
//        balancedParentheses();
        recursiveFibonacci();
    }

    private static void recursiveFibonacci() {
        ArrayDeque<Long> stack = new ArrayDeque<>();
        getFibonacci(SCANNER.nextInt(), stack);
        System.out.println(stack.pop());
    }

    private static void getFibonacci(int n, ArrayDeque<Long> stack) {
        stack.push(1L);
        stack.push(1L);
        for (int i = 1; i < n; i++) {
            long prev = stack.pop();
            long next = prev + stack.peek();
            stack.push(prev);
            stack.push(next);
        }
    }


    private static void balancedParentheses() {
        char[] input = SCANNER.nextLine().toCharArray();
        Deque<Character> openingParentheses = new ArrayDeque<>();

        boolean areBalanced = input.length % 2 == 0;
        for (char c : input) {
            if (c == '{' || c == '[' || c == '(') {
                openingParentheses.push(c);
            } else {
                if (openingParentheses.isEmpty() || !areBalanced(openingParentheses.pop(), c)) {
                    areBalanced = false;
                    break;
                }
            }

        }

        System.out.println(areBalanced ? "YES" : "NO");

    }

    private static boolean areBalanced(char opening, char closing) {
        return switch (opening) {
            case '{':
                yield closing == '}';
            case '[':
                yield closing == ']';
            case '(':
                yield closing == ')';
            default:
                yield false;
        };
    }

    private static void basicQueueOperations() {
        int[] input = getInputNumbers();
        int countToOffer = input[0];
        int countToPoll = input[1];
        int numberToCheck = input[2];

        Deque<Integer> queue = new ArrayDeque<>();

        IntStream.range(0, countToOffer).forEach(n -> queue.offer(SCANNER.nextInt()));

        IntStream.range(0, countToPoll).forEach(n -> queue.poll());

        System.out.println(queue.contains(numberToCheck) ? true : getMinInteger(queue));
    }

    private static void maximumElement() {
        int commands = Integer.parseInt(SCANNER.nextLine());
        Deque<Integer> stack = new ArrayDeque<>();

        IntStream.range(0, commands).forEach(n -> {
            executeCommand(stack);
        });

    }

    private static void executeCommand(Deque<Integer> stack) {
        int[] command = getInputNumbers();

        switch (command[0]) {
            case 1 -> stack.push(command[1]);
            case 2 -> stack.pop();
            case 3 -> System.out.println(getMaxInteger(stack));
        }
    }


    private static void basicStackOperations() {
        int[] input = getInputNumbers();

        int countToPush = input[0];
        int countToPop = input[1];
        int numberToCheck = input[2];

        Deque<Integer> stack = new ArrayDeque<>();

        IntStream.range(0, countToPush).forEach(n -> stack.push(SCANNER.nextInt()));

        IntStream.range(0, countToPop).forEach(n -> stack.pop());

        System.out.println(stack.contains(numberToCheck) ? true : getMinInteger(stack));
    }

    private static Integer getMinInteger(Deque<Integer> deque) {
        return deque.stream().min(Integer::compareTo).orElse(0);
    }

    private static int getMaxInteger(Deque<Integer> deque) {
        return deque.stream().max(Integer::compareTo).orElse(0);
    }

    private static int[] getInputNumbers() {
        return Arrays.stream(SCANNER.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    private static void reverseNumbersWithAStack() {
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.stream(SCANNER.nextLine().split(" "))
                .map(Integer::parseInt)
                .toList().forEach(stack::push);

        IntStream.range(0, stack.size()).forEach(n -> System.out.print(stack.pop() + " "));
    }
}
