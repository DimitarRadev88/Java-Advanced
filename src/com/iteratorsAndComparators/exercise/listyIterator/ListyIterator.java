package com.iteratorsAndComparators.exercise.listyIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListyIterator implements Iterable<String> {

    private List<String> elements;
    private int index;

    public ListyIterator(String... elements) {
        setElements(elements);
    }

    private void setElements(String... elements) {
        this.elements = new ArrayList<>(List.of(elements));
    }


    public boolean hasNext() {
        return this.index < this.elements.size() - 1;
    }

    public boolean move() {
        if (hasNext()) {
            this.index++;
            return true;
        }
        return false;
    }

    public void print() {
        if (this.elements.isEmpty()) {
            throw new UnsupportedOperationException("Invalid Operation!");
        }
        System.out.println(this.elements.get(index));
    }


    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            private int index;
            @Override
            public boolean hasNext() {
                return this.index < elements.size();
            }

            @Override
            public String next() {
                return elements.get(this.index++);
            }
        };
    }
}
