package com.definingClasses.exercise.pokemonTrainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trainer {
    private String name;
    private List<Pokemon> pokemonList;
    private int badges;

    public Trainer(String name) {
        this.name = name;
        this.pokemonList = new ArrayList<>();
        this.badges = 0;
    }

    public String getName() {
        return name;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemonList.add(pokemon);
    }

    private boolean hasPokemonWithElement(String element) {
        return pokemonList.stream().anyMatch(p -> p.getElement().equals(element));
    }

    public void compete(String element) {
        if (!hasPokemonWithElement(element)) {
            allPokemonLoseHealth();
        } else {
            this.badges++;
        }

    }

    public int getBadges() {
        return this.badges;
    }

    public void allPokemonLoseHealth() {
        pokemonList.forEach(p -> p.setHealth(p.getHealth() - 10));
        pokemonList.removeIf(Pokemon::isDead);
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.badges, this.pokemonList.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(name, trainer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
