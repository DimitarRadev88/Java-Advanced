package com.iteratorsAndComparators.lab.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Book bookOne = new Book("Animal Farm", 2003, "George Orwell");
        Book bookThree = new Book("The Documents in the Case", 2002);
        Book bookTwo = new Book("The Documents in the Case", 1930, "Dorothy Sayers", "Robert Eustace");

//        List<Book> books = new ArrayList<>();
//        books.add(bookOne);
//        books.add(bookTwo);
//        books.add(bookThree);


//        Library library = new Library(bookOne, bookTwo, bookThree);
//
//        for (Book book: library) {
//            System.out.println(book.getTitle());
//        }


//        if (bookOne.compareTo(bookTwo) > 0) {
//            System.out.println(String.format("%s is before %s", bookOne.getTitle(), bookTwo.getTitle()));
//        } else if (bookOne.compareTo(bookTwo) < 0) {
//            System.out.println(String.format("%s is before %s", bookTwo.getTitle(), bookOne.getTitle()));
//        } else {
//            System.out.println("Book are equal");
//        }

        List<Book> books = new ArrayList<>();
        books.add(bookOne);
        books.add(bookTwo);
        books.add(bookThree);

        books.sort(new BookComparator());

        for (Book book : books) {
            System.out.println(book.getTitle() + " " + book.getYear());
        }


    }
}
