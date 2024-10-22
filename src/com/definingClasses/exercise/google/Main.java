package com.definingClasses.exercise.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Person> PEOPLE = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            parsePersonInfo(input);
            input = scanner.nextLine();
        }

        String personToPrint = scanner.nextLine();

        System.out.println(PEOPLE.get(PEOPLE.indexOf(new Person(personToPrint))));
    }

    private static void parsePersonInfo(String input) {
        String[] info = input.split(" ");

        Person person = new Person(info[0]);

        if (!PEOPLE.contains(person)) {
            PEOPLE.add(person);
        }

        person = PEOPLE.get(PEOPLE.indexOf(person));

        switch (info[1]) {
            case "company" -> person.setJob(new Job(new Company(info[2]), new Department(info[3]), Double.parseDouble(info[4])));
            case "pokemon" -> person.addPokemon(new Pokemon(info[2], info[3]));
            case "parents" -> person.addParent(new Parent(info[2], info[3]));
            case "children" -> person.addChild(new Child(info[2], info[3]));
            case "car" -> person.setCar(new Car(info[2], Integer.parseInt(info[3])));
        }
    }
}
