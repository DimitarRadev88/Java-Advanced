package com.setsAndMapsAdvanced.exercise;

import java.util.*;
import java.util.function.IntToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
//        uniqueUsernames();
//        setsOfElements();
//        periodicTable();
//        countSymbols();
//        phonebook();
//        fixEmails();
        handsOfCards();
    }

    private static void handsOfCards() {
        String input = SCANNER.nextLine();

        Map<String, Set<String>> playersAndHands = new LinkedHashMap<>();
        while (!"JOKER".equals(input)) {
            String[] nameAndHand = input.split(": ");

            playersAndHands.putIfAbsent(nameAndHand[0], new HashSet<>());

            playersAndHands.get(nameAndHand[0]).addAll(Arrays.stream(nameAndHand[1].split(", ")).toList());

            input = SCANNER.nextLine();
        }

        playersAndHands.forEach((p, hand) -> {
            int sum = hand.stream().mapToInt(c -> getCardValue(c)).sum();
            System.out.println(p + ": " + sum);
        });
    }

    private static int getCardValue(String c) {
        return getCardPower(c.substring(0, c.length() - 1)) * getCardTypeMultiplier(c.charAt(c.length() - 1));
    }

    private static int getCardTypeMultiplier(char type) {
        return switch (type) {
            case 'S' -> 4;
            case 'H' -> 3;
            case 'D' -> 2;
            case 'C' -> 1;
            default -> 0;
        };
    }

    private static int getCardPower(String power) {
        return switch (power) {
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            case "A" -> 14;
            default -> Integer.parseInt(power);
        };

    }

    private static void fixEmails() {
        Map<String, String> contactEmails = new LinkedHashMap<>();

        String input = SCANNER.nextLine();
        while (!"stop".equals(input)) {
            String email = SCANNER.nextLine();

            if (!email.endsWith("us") && !email.endsWith("uk") && !email.endsWith("com")) {
                contactEmails.put(input, email);
            }

            input = SCANNER.nextLine();
        }

        contactEmails.forEach((n, e) -> System.out.println(n + " -> " + e));
    }

    private static void phonebook() {
        Map<String, String> phonebook = new HashMap<>();

        addContacts(phonebook);
        searchForContact(phonebook);

    }

    private static void searchForContact(Map<String, String> phonebook) {
        String input = SCANNER.nextLine();
        while (!"stop".equals(input)) {
            if (!phonebook.containsKey(input)) {
                System.out.println("Contact " + input + " does not exist.");
            } else {
                System.out.println(input + " -> " + phonebook.get(input));
            }

            input = SCANNER.nextLine();
        }
    }

    private static void addContacts(Map<String, String> phonebook) {
        String input = SCANNER.nextLine();

        while (!"search".equals(input)) {
            String[] contactInfo = input.split("-");
            phonebook.put(contactInfo[0], contactInfo[1]);
            input = SCANNER.nextLine();
        }
    }

    private static void countSymbols() {
        Map<Character, Integer> symbolsCount = new TreeMap<>();

        for (char c : SCANNER.nextLine().toCharArray()) {
            symbolsCount.putIfAbsent(c, 0);
            symbolsCount.put(c, symbolsCount.get(c) + 1);
        }

        symbolsCount.forEach((s, c) -> System.out.println(s + ": " + c + " time/s"));
    }

    private static void periodicTable() {
        Set<String> compoundsSet = new TreeSet<>();
        IntStream.range(0, Integer.parseInt(SCANNER.nextLine()))
                .forEach(line -> {
                    compoundsSet.addAll(Arrays.asList(SCANNER.nextLine().split(" ")));
                });

        System.out.println(String.join(" ", compoundsSet));
    }

    private static void setsOfElements() {
        int n = SCANNER.nextInt();
        int m = SCANNER.nextInt();
        SCANNER.nextLine();

        Set<Integer> setN = getIntegerLinkedHashSet(n);
        setN.retainAll(getIntegerLinkedHashSet(m));

        System.out.println(setN.toString().replaceAll("[]\\[\\,]", ""));
    }

    private static LinkedHashSet<Integer> getIntegerLinkedHashSet(int size) {
        return IntStream.range(0, size)
                .map(line -> Integer.parseInt(SCANNER.nextLine()))
                .boxed()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static void uniqueUsernames() {
        IntStream.range(0, Integer.parseInt(SCANNER.nextLine()))
                .mapToObj(line -> SCANNER.nextLine())
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .forEach(System.out::println);
    }
}
