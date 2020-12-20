package com.tommy.proxy;

/**
 * Real Subject
 */
public class DefaultBookService implements BookService {

    @Override
    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }
}
