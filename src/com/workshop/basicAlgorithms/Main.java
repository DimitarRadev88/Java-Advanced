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
//        sortingAlgorithms();
        binarySearch();
    }

    private static void binarySearch() {
        int[] numbers = getNumbers();
        int number = Integer.parseInt(SCANNER.nextLine());

        int index = getIndex(numbers, number);

        System.out.println(index);
    }

    private static int getIndex(int[] arr, int number) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
        int mid = start + (end - start) / 2;
            if (arr[mid] == number) {
                return mid;
            } else if (arr[mid] > number) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }


    private static void sortingAlgorithms() {
        int[] numbers = getNumbers();
//        int[] sorted = mergeSort(numbers);

        sort(numbers);

        for (int number : numbers) {
            System.out.print(number + " ");
        }

    }

    private static int[] getNumbers() {
        return Arrays.stream(SCANNER.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, ++i, j);
            }
        }

        swap(arr, ++i, high);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        int middleIndex = arr.length / 2;

        int[] firstPartition = Arrays.copyOfRange(arr, 0, middleIndex);
        int[] secondPartition = Arrays.copyOfRange(arr, middleIndex, arr.length);

        firstPartition = mergeSort(firstPartition);
        secondPartition = mergeSort(secondPartition);

        int arrIndex = 0;
        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < firstPartition.length && secondIndex < secondPartition.length) {
            if (firstPartition[firstIndex] < secondPartition[secondIndex]) {
                arr[arrIndex++] = firstPartition[firstIndex++];
            } else {
                arr[arrIndex++] = secondPartition[secondIndex++];
            }
        }

        while (firstIndex < firstPartition.length) {
            arr[arrIndex++] = firstPartition[firstIndex++];
        }

        while (secondIndex < secondPartition.length) {
            arr[arrIndex++] = secondPartition[secondIndex++];
        }

        return arr;
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
        return getNumbers();
    }

    private static int getSum(int[] numbers, int index) {
        if (index == numbers.length) {
            return 0;
        }

        return numbers[index] + getSum(numbers, index + 1);
    }
}
