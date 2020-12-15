package com.tommy.di;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContainerServiceTest {

    @Test
    @DisplayName("BookRepository 객체 가져오기")
    void getBookRepository() {
        BookRepository bookRepository = ContainerService.getObject(BookRepository.class);
        assertThat(bookRepository).isNotNull();
    }

    @Test
    @DisplayName("@Inject로 주입된 BookService 객체 가져오기")
    void getBookService() {
        BookService bookService = ContainerService.getObject(BookService.class);
        assertThat(bookService).isNotNull();
        assertThat(bookService.bookRepository).isNotNull();
    }
}
