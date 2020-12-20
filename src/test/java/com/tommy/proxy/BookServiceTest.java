package com.tommy.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.bytebuddy.matcher.ElementMatchers.named;

class BookServiceTest {

    // private final BookService bookService = new BookServiceProxy(new DefaultBookService());
    
    @Test
    @DisplayName("리플렉션의 Proxy 클래스로 interface 기반의 다이나믹 프록시 생성")
    void rent() {
        // Java 의 Dynamic Proxy 는 interface 기반의 프록시만 생성한다. Class 기반이 Proxy 를 제공하지 않는다.
        BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(),
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

        Book book = new Book("bytecode");
        bookService.rent(book);
        bookService.returnBook(book);
    }

    @Test
    @DisplayName("cglib 으로 class 기반의 다이나믹 프록시 생성")
    void dynamicProxyCglib() {
        MethodInterceptor handler = new MethodInterceptor() {
            final BookService bookService = new DefaultBookService();

            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("dynamic proxy cglib start");
                    Object invoke = method.invoke(bookService, args);
                    System.out.println("dynamic proxy cglib end");
                    return invoke;
                }
                return method.invoke(bookService, args);
            }
        };
        BookService bookService = (BookService) Enhancer.create(BookService.class, handler);

        Book book = new Book("cglibProxy");
        bookService.rent(book);
        bookService.returnBook(book);
    }

    @Test
    @DisplayName("ByteBuddy 로 class 기반의 다이나믹 프록시 생성")
    void dynamicProxyByteBuddy() throws Exception {
        Class<? extends BookService> proxyClass = new ByteBuddy().subclass(BookService.class)
                .method(named("rent"))
                .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    final BookService bookService = new DefaultBookService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("dynamic proxy byteBuddy start");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("dynamic proxy byteBuddy end");
                        return invoke;
                    }
                }))
                .make()
                .load(BookService.class.getClassLoader())
                .getLoaded();

        BookService bookService = proxyClass.getConstructor(null).newInstance();

        Book book = new Book("byteBuddyProxy");
        bookService.rent(book);
    }

}
