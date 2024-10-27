package com.iteratorsAndComparators.lab.book;

import java.util.Iterator;

public class Library implements Iterable<Book> {
    private Book[] books;

    public Library(Book... books) {
        this.books = books;
    }

    @Override
    public Iterator<Book> iterator() {
        return new LibIterator();
    }

    private class LibIterator implements Iterator<Book> {
        private int counter;

        public boolean hasNext() {
            return counter < books.length - 1;
        }

        public Book next() {
            return books[counter++];
        }

    }

}
