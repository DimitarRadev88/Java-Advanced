package com.generics.exercise.genericBox;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        Box<String> box = new Box<>();
//        IntStream.range(0, Integer.parseInt(scanner.nextLine())).forEach(l -> box.add(scanner.nextLine()));
//        System.out.println(box.getCountOfGreaterElements(scanner.nextLine()));

//        Box<Integer> box = new Box<>();
//        IntStream.range(0, Integer.parseInt(scanner.nextLine())).forEach(l -> box.add(Integer.parseInt(scanner.nextLine())));

        Box<Double> box = new Box<>();
        IntStream.range(0, Integer.parseInt(scanner.nextLine())).forEach(l -> box.add(Double.parseDouble(scanner.nextLine())));
        System.out.println(box.getCountOfGreaterElements(scanner.nextDouble()));

//        box.swap(scanner.nextInt(), scanner.nextInt());


//        System.out.println(box);
    }
}
