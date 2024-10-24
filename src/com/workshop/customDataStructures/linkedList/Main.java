package com.workshop.customDataStructures.linkedList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedList list = new CustomLinkedList();

        for (int i = 0; i < 7; i++) {
            list.addLast(i);
        }

        for (int i = 0; i < 7; i++) {
            System.out.println(list.get(i));
        }

        list.forEach(System.out::println);

        int[] arr = list.toArray();

        for (int i : arr) {
            System.out.println(i);
        }

        for (int i = 0; i < 6; i++) {
            System.out.println(list.removeFirst());
        }

        System.out.println(list.get(0));

//        for (int i = 0; i < 6; i++) {
//            System.out.println(list.removeLast());
//        }
    }
}
