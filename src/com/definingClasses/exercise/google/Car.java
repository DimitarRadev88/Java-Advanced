package com.definingClasses.exercise.google;

public class Car {
    private String model;
    private int horsepower;

    public Car(String model, int horsepower) {
        this.model = model;
        this.horsepower = horsepower;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.model, this.horsepower);
    }
}
