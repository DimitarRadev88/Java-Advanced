package com.definingClasses.exercise.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private Job job;
    private List<Pokemon> pokemon;
    private List<Parent> parents;
    private List<Child> children;
    private Car car;

    public Person(String name) {
        this.name = name;
        this.job = null;
        this.pokemon = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
        this.car = null;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemon.add(pokemon);
    }

    public void addParent(Parent parent) {
        this.parents.add(parent);
    }

    public void addChild(Child child) {
        this.children.add(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("""
                        %s
                        Company:%s
                        Car:%s
                        Pokemon:%s
                        Parents:%s
                        Children:%s
                        """,
                this.name,
                this.job == null ? "" : System.lineSeparator() + this.job,
                this.car == null ? "" : System.lineSeparator() + this.car,
                this.pokemon.isEmpty() ?
                        "" :
                        System.lineSeparator() + this.pokemon.stream()
                                .map(Pokemon::toString)
                                .collect(Collectors.joining(System.lineSeparator())),
                this.parents.isEmpty() ?
                        "" :
                        System.lineSeparator() + this.parents.stream()
                                .map(Parent::toString)
                                .collect(Collectors.joining(System.lineSeparator())),
                this.children.isEmpty() ?
                        "" :
                System.lineSeparator() + this.children.stream()
                        .map(Child::toString)
                        .collect(Collectors.joining(System.lineSeparator()))
                ).trim();
    }
}
