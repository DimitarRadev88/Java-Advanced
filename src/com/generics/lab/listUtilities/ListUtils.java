package com.generics.lab.listUtilities;

import java.util.List;

public class ListUtils {

    public static <T extends Comparable<T>> T getMax(List<T> items) {
        return items.stream().max(Comparable::compareTo).orElseThrow(IllegalArgumentException::new);
    }

    public static <T extends Comparable<T>> T getMin(List<T> items) {
        return items.stream().min(Comparable::compareTo).orElseThrow(IllegalArgumentException::new);
    }
}
