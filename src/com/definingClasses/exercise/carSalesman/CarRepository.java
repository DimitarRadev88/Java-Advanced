package com.definingClasses.exercise.carSalesman;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private List<Car> carList;

    public CarRepository() {
        this.carList = new ArrayList<>();
    }

    public void add(Car car) {
        this.carList.add(car);
    }

    public List<Car> getCars() {
        return this.carList;
    }
}
