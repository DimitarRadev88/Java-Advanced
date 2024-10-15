package com.setsAndMapsAdvanced.lab;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        parkingLot();
//        softUniParty();
//        voinaNumberGame();
//        countRealNumbers();
//        averageStudentsGrades();
//        productShop();
//        citiesByContinentAndCountry();
//        academyGraduation();
//        largestThreeNumbers();
    }

    private static void largestThreeNumbers() {
        System.out.println(Arrays.stream(SCANNER.nextLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static void academyGraduation() {
        Map<String, Double> studentsNamesAndAverageGrade = getStudentsAverageGrades();

        studentsNamesAndAverageGrade.forEach((student, grade) -> {
            System.out.println(student + " is graduated with " + grade);
        });
    }

    private static Map<String, Double> getStudentsAverageGrades() {
        Map<String, Double> studentsNamesAndAverageGrade = new TreeMap<>();
        IntStream.range(0, Integer.parseInt(SCANNER.nextLine()))
                .forEach(input -> {
                    String name = SCANNER.nextLine();
                    double[] grades = Arrays.stream(SCANNER.nextLine().split(" "))
                            .mapToDouble(Double::parseDouble)
                            .toArray();
                    studentsNamesAndAverageGrade.put(name, Arrays.stream(grades).sum() / grades.length);
                });

        return studentsNamesAndAverageGrade;
    }

    private static void citiesByContinentAndCountry() {
        Map<String, Map<String, List<String>>> citiesByContinentAndCountry = getCitiesContinentsAndCountries();

        printFormattedContinentsCountriesAndCities(citiesByContinentAndCountry);
    }

    private static void printFormattedContinentsCountriesAndCities(Map<String, Map<String, List<String>>> citiesByContinentAndCountry) {
        citiesByContinentAndCountry.forEach((continent, map) -> {
            System.out.println(continent + ":");
            map.forEach((country, list) -> {
                System.out.println("  " + country + " -> " + String.join(", ", list));
            });
        });
    }

    private static Map<String, Map<String, List<String>>> getCitiesContinentsAndCountries() {
        Map<String, Map<String, List<String>>> map = new LinkedHashMap<>();

        IntStream.range(0, Integer.parseInt(SCANNER.nextLine()))
                .mapToObj(n -> SCANNER.nextLine().split(" "))
                .forEach(line -> {
                    map.putIfAbsent(line[0], new LinkedHashMap<>());
                    map.get(line[0]).putIfAbsent(line[1], new ArrayList<>());
                    map.get(line[0]).get(line[1]).add(line[2]);
                });

        return map;
    }

    private static void productShop() {
        Map<String, Map<String, Double>> shopProductPriceMap = getShopsProductsAndPrices();
        printFormattedShopsProductsAndPrices(shopProductPriceMap);
    }

    private static void printFormattedShopsProductsAndPrices(Map<String, Map<String, Double>> shopProductPriceMap) {
        shopProductPriceMap.forEach((shop, map) -> {
            System.out.println(shop + "->");
            map.forEach((product, price) -> {
                System.out.printf("Product: %s, Price: %.1f%n", product, price);
            });
        });
    }

    private static Map<String, Map<String, Double>> getShopsProductsAndPrices() {
        Map<String, Map<String, Double>> shopProductPriceMap = new TreeMap<>();
        String input = SCANNER.nextLine();
        while (!"Revision".equals(input)) {
            String[] shopProductPriceInfo = input.split(",\\s+");

            String shop = shopProductPriceInfo[0];
            String product = shopProductPriceInfo[1];
            double price = Double.parseDouble(shopProductPriceInfo[2]);

            shopProductPriceMap.putIfAbsent(shop, new LinkedHashMap<>());
            shopProductPriceMap.get(shop).put(product, price);

            input = SCANNER.nextLine();
        }

        return shopProductPriceMap;
    }

    private static void averageStudentsGrades() {
        Map<String, List<Double>> studentsGrades = getStudentsAndGrades();

        printFormattedStudentsAndGrades(studentsGrades);

    }

    private static void printFormattedStudentsAndGrades(Map<String, List<Double>> studentsGrades) {
        studentsGrades.forEach((s, list) -> System.out.printf("%s -> %s (avg: %.2f)%n",
                s,
                list.stream().map(n -> String.format("%.2f", n)).collect(Collectors.joining(" ")),
                list.stream().mapToDouble(d -> d).sum() / list.size()
        ));
    }

    private static Map<String, List<Double>> getStudentsAndGrades() {
        Map<String, List<Double>> studentsGrades = new TreeMap<>();
        IntStream.range(0, Integer.parseInt(SCANNER.nextLine())).forEach(line -> {
            String[] studentInfo = SCANNER.nextLine().split(" ");
            studentsGrades.putIfAbsent(studentInfo[0], new ArrayList<>());
            studentsGrades.get(studentInfo[0]).add(Double.parseDouble(studentInfo[1]));
        });

        return studentsGrades;
    }

    private static void countRealNumbers() {
        Map<Double, Integer> realNumbersCount = new LinkedHashMap<>();

        Arrays.stream(SCANNER.nextLine().split(" "))
                .mapToDouble(Double::parseDouble)
                .forEach(n -> {
                    realNumbersCount.putIfAbsent(n, 0);
                    realNumbersCount.put(n, realNumbersCount.get(n) + 1);
                });

        realNumbersCount.forEach((k, v) -> System.out.printf("%.1f -> %d%n", k, v));

    }


    private static void voinaNumberGame() {
        Set<Integer> firstPlayer = getIntegerLinkedHashSet();
        Set<Integer> secondPlayer = getIntegerLinkedHashSet();

        int rounds = 50;

        while (rounds-- > 0 && !firstPlayer.isEmpty() && !secondPlayer.isEmpty()) {
            play(firstPlayer, secondPlayer);
        }

        printGameEndResult(firstPlayer, secondPlayer);
    }

    private static void play(Set<Integer> firstPlayer, Set<Integer> secondPlayer) {
        int firstCard = firstPlayer.iterator().next();
        firstPlayer.remove(firstCard);
        int secondCard = secondPlayer.iterator().next();
        secondPlayer.remove(secondCard);

        if (firstCard > secondCard) {
            firstPlayer.add(firstCard);
            firstPlayer.add(secondCard);
        } else if (secondCard > firstCard) {
            secondPlayer.add(firstCard);
            secondPlayer.add(secondCard);
        }
    }

    private static void printGameEndResult(Set<Integer> firstPlayer, Set<Integer> secondPlayer) {
        if (firstPlayer.size() > secondPlayer.size()) {
            System.out.println("First player win!");
        } else if (secondPlayer.size() > firstPlayer.size()) {
            System.out.println("Second player win!");
        } else {
            System.out.println("Draw!");
        }
    }

    private static LinkedHashSet<Integer> getIntegerLinkedHashSet() {
        return Arrays.stream(SCANNER.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static void softUniParty() {
        Set<String> guestsSet = new TreeSet<>();

        addGuests(guestsSet);
        checkComingGuests(guestsSet);
        printAbsentGuests(guestsSet);

    }

    private static void printAbsentGuests(Set<String> guestsSet) {
        System.out.println(guestsSet.size());
        guestsSet.forEach(System.out::println);
    }

    private static void checkComingGuests(Set<String> guestsSet) {
        String input = SCANNER.nextLine();

        while (!"END".equals(input)) {
            guestsSet.remove(input);
            input = SCANNER.nextLine();
        }
    }

    private static void addGuests(Set<String> guestsSet) {
        String input = SCANNER.nextLine();

        while (!"PARTY".equals(input)) {
            guestsSet.add(input);
            input = SCANNER.nextLine();
        }
    }

    private static void parkingLot() {
        Set<String> carNumbers = new LinkedHashSet<>();

        String input = SCANNER.nextLine();

        while (!"END".equals(input)) {
            String[] directionAndNumber = input.split(",\\s+");

            switch (directionAndNumber[0]) {
                case "IN" -> carNumbers.add(directionAndNumber[1]);
                case "OUT" -> carNumbers.remove(directionAndNumber[1]);
            }

            input = SCANNER.nextLine();
        }

        if (carNumbers.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            carNumbers.forEach(System.out::println);
        }
    }
}
