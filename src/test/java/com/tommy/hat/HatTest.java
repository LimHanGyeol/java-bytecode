package com.tommy.hat;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
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
    @DisplayName("모자에서 토끼를 꺼내는 마술")
    void magic() {
        assertThat(new Hat().pullOut()).isEqualTo("Rabbit");
    }
}
