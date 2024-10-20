package com.functionalProgramming.exercise;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        consumerPrint();
//        knightsOfHonor();
//        customMinFunction();
//        appliedArithmetic();
//        reverseAndExclude();
//        predicateForNames();
//        findTheSmallestElement();
//        customComparator();
//        listOfPredicates();
        predicateParty();
    }

    private static void predicateParty() {
        BiPredicate<String, String> nameStartsWith = String::startsWith;
        BiPredicate<String, String> nameEndsWith = String::endsWith;
        BiPredicate<String, String> isOfLength = (name, length) -> name.length() == Integer.parseInt(length);

        Function<String, BiPredicate<String, String>> getPredicate = command -> {
            return switch (command) {
                case "StartsWith" -> nameStartsWith;
                case "EndsWith" -> nameEndsWith;
                case "Length" -> isOfLength;
                default -> throw new IllegalArgumentException();
            };
        };

        List<String> names = Arrays.stream(SCANNER.nextLine().split(" ")).collect(Collectors.toList());

        String input = SCANNER.nextLine();

        while (!"Party!".equals(input)) {
            String[] command = input.split(" ");
            String predicate = command[1];
            String parameter = command[2];
            switch (command[0]) {
                case "Remove": names.removeIf(n -> getPredicate.apply(predicate).test(n, parameter));
                break;
                case "Double":
                    List<String> toAdd = names.stream().filter(n -> getPredicate.apply(predicate).test(n, parameter)).toList();
                    names.addAll(toAdd);
                break;
            }
            input = SCANNER.nextLine();
        }

        System.out.println((names.isEmpty() ?
                "Nobody is" :
                (names.stream().sorted().toList().toString().replaceAll("[\\[\\]]", "") + " are")) +
                        " going to the party!");

    }

    private static void listOfPredicates() {
        int[] array = IntStream.rangeClosed(1, Integer.parseInt(SCANNER.nextLine())).toArray();
        BiPredicate<Integer, List<Integer>> isDivisibleByAll = (n, list) -> {
            for (int i : list) {
                if (n % i != 0) {
                    return false;
                }
            }
            return true;
        };

        List<Integer> divisors = getIntegerList(" ");
        System.out.println(Arrays.stream(array)
                .filter(n -> isDivisibleByAll.test(n, divisors))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static void customComparator() {
        Comparator<Integer> evenAndOdd = (f, s) -> {
            if (f % 2 == 0 && s % 2 != 0) {
                return -1;
            } else if (f % 2 != 0 && s % 2 == 0) {
                return 1;
            } else {
                return f.compareTo(s);
            }
        };

        Integer[] numbers = Arrays.stream(readIntArr(" ")).boxed().toArray(Integer[]::new);

        Arrays.sort(numbers, evenAndOdd);

        System.out.println(Arrays.stream(numbers).map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void findTheSmallestElement() {
        Function<List<Integer>, Integer> getMinElement = list -> list.lastIndexOf(list.stream().min(Integer::compare).get());

        List<Integer> numbers = getIntegerList("\\s+");

        System.out.println(getMinElement.apply(numbers));

    }

    private static List<Integer> getIntegerList(String delimiter) {
        return Arrays.stream(readIntArr(delimiter)).boxed().collect(Collectors.toList());
    }

    private static void predicateForNames() {
        BiPredicate<String, Integer> nameIsShorterThan = (name, length) -> name.length() <= length;

        int maxLength = Integer.parseInt(SCANNER.nextLine());

        Arrays.stream(SCANNER.nextLine().split(" "))
                .filter(name -> nameIsShorterThan.test(name, maxLength))
                .forEach(System.out::println);
    }

    private static void reverseAndExclude() {
        Consumer<List<Integer>> reverse = Collections::reverse;
        BiConsumer<List<Integer>, Integer> removeDivisibleBy = (list, number) -> list.removeIf(n -> n % number == 0);
        Consumer<List<Integer>> printList = list -> System.out.println(list.toString().replaceAll("[\\[\\],]", ""));
        Consumer<List<Integer>> reverseExcludeAndPrint = list -> {
            reverse.accept(list);
            removeDivisibleBy.accept(list, SCANNER.nextInt());
            printList.accept(list);
        };

        List<Integer> list = getIntegerList(" ");

        reverseExcludeAndPrint.accept(list);
    }

    private static void appliedArithmetic() {
        Consumer<List<Integer>> add = list -> {
            list.replaceAll(integer -> integer + 1);
        };
        Consumer<List<Integer>> multiply = list -> {
            list.replaceAll(integer -> integer * 2);
        };
        Consumer<List<Integer>> subtract = list -> {
            list.replaceAll(integer -> integer - 1);
        };
        Consumer<List<Integer>> print = list ->
                System.out.println(list.toString().replaceAll("[\\[\\],]", ""));

        Function<String, Consumer<List<Integer>>> getConsumer = command -> switch (command) {
            case "add" -> add;
            case "multiply" -> multiply;
            case "subtract" -> subtract;
            case "print" -> print;
            default -> throw new IllegalArgumentException();
        };

        List<Integer> numbers = getIntegerList(" ");

        String command = SCANNER.nextLine();

        while (!"end".equals(command)) {
            getConsumer.apply(command).accept(numbers);
            command = SCANNER.nextLine();
        }
    }

    private static int[] readIntArr(String delimiter) {
        return Arrays.stream(SCANNER.nextLine().split(delimiter))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void customMinFunction() {
        Function<int[], Integer> findSmallest = arr -> Arrays.stream(arr).min().getAsInt();

        int[] arr = readIntArr(" ");
        System.out.println(findSmallest.apply(arr));
    }

    private static void knightsOfHonor() {
        Consumer<String> printKnightedName = n -> System.out.println("Sir " + n);

        Arrays.stream(SCANNER.nextLine().split(" ")).forEach(printKnightedName);
    }

    private static void consumerPrint() {
        Consumer<String> print = System.out::println;

        Arrays.stream(SCANNER.nextLine().split(" ")).forEach(print);
    }
}
