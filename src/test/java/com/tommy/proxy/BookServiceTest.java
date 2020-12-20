package com.tommy.proxy;

import org.junit.jupiter.api.Test;

class BookServiceTest {

    private final BookService bookService = new BookServiceProxy(new DefaultBookService());

    @Test
    void rent() {
        Book book = new Book("bytecode");
        bookService.rent(book);
    }
}
