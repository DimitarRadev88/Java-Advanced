package com.workshop.customDataStructures.smartStack;

import java.util.function.Consumer;

public interface CustomStack {
    void push(int element);
    int pop();
    int peek();
    void forEach(Consumer<Integer> consumer);
}
