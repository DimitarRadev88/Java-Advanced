package com.definingClasses.exercise.google;

public abstract class Relative {
    private String name;
    private String birthDay;

    protected Relative(String name, String birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.name, this.birthDay);
    }

}
