package com.iteratorsAndComparators.exercise.comparingObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Person> people = new ArrayList<>();

        while (!"END".equals(input)) {
            String[] personInfo = input.split(" ");
            people.add(new Person(personInfo[0], Integer.parseInt(personInfo[1]), personInfo[2]));
            input = scanner.nextLine();
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;

        Person person = people.get(index);

        int count = (int) people.stream().filter(p -> p.equals(person)).count();

        if (count == 1) {
            System.out.println("No matches");
        } else {
            System.out.println(count + " " + (people.size() - count) + " " + people.size());
        }
    }
}
