package com.workshop.customDataStructures.linkedList;

import java.util.function.Consumer;

public class CustomLinkedList implements LinkedList {
    private static class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void addFirst(int element) {
        Node current = new Node(element);

        if (this.size == 0) {
            this.head = current;
            this.tail = current;
        } else {
            this.head.prev = current;
            current.next = this.head;
            this.head = current;
        }
        size++;
    }

    @Override
    public void addLast(int element) {
        Node current = new Node(element);
        if (this.size == 0) {
            this.head = current;
            this.tail = current;
        } else {
            this.tail.next = current;
            current.prev = this.tail;
            this.tail = current;
        }
        size++;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size);
        }

        Node node;
        if (index < size / 2) {
            node = this.head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = this.tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node.value;
    }

    @Override
    public int removeFirst() {
        if (this.size == 0) {
            throw new UnsupportedOperationException("Cannot remove element from empty list");
        }
        int value = this.head.value;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
        this.size--;
        return value;
    }

    @Override
    public int removeLast() {
        if (this.size == 0) {
            throw new UnsupportedOperationException("Cannot remove element from empty list");
        }
        int value = this.tail.value;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        this.size--;
        return value;
    }

    @Override
    public void forEach(Consumer<Integer> consumer) {
        Node node = this.head;
        for (int i = 0; i < size; i++) {
            consumer.accept(node.value);
            node = node.next;
        }
    }

    @Override
    public int[] toArray() {
        int[] arr = new int[size];
        Node node = this.head;
        for (int i = 0; i < size; i++) {
            arr[i] = node.value;
            node = node.next;
        }
        return arr;
    }
}
