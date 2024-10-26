package com.generics.exercise.genericBox;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Comparable<T>> {
    private List<T> elements;

    public Box() {
        this.elements = new ArrayList<>();
    }

    public void add(T item) {
        this.elements.add(item);
    }

    public void swap(int first, int second) {
        if (isOutOfBounds(first) || isOutOfBounds(second)) {
            throw new IndexOutOfBoundsException("Cannot swap elements, " +
                    "because on of the input index values is out of bounds: " + "[" + first + ", " + second + "]");
        }
        T firstItem = this.elements.get(first);
        this.elements.set(first, this.elements.get(second));
        this.elements.set(second, firstItem);
    }

    public int getCountOfGreaterElements(T element) {
        return (int) this.elements.stream().filter(e -> e.compareTo(element) >0).count();
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= this.elements.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.elements.forEach(i -> sb.append(String.format("%s: %s",i.getClass().getName(), i)).append(System.lineSeparator()));
        return sb.toString();
    }
}
