package com.definingClasses.exercise.rawData;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Garage garage = new Garage();

        IntStream.range(0, Integer.parseInt(scanner.nextLine())).mapToObj(l -> scanner.nextLine().split(" "))
                .map(info -> getCar(info)).forEach(car -> garage.addCar(car));

        String wantedCargo = scanner.nextLine();

        garage.getCarNamesFilteredByCargoType(wantedCargo).forEach(System.out::println);
    }

    private static Car getCar(String[] info) {
        Engine engine = new Engine(Integer.parseInt(info[1]), Integer.parseInt(info[2]));
        Cargo cargo = new Cargo(Integer.parseInt(info[3]), CargoType.valueOf(info[4].equals("flamable") ? "FLAMMABLE" : info[4].toUpperCase()));
        Tyres tyres = new Tyres(new Tyre[]{
                new Tyre(Double.parseDouble(info[5]), Integer.parseInt(info[6])),
                new Tyre(Double.parseDouble(info[7]), Integer.parseInt(info[8])),
                new Tyre(Double.parseDouble(info[9]), Integer.parseInt(info[10])),
                new Tyre(Double.parseDouble(info[11]), Integer.parseInt(info[12])),
        });
        return new Car(info[0], engine, cargo, tyres);
    }
}
