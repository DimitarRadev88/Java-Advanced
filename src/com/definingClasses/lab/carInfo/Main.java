package com.definingClasses.lab.carInfo;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Car[] cars = IntStream.range(0, Integer.parseInt(scanner.nextLine()))
                .mapToObj(l -> getCar(scanner))
                .toArray(Car[]::new);

        Arrays.stream(cars).forEach(System.out::println);
    }

    private static Car getCar(Scanner scanner) {
        String[] carInfo = scanner.nextLine().split(" ");
        return new Car(carInfo[0], carInfo[1], Integer.parseInt(carInfo[2]));
    }
}
