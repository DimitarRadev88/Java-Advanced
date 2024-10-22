package com.definingClasses.exercise.rawData;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tyres tyres;

    public Car(String model, Engine engine, Cargo cargo, Tyres tyres) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tyres = tyres;
    }

    public String getModel() {
        return this.model;
    }

    public double getPower() {
        return engine.getEnginePower();
    }

    public boolean hasLowPressureTyre(double threshold) {
        return tyres.getLowestPressureTyre().getPressure() < threshold;
    }

    public Cargo getCargo() {
        return this.cargo;
    }
}
