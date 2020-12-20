package com.tommy.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class BookServiceTest {

    // private final BookService bookService = new BookServiceProxy(new DefaultBookService());

    // Java 의 Dynamic Proxy 는 interface 기반의 프록시만 생성한다. Class 기반이 Proxy 를 제공하지 않는다.
    private final BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(),
            new Class[]{BookService.class}, new InvocationHandler() {
                final BookService bookService = new DefaultBookService();

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().equals("rent")) {
                        System.out.println("proxy start");
                        Object invoke = method.invoke(bookService, args); // DefaultBookService Return
                        System.out.println("proxy end");
                        return invoke;
                    }
                    return method.invoke(bookService, args);
                }
            });

    @Test
    void rent() {
        Book book = new Book("bytecode");
        bookService.rent(book);
        bookService.returnBook(book);
    }
}
