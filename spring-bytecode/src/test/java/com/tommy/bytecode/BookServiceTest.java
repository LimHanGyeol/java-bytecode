package com.tommy.bytecode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    void getDependencyInjection() {
        assertThat(bookService.bookRepository).isNotNull();
    }
}
