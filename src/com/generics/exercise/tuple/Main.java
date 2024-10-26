package com.generics.exercise.tuple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] line = getSplitLine(scanner);
        Tuple<String, String> nameAndAddress = new Tuple<>(line[0] + " " + line[1], line[2]);

        line = getSplitLine(scanner);
        Tuple<String, Integer> nameAndLitersOfBeer = new Tuple<>(line[0], Integer.parseInt(line[1]));

        line = getSplitLine(scanner);
        Tuple<Integer, Double> integerDoubleTuple = new Tuple<>(Integer.parseInt(line[0]), Double.parseDouble(line[1]));

        System.out.println(nameAndAddress);
        System.out.println(nameAndLitersOfBeer);
        System.out.println(integerDoubleTuple);
    }

    private static String[] getSplitLine(Scanner scanner) {
        return scanner.nextLine().split(" ");
    }
}
