package com.definingClasses.lab.carConstructors;

public class Car {
    private String brand;
    private String model;
    private int horsePower;

    public Car(String brand, String model, int horsePower) {
        setBrand(brand);
        setModel(model);
        setHorsepower(horsePower);
    }

    public Car(String brand) {
        this.brand = brand;
        setModel("unknown");
        setHorsepower(-1);
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    private void setHorsepower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String carInfo() {
        return String.format("The car is: %s %s - %d HP.", this.brand, this.model, this.horsePower);
    }

    @Override
    public String toString() {
        return carInfo();
    }
}
