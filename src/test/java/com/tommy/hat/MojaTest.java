package com.tommy.hat;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MojaTest {

    @Test
    void magic() {
        Moja moja = new MagicHat();
        assertThat(moja.pullOut()).isEqualTo("Rabbit!");
    }
}
