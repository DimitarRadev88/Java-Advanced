package com.definingClasses.exercise.rawData;

import java.util.Arrays;
import java.util.Comparator;

public class Tyres {
    Tyre[] tyres;

    public Tyres(Tyre[] tyres) {
        this.tyres = tyres;
    }

    public Tyre getLowestPressureTyre() {
        return Arrays.stream(tyres).min(Comparator.comparingDouble(Tyre::getPressure)).get();
    }
}
