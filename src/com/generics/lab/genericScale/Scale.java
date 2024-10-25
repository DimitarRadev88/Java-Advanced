package com.generics.lab.genericScale;

public class Scale<T extends Comparable<T>> {
    T left;
    T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier() {
        int result = this.left.compareTo(this.right);
        return result == 0 ? null : result > 0 ? this.left : this.right;
    }
}
