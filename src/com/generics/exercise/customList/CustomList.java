package com.generics.exercise.customList;

import java.util.function.Consumer;

public interface CustomList<T extends Comparable<T>> extends Iterable<T> {
    void add(T element);
    T get(int index);
    T remove(int index);
    boolean contains(T element);
    void swap(int first, int second);
    int countGreaterThan(T element);
    T getMax();
    T getMin();
    void add(int index, T element);
    int size();

}
