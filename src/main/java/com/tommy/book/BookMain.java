package com.tommy.book;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class BookMain {

    public static void main(String[] args) throws ClassNotFoundException {
        // Class Type 을 가져오는 방법 1
        Class<Book> bookClass = Book.class;

        // Class Type 을 가져오는 방법 2
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // Class Type 을 가져오는 방법 3
        Class<?> aClass1 = Class.forName("com.tommy.book.Book");

        // d 만 나온다. 이유는 public 접근제어자만 리턴한다.
        Arrays.stream(bookClass.getFields())
                .forEach(System.out::println);

        System.out.println();

        // 접근제어자 상관 없이 모든 필드를 가져온다.
        Arrays.stream(bookClass.getDeclaredFields())
        // Arrays.stream(bookClass.getDeclaredField("f")) // 특정 이름의 필드를 가져온다.
                .forEach(System.out::println);

        System.out.println();

        // 리플렉션으로 접근제어자를 무시할 수 있다.
        Arrays.stream(bookClass.getDeclaredFields())
                .forEach(field -> {
                    try {
                        field.setAccessible(true); // 2. 해당 setter 호출하면 private 접근제어자에 접근할 수 있다.
                        System.out.printf("%s %s\n", field, field.get(book)); // 1. 호출 시 private 접근제어자에는 접근 하지 못한다고 나온다.
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println("메서드 가져오기");

        Arrays.stream(bookClass.getMethods())
                .forEach(System.out::println);

        System.out.println("생성자 가져오기");

        Arrays.stream(bookClass.getConstructors())
                .forEach(System.out::println);

        System.out.println("상속 받은 수퍼 클래스 가져오기");
        Class<? super MyBook> superclass = MyBook.class.getSuperclass();
        System.out.println(superclass);

        System.out.println("구현한 인터페이스 가져오기");
        Class<?>[] interfaces = MyBook.class.getInterfaces();
        Arrays.stream(interfaces)
                .forEach(System.out::println);

        System.out.println("필드들의 정보 가져오기");
        Arrays.stream(Book.class.getDeclaredFields())
                .forEach(field -> {
                    int modifiers = field.getModifiers();
                    System.out.println(field);
                    System.out.println(Modifier.isPrivate(modifiers));
                    System.out.println(Modifier.isStatic(modifiers));
                });

        System.out.println("메서드들의 정보 가져오기");
        Arrays.stream(Book.class.getMethods())
                .forEach(method -> {
                    int modifiers = method.getModifiers();
                    System.out.println(method.getReturnType());
                    System.out.println(Arrays.toString(method.getParameterTypes()));
                    System.out.println(method.getParameterCount());
                });

    }
}
