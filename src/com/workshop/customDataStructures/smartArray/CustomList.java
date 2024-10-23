package com.workshop.customDataStructures.smartArray;

import java.util.function.Consumer;

public interface CustomList {
    void  add(int element);
    int get(int index);
    int remove(int index);
    boolean contains(int element);
    void add(int index, int element);
    void forEach(Consumer<Integer> consumer);
    int size();
}
