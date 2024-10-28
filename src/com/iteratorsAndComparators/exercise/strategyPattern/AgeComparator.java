package com.iteratorsAndComparators.exercise.strategyPattern;

import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person f, Person s) {
        return Integer.compare(f.getAge(), s.getAge());
    }
}
