package com.definingClasses.exercise.pokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private List<Trainer> trainerList;

    public Tournament() {
        this.trainerList = new ArrayList<>();
    }

    public void addTrainer(Trainer trainer) {
        if (!this.trainerList.contains(trainer)) {
            this.trainerList.add(trainer);
        }
    }

    public Trainer getTrainer(String name) {
        return trainerList.get(trainerList.indexOf(new Trainer(name)));
    }

    public boolean isInTournament(Trainer trainer) {
        return trainerList.contains(trainer);
    }

    public List<Trainer> getTrainerList() {
        return this.trainerList;
    }
}
