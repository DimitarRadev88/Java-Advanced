package com.functionalProgramming.lab;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        sortEvenNumbers();
//        sumNumbers();
//        countUppercaseWords();
//        addVAT();
//        filterByAge();
//        findEvenOrOdds();
    }

    private static void findEvenOrOdds() {
        BiFunction<Integer, Integer, int[]> getArrayInRange = (s, e) -> IntStream.rangeClosed(s, e).toArray();

        BiPredicate<String, Integer> numberFilter = (c, n) -> c.equals("odd") == (n % 2 != 0);
        int[] startAndEnd = Arrays.stream(SCANNER.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String condition = SCANNER.nextLine();
        System.out.println(Arrays.stream(getArrayInRange.apply(startAndEnd[0], startAndEnd[1]))
                .filter(n -> numberFilter.test(condition, n))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));

    }

    private static void filterByAge() {
        Map<String, Integer> peopleAndAges = new LinkedHashMap<>();

        Consumer<Map<String, Integer>> addEntryFromInput = map -> {
            String[] split = SCANNER.nextLine().split(", ");
            map.put(split[0], Integer.parseInt(split[1]));
        };

        BiConsumer<Map.Entry<String, Integer>, String> printDesiredInfo = (e, desiredInfoToPrint) -> {
            if (desiredInfoToPrint.equals("name")) {
                System.out.println(e.getKey());
            } else if (desiredInfoToPrint.equals("name age")) {
                System.out.println(e.getKey() + " - " + e.getValue());
            } else if (desiredInfoToPrint.equals("age")) {
                System.out.println(e.getValue());
            }
        };

        IntStream.range(0, Integer.parseInt(SCANNER.nextLine())).forEach(l -> addEntryFromInput.accept(peopleAndAges));

        String ageCondition = SCANNER.nextLine();
        int ageThreshold = Integer.parseInt(SCANNER.nextLine());
        String desiredInfoToPrint = SCANNER.nextLine();

        peopleAndAges.entrySet().stream().filter(e -> isTheDesiredAgeGroup(e.getValue(), ageCondition, ageThreshold))
                .forEach(e -> printDesiredInfo.accept(e, desiredInfoToPrint));
    }

    private static boolean isTheDesiredAgeGroup(int age, String ageCondition, int ageThreshold) {
        return switch (ageCondition) {
            case "older" -> age >= ageThreshold;
            case "younger" -> age <= ageThreshold;
            default -> throw new IllegalArgumentException();
        };
    }

    private static void addVAT() {
        UnaryOperator<Double> addVat = n -> n * 1.2;

        Arrays.stream(SCANNER.nextLine().split(", "))
                .mapToDouble(n -> addVat.apply(Double.parseDouble(n)))
                .forEach(n -> System.out.printf("%.2f%n", n));
    }

    private static void countUppercaseWords() {
        Predicate<String> startsWithUppercaseLetter = str -> Character.isUpperCase(str.charAt(0));

        String[] array = Arrays.stream(SCANNER.nextLine().split(" "))
                .filter(startsWithUppercaseLetter)
                .toArray(String[]::new);

        System.out.println(array.length);
        Arrays.stream(array).forEach(System.out::println);
    }

    private static void sumNumbers() {
        Function<String, Integer> parseInt = str -> {
            int number = 0;
            int tenths = 1;
            for (int i = str.length() - 1; i > 0; i--) {
                char current = str.charAt(i);
                if (!Character.isDigit(current)) {
                    throw new NumberFormatException("Number format exception occurred trying to parse \"" + str + "\"");
                }
                number += Character.getNumericValue(current) * tenths;
                tenths *= 10;
            }

            if (str.charAt(0) == '-') {
                number *= -1;
            } else {
                number += Character.getNumericValue(str.charAt(0)) * tenths;
            }

            return number;
        };

        String[] array = SCANNER.nextLine().split(", ");
        System.out.println("Count = " + array.length);
        System.out.println("Sum = " + Arrays.stream(array).mapToInt(parseInt::apply).sum());


    }

    private static void sortEvenNumbers() {
        int[] evenNumbers = Arrays.stream(SCANNER.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .filter(n -> n % 2 == 0)
                .toArray();

        System.out.println(getIntArrayJoinedWithDelimiter(evenNumbers, ", "));

        int[] sortedEvenNumbers = Arrays.stream(evenNumbers).sorted().toArray();

        System.out.println(getIntArrayJoinedWithDelimiter(sortedEvenNumbers, ", "));
    }

    private static String getIntArrayJoinedWithDelimiter(int[] arr, String delimiter) {
        return Arrays
                .stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(delimiter));
    }
}
