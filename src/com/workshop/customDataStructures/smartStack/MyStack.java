package com.workshop.customDataStructures.smartStack;

import com.workshop.customDataStructures.smartArray.CustomList;
import com.workshop.customDataStructures.smartArray.SmartArray;

import java.util.function.Consumer;

public class MyStack implements CustomStack {

    CustomList data;

    public MyStack() {
        this.data = new SmartArray();
    }

    @Override
    public void push(int element) {
        this.data.add(element);
    }

    @Override
    public int pop() {
        if (this.data.size() == 0) {
            throw new IllegalStateException("No element to return from 'pop' operation because MyStack is empty!");
        }
        return this.data.remove(this.data.size() - 1);
    }

    @Override
    public int peek() {
        if (this.data.size() == 0) {
            throw new IllegalStateException("No element to return from 'pop' operation because MyStack is empty!");
        }
        return this.data.get(this.data.size() - 1);
    }

    @Override
    public void forEach(Consumer<Integer> consumer) {
        this.data.forEach(consumer);
    }
}
