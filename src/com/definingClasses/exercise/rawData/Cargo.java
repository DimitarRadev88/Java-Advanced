package com.definingClasses.exercise.rawData;

public class Cargo {
    private int weight;
    private CargoType type;

    public Cargo(int weight, CargoType type) {
        this.weight = weight;
        this.type = type;
    }

    public CargoType getType() {
        return this.type;
    }
}
