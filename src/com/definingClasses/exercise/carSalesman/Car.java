package com.definingClasses.exercise.carSalesman;

public class Car {
    private String model;
    private Engine engine;
    private int weight;
    private String color;

    public Car(String model, Engine engine, int weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }
    public Car(String model, Engine engine, String color) {
        this(model, engine, -1, color);
    }

    public Car(String model, Engine engine, int weight) {
        this(model, engine, weight, "n/a");
    }

    public Car(String model, Engine engine) {
        this(model, engine, -1, "n/a");
    }

    @Override
    public String toString() {
        return  String.format("""
                %s:
                %s
                Weight: %s
                Color: %s
                """, this.model,
                engine.toString().trim(),
                this.weight < 0 ? "n/a" : this.weight,
                this.color);
    }
}
