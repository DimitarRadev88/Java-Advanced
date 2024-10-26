package com.generics.exercise.threeuple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] line = readSplitLine(scanner);

        Threeuple<String, String, String> firstThreeuple = new Threeuple<>(line[0] + " " + line[1], line[2], line[3]);
        line = readSplitLine(scanner);
        Threeuple<String, Integer, Boolean> secondThreeuple = new Threeuple<>(line[0], Integer.parseInt(line[1]), line[2].equals("drunk"));

        line = readSplitLine(scanner);
        Threeuple<String, Double, String> thirdThreeuple = new Threeuple<>(line[0], Double.parseDouble(line[1]), line[2]);

        System.out.println(firstThreeuple);
        System.out.println(secondThreeuple);
        System.out.println(thirdThreeuple);
    }

    private static String[] readSplitLine(Scanner scanner) {
        return scanner.nextLine().split(" ");
    }
}
