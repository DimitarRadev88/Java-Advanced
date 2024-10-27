package com.iteratorsAndComparators.exercise.stackIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

public class Stack implements Iterable<Integer> {

    private Integer[] elements;
    private int size;

    public Stack() {
        this.elements = new Integer[4];
    }

    public void push(int... elements) {
        for (int element : elements) {
            if (this.size >= this.elements.length) {
                grow();
            }
            this.elements[this.size++] = element;
        }
    }

    public int pop() {
        if (this.size == 0) {
            throw new UnsupportedOperationException("No elements");
        }

        if (this.elements.length < this.elements.length / 2) {
            shrink();
        }

        int element = this.elements[--this.size];
        this.elements[this.size] = null;
        return element;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        int index = this.size - 1;

        while (index >= 0) {
            action.accept(this.elements[index--]);
        }
    }

    private void shrink() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length / 2);
    }

    private void grow() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Integer> {

        private int index;
        @Override
        public boolean hasNext() {
            return elements[index + 1] != null;
        }

        @Override
        public Integer next() {
            return elements[index++];
        }
    }
}
