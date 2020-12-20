package com.tommy.proxy;

public class BookServiceProxy implements BookService {

    private BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("proxy start");
        bookService.rent(book);
        System.out.println("proxy end");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("다이나믹 프록시를 사용하므로 해당 클래스는 없어도 됨.");
    }
}
