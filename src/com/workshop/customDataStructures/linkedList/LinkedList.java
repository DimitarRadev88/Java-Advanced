package com.workshop.customDataStructures.linkedList;

import java.util.function.Consumer;

public interface LinkedList {
    void addFirst(int element);
    void addLast(int element);
    int get(int index);
    int removeFirst();
    int removeLast();
    void forEach(Consumer<Integer> consumer);
    int[] toArray();
}
