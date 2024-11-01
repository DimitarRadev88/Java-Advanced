package com.workshop.basicAlgorithms;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        recursiveArraySum();
//        recursiveFactorial();
//        sumOfCoins();
        mergeSort();
    }

    private static void mergeSort() {

    }

    private static void sumOfCoins() {
        Map<Integer, Integer> coinCountMap = new LinkedHashMap<>();

        try {
            addCoins(coinCountMap);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        long countOfCoins = coinCountMap.values().stream().mapToInt(n -> n).sum();

        System.out.printf("Number of coins to take: %d%n", countOfCoins);
        coinCountMap.forEach((coin, count) -> System.out.printf("%d coin(s) with value %d%n", count, coin));

    }

    private static void addCoins(Map<Integer, Integer> coinCount) {
        int[] coins = Arrays.stream(SCANNER.nextLine().split(": ")[1].split(", "))
                .mapToInt(Integer::parseInt).toArray();

        int target = Integer.parseInt(SCANNER.nextLine().split(": ")[1]);

        while (target > 0) {
            int threshold = target;
            int coin = Arrays.stream(coins).filter(c -> c <= threshold).max().orElse(0);

            if (coin == 0) {
                throw new IllegalArgumentException("Error");
            }
            coinCount.put(coin, target / coin);

            target %= coin;
        }
    }

    private static void recursiveFactorial() {
        int number = Integer.parseInt(SCANNER.nextLine());

        System.out.println(calculateFactorial(number));
    }

    private static long calculateFactorial(int number) {
        if (number < 2) {
            return 1;
        }

        return number * calculateFactorial(number - 1);
    }

    private static void recursiveArraySum() {
        int[] numbers = readNumbersArray();

        System.out.println(getSum(numbers, 0));
    }

    private static int[] readNumbersArray() {
        return Arrays.stream(SCANNER.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static int getSum(int[] numbers, int index) {
        if (index == numbers.length) {
            return 0;
        }

        return numbers[index] + getSum(numbers, index + 1);
    }
}
