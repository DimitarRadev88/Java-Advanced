package com.iteratorsAndComparators.exercise.froggy;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Integer> visited = new ArrayList<>();
        while (!"END".equals(input)) {
            new Lake(Arrays.stream(input.split(", "))
                    .map(Integer::parseInt).collect(Collectors.toList())).forEach(visited::add);

            System.out.println(visited.toString().replaceAll("[\\[\\]]", ""));

            visited.clear();
            input = scanner.nextLine();
        }


    }
}
