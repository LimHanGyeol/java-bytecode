package com.tommy.book;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // default : RetentionPolicy.CLASS
@Target({ElementType.TYPE, ElementType.FIELD}) // Target 은 위치를 지정할 수 있다. 타입과 필드에 지정했다.
@Inherited // 상속이 되는 어노테이션
public @interface MyAnnotation {

    /*
     * 어노테이션은 String, Integer, List 등의 레퍼런스 타입을 값으로 가지고 있을 수 있다.
     * 기본 값을 가지고 있을 수도 있다.
     */
    String name() default "hangyeol";

    int number() default 100;

    /*
     * 키워드를 value 로 주면 어노테이션 사용 시 @MyAnnotation("name") 으로 key 없이 사용할 수 있다.
     * 그러나 n개의 인자를 가지고 있을 경우 key 값을 주어야 한다.
     */
    String value() default "keesun";
}
