package com.tommy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// annotation 을 Type 으로 한정 짓더라도, interface, class, enum 에서 사용할 수 있다.
// 타입을 구체적으로 지정할 수 없어 아쉬운 점이다.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE) // CLASS 로 설정하면 Bytecode 에서도 유지한다는 말이다. 그럴 필요가 없다.
public @interface Magic {
}
