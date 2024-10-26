package com.generics.exercise.customList;

public class Sorter {
    public static <T extends Comparable<T>> void sort(CustomList<T> items) {
        quickSort(items, 0, items.size() - 1);
    }

    private static <T extends Comparable<T>> void quickSort(CustomList<T> items, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(items, begin, end);


            quickSort(items, begin, partitionIndex - 1);
            quickSort(items, partitionIndex + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(CustomList<T> items, int begin, int end) {
        T pivot = items.get(end);
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (items.get(j).compareTo(pivot) <= 0) {
                i++;

                items.swap(i, j);
            }
        }

        items.swap(i + 1, end);

        return i + 1;
    }


}
