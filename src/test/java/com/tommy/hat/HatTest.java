package com.tommy.hat;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

class HatTest {

    @Test
    @DisplayName("모자에서 토끼를 꺼내는 마술 준비 (바이트코드 조작)")
    void prepareMagic() {
        try {
            new ByteBuddy().redefine(Hat.class)
                    .method(named("pullOut")).intercept(FixedValue.value("Rabbit"))
                    .make()
                    .saveIn(new File("C:\\Users\\HANGYEOL\\Documents\\java-bytecode\\target\\classes\\"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("모자에서 토끼를 꺼내는 마술1")
    void magic() {
        assertThat(new Hat().pullOut()).isEqualTo("Rabbit");
    }

    @Test
    @DisplayName("모자에서 토끼를 꺼내는 마술2") // "코드조작 + 확인 두 동작이 아닌 한번에 마술이 이루어지는 코드"
    void magic2() {
        ClassLoader classLoader = Hat.class.getClassLoader();
        TypePool typePool = TypePool.Default.of(classLoader);

        try {
            new ByteBuddy().redefine(
                    typePool.describe("com.tommy.hat.Hat").resolve(),
                    ClassFileLocator.ForClassLoader.of(classLoader))
                    .method(named("pullOut")).intercept(FixedValue.value("Rabbit"))
                    .make()
                    .saveIn(new File("C:\\Users\\HANGYEOL\\Documents\\java-bytecode\\target\\classes\\"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(new Hat().pullOut()).isEqualTo("Rabbit");
    }
}
