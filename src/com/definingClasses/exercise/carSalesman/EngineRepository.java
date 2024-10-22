package com.definingClasses.exercise.carSalesman;

import java.util.ArrayList;
import java.util.List;

public class EngineRepository {
    private List<Engine> engineList;

    public EngineRepository() {
        this.engineList = new ArrayList<>();
    }

    public void add(Engine engine) {
        engineList.add(engine);
    }

    public Engine get(String model) {
        return engineList.stream().filter(e -> e.getModel().equals(model)).findFirst().get();
    }
}
