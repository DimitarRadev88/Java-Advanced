package com.iteratorsAndComparators.exercise.strategyPattern;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Person> peopleByName = new TreeSet<>(new NameComparator());
        Set<Person> peopleByAge = new TreeSet<>(new AgeComparator());

        IntStream.range(0, Integer.parseInt(scanner.nextLine())).forEach(l -> {
            String[] personInfo = scanner.nextLine().split(" ");
            Person person = new Person(personInfo[0], Integer.parseInt(personInfo[1]));
            peopleByName.add(person);
            peopleByAge.add(person);
        });

        peopleByName.forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
        peopleByAge.forEach(p -> System.out.println(p.getName() + " " + p.getAge()));
    }
}
