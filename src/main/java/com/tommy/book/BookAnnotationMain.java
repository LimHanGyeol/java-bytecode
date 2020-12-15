package com.tommy.book;

import java.util.Arrays;

public class BookAnnotationMain {

    public static void main(String[] args) {
        System.out.println("어노테이션 조회 (조회되지 않음 - 어노테이션은 주석과 같기 때문)");

        /*
         * 어노테이션의 정보는 소스, 클래스까지는 남는다.
         * 바이트코드를 로딩했을때 메모리상에는 남지 않는다.
         * Runtime 에서도 정보를 유지하고 싶다면,
         * @Retention(RetentionPolicy.RUNTIME) 어노테이션을 붙여주어야 한다.
         */
        Arrays.stream(Book.class.getAnnotations())
                .forEach(System.out::println);

        System.out.println("어노테이션 상속 확인");
        Arrays.stream(MyBook.class.getAnnotations())
                .forEach(System.out::println);

        /*
         * 어노테이션이 상속이 되어도 특정 클래스의 속성만 가져온다.
         */
        System.out.println("특정 클래스의 어노테이션 확인");
        Arrays.stream(MyBook.class.getDeclaredAnnotations())
                .forEach(System.out::println);

        System.out.println("필드에 있는 어노테이션 가져오기");
        Arrays.stream(Book.class.getDeclaredFields())
                .forEach(field -> Arrays.stream(field.getAnnotations())
                        .forEach(annotation -> {
                            System.out.println("필드에 있는 어노테이션의 값 가져오기");
                            if (annotation instanceof MyAnnotation) {
                                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                                System.out.println(myAnnotation.name());
                                System.out.println(myAnnotation.number());
                            }
                        }));
    }
}
