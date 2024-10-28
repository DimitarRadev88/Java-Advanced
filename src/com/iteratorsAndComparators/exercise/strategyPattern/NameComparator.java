package com.iteratorsAndComparators.exercise.strategyPattern;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person f, Person s) {
        int result = Integer.compare(f.getName().length(), s.getName().length());
        if (result == 0) {
            result = Integer.compare(f.getName().toLowerCase().charAt(0), s.getName().toLowerCase().charAt(0));
        }
        return result;
    }
}
