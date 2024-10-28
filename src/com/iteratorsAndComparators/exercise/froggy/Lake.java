package com.iteratorsAndComparators.exercise.froggy;

import java.util.Iterator;
import java.util.List;

public class Lake implements Iterable<Integer> {

    private List<Integer> numbers;

    public Lake(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }

    public class Frog implements Iterator<Integer> {

        private int index;

        @Override
        public boolean hasNext() {
            return this.index < numbers.size();
        }

        @Override
        public Integer next() {
            int number = numbers.get(index);
            if (this.index + 2 >= numbers.size() && this.index % 2 == 0) {
                this.index = 1;
            } else {
                this.index += 2;
            }
            return number;
        }
    }
}
