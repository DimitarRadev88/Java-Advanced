package com.generics.exercise.tuple;

public class Tuple<T, E> {
    private T firstItem;
    private E secondItem;

    public Tuple() {
    }

    public Tuple(T firstItem, E secondItem) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    public void setFirstItem(T item) {
        this.firstItem = item;
    }

    public void setSecondItem(E item) {
        this.secondItem = item;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.firstItem, this.secondItem);
    }
}
