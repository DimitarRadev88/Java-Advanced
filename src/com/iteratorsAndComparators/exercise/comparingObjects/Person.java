package com.iteratorsAndComparators.exercise.comparingObjects;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(town, person.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, town);
    }

    @Override
    public int compareTo(Person o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            result = Integer.compare(this.age, o.age);
        }
        if (result == 0) {
            result = this.town.compareTo(o.town);
        }
        return result;
    }
}
