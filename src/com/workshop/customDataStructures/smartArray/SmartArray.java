package com.workshop.customDataStructures.smartArray;

import java.util.Arrays;
import java.util.function.Consumer;

public class SmartArray implements CustomList {
    private static final int INITIAL_CAPACITY = 4;
    int[] data;
    int size;
    int capacity;

    public SmartArray() {
        this.data = new int[INITIAL_CAPACITY];
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    @Override
    public void add(int element) {
        if (this.capacity == this.size) {
            resize();
        }
        this.data[this.size++] = element;
    }

    @Override
    public int get(int index) {
        validateIndex(index);
        return this.data[index];
    }


    @Override
    public int remove(int index) {
        validateIndex(index);
        int element = this.data[index];
        shiftLeft(index);
        this.size--;
        if (this.size < this.capacity / 2) {
            shrink();
        }
        return element;
    }

    @Override
    public boolean contains(int element) {
        return Arrays.binarySearch(Arrays.stream(this.data, 0, size).sorted().toArray(), element) >= 0;
    }

    @Override
    public void add(int index, int element) {
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
    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.data[i]);
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
        int[] fromIndex = Arrays.copyOfRange(this.data, index + 1, size);
        System.arraycopy(fromIndex, 0, this.data, index, fromIndex.length);
        this.data[this.size - 1] = 0;
    }

    private void shiftRight(int index) {
        int[] from = Arrays.copyOfRange(this.data, index, this.size);
        System.arraycopy(from, 0, this.data, index + 1, from.length);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size);
        }
    }

}
