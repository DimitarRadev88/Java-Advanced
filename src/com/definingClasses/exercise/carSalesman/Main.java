package com.definingClasses.exercise.carSalesman;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static final EngineRepository ENGINE_REPOSITORY = new EngineRepository();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CarRepository cars = new CarRepository();

        IntStream.range(0, Integer.parseInt(scanner.nextLine())).mapToObj(line -> scanner.nextLine().split(" "))
                .map(info -> createEngine(info)).forEach(engine -> ENGINE_REPOSITORY.add(engine));

        IntStream.range(0, Integer.parseInt(scanner.nextLine())).mapToObj(line -> scanner.nextLine().split(" "))
                .map(info -> createCar(info)).forEach(car -> cars.add(car));

        cars.getCars().forEach(System.out::print);
    }

    private static Car createCar(String[] specs) {
        Engine engine = ENGINE_REPOSITORY.get(specs[1]);
        if (specs.length == 4) {
            return new Car(specs[0], engine, Integer.parseInt(specs[2]), specs[3]);
        } else if (specs.length == 2) {
            return new Car(specs[0], engine);
        } else if (specs.length == 3 && isNumber(specs[2])) {
            return new Car(specs[0], engine, Integer.parseInt(specs[2]));
        } else if (specs.length == 3) {
            return new Car(specs[0], engine, specs[2]);
        }
        throw new IllegalStateException();
    }

    private static Engine createEngine(String[] specs) {
        if (specs.length == 4) {
            return new Engine(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]), specs[3]);
        } else if (specs.length == 2) {
            return new Engine(specs[0], Integer.parseInt(specs[1]));
        } else if (specs.length == 3 && isNumber(specs[2])) {
            return new Engine(specs[0], Integer.parseInt(specs[1]), Integer.parseInt(specs[2]));
        } else if (specs.length == 3) {
            return new Engine(specs[0], Integer.parseInt(specs[1]), specs[2]);
        }
        throw new IllegalStateException();
    }

    private static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
