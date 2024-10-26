package com.generics.exercise.threeuple;

public class Threeuple<D, O, G> {
    private D first;
    private O second;
    private G third;

    public Threeuple() {
    }

    public Threeuple(D first, O second, G third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void setFirst(D item) {
        this.first = item;
    }
    public void setSecond(O item) {
        this.second = item;
    }
    public void setThird(G item) {
        this.third = item;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> %s", this.first, this.second, this.third);
    }
}
