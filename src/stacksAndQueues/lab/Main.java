package stacksAndQueues.lab;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        browserHistory();
//        simpleCalculator();
//        decimalToBinaryConverter();
//        matchingBrackets();
        printerQueue();
    }

    private static void printerQueue() {
        Deque<String> printerQueue = new ArrayDeque<>();

        String input = SCANNER.nextLine();

        while (!"print".equals(input)) {
            if ("cancel".equals(input)) {
                if (!printerQueue.isEmpty()) {
                    System.out.println("Canceled " + printerQueue.poll());
                } else {
                    System.out.println("Printer is on standby");
                }
            } else {
                printerQueue.offer(input);
            }

            input = SCANNER.nextLine();
        }

        printerQueue.forEach(System.out::println);
    }

    private static void matchingBrackets() {
        String[] expression = SCANNER.nextLine().split("");

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals("(")) {
                stack.push(i);
            } else if (expression[i].equals(")")) {
                for (int j = stack.pop(); j <= i; j++) {
                    System.out.print(expression[j]);
                }
                System.out.println();
            }
        }

    }

    private static void decimalToBinaryConverter() {
        int number = Integer.parseInt(SCANNER.nextLine());
        if (number == 0) {
            System.out.println(0);
            return;
        }
        Deque<Integer> binaryStack = new ArrayDeque<>();

        while (number > 0) {
            binaryStack.push(number % 2);
            number /= 2;
        }

        binaryStack.forEach(System.out::print);

    }

    private static void simpleCalculator() {
        Deque<String> stack = Arrays.stream(SCANNER.nextLine().split(" "))
                .collect(Collectors.toCollection(ArrayDeque::new));

        int sum = Integer.parseInt(stack.pop());
        while (stack.size() >= 2) {
            String operator = stack.pop();
            if (operator.equals("-")) {
                sum -= Integer.parseInt(stack.pop());
            } else {
                sum += Integer.parseInt(stack.pop());
            }

        }

        System.out.println(sum);

    }

    private static void browserHistory() {
        Deque<String> historyStack = new ArrayDeque<>();

        String input = SCANNER.nextLine();

        while (!"Home".equals(input)) {
            if (!"back".equals(input)) {
                historyStack.push(input);
                System.out.println(input);
            } else {
                if (historyStack.size() <= 1) {
                    System.out.println("no previous URLs");
                } else {
                    historyStack.pop();
                    System.out.println(historyStack.peek());
                }
            }

            input = SCANNER.nextLine();
        }
    }
}
