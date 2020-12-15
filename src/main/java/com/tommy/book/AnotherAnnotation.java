package com.tommy.book;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface AnotherAnnotation {

    String value() default "keesun";

    int number() default 100;
}
