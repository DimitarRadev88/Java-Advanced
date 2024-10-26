package com.generics.exercise.customList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class SmartArray<T extends Comparable<T>> implements CustomList<T> {
    private static final int INITIAL_CAPACITY = 4;
    T[] data;
    int size;
    int capacity;

    public SmartArray() {
        this.data = (T[]) new Comparable[4];
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    @Override
    public void add(T element) {
        if (this.capacity == this.size) {
            resize();
        }
        this.data[this.size++] = element;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return this.data[index];
    }


    @Override
    public T remove(int index) {
        validateIndex(index);
        T element = this.data[index];
        shiftLeft(index);
        this.size--;
        if (this.size < this.capacity / 2) {
            shrink();
        }
        return element;
    }

    @Override
    public boolean contains(T element) {
        return Arrays.binarySearch(Arrays.stream(this.data, 0, size).sorted().toArray(), element) >= 0;
    }

    @Override
    public void swap(int first, int second) {
        validateIndex(first);
        validateIndex(second);
        T firstElement = this.data[first];
        this.data[first] = this.data[second];
        this.data[second] = firstElement;
    }

    @Override
    public int countGreaterThan(T element) {
        return (int) Arrays.stream(this.data).filter(Objects::nonNull).filter(e -> e.compareTo(element) > 0).count();
    }

    @Override
    public T getMax() {
        return Arrays.stream(this.data).filter(Objects::nonNull).max(Comparable::compareTo).orElseThrow(() ->
                new IllegalStateException("Cannot get max element from empty SmartArray"));
    }

    @Override
    public T getMin() {
        return Arrays.stream(this.data).filter(Objects::nonNull).min(Comparable::compareTo).orElseThrow(() ->
                new IllegalStateException("Cannot get min element from empty SmartArray"));
    }

    @Override
    public void add(int index, T element) {
        validateAddIndex(index);
        if (this.capacity == this.size) {
            resize();
        }
        if (index == this.size - 1) {
            add(element);
        } else {
            shiftRight(index);
            this.data[index] = element;
            this.size++;
        }
    }


    @Override
    public int size() {
        return this.size;
    }

    private void resize() {
        this.capacity *= 2;
        this.data = Arrays.copyOf(this.data, this.capacity);
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size);
        }
    }

    private void shrink() {
        this.capacity /= 2;
        this.data = Arrays.copyOf(this.data, this.capacity);
    }

    private void shiftLeft(int index) {
        T[] fromIndex = Arrays.copyOfRange(this.data, index + 1, size);
        System.arraycopy(fromIndex, 0, this.data, index, fromIndex.length);
        this.data[this.size - 1] = null;
    }

    private void shiftRight(int index) {
        T[] from = Arrays.copyOfRange(this.data, index, this.size);
        System.arraycopy(from, 0, this.data, index + 1, from.length);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(this.data).filter(Objects::nonNull).iterator();
    }

}