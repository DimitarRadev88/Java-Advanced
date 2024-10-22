package com.definingClasses.exercise.rawData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Garage {
    private List<Car> carList;

    public Garage() {
        this.carList = new ArrayList<>();
    }

    public void addCar(Car car) {
        this.carList.add(car);
    }

    public List<String> getCarNamesFilteredByCargoType(String cargoType) {
        return switch (cargoType) {
            case "fragile" -> getAllCarsWithFragileCargo().stream().map(Car::getModel).collect(Collectors.toList());
            case "flamable" -> getAllCarsWithFlammableCargo().stream().map(Car::getModel).collect(Collectors.toList());
            default -> throw new IllegalStateException("Unexpected value: " + cargoType);
        };
    }

    private List<Car> getAllCarsWithFragileCargo() {
        return carList.stream().filter(car -> car.getCargo().getType().name().equalsIgnoreCase("fragile"))
                .filter(car -> car.hasLowPressureTyre(1)).collect(Collectors.toList());
    }

    private List<Car> getAllCarsWithFlammableCargo() {
        return carList.stream().filter(car -> car.getCargo().getType().name().equalsIgnoreCase("flammable"))
                .filter(car -> car.getPower() > 250).collect(Collectors.toList());
    }
}
