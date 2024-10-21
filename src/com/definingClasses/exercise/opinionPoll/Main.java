package com.definingClasses.exercise.opinionPoll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Person> personList = new ArrayList<>();

        addPeopleToPoll(scanner, personList);

        printAllAbove30(personList);
    }

    private static void printAllAbove30(List<Person> personList) {
        personList.stream().filter(p -> p.getAge() > 30).sorted(Comparator.comparing(Person::getName)).forEach(System.out::println);
    }

    private static void addPeopleToPoll(Scanner scanner, List<Person> personList) {
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            String[] personInfo = scanner.nextLine().split(" ");
            personList.add(new Person(personInfo[0], Integer.parseInt(personInfo[1])));
        }
    }
}
