package com.definingClasses.exercise.carSalesman;

public class Engine {
    private String model;
    private int power;
    private int displacement;
    private String efficiency;

    public Engine(String model, int power, int displacement, String efficiency) {
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    public Engine(String model, int power, String efficiency) {
        this(model, power, -1, efficiency);
    }

    public Engine(String model, int power, int displacement) {
        this(model, power, displacement, "n/a");
    }

    public Engine(String model, int power) {
        this(model, power, -1, "n/a");
    }

    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        return String.format("""
                %s:
                Power: %s
                Displacement: %s
                Efficiency: %s
                """,
                this.model,
                this.power,
                this.displacement < 0 ? "n/a" : this.displacement,
                this.efficiency);
    }
}
