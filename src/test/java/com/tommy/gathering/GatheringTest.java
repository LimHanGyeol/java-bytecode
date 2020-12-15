package com.tommy.gathering;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Jacoco 코드 커버리지 측정
 */
class GatheringTest {

    @Test
    @DisplayName("모임인원이 모두 차지 않았을 경우")
    void gatheringIsEnrollmentFullIsFalse() {
        Gathering gathering = new Gathering(100, 10);
        assertThat(gathering.isEnrollmentFull()).isFalse();
    }

    @Test
    @DisplayName("모임인원이 모두 찼을 경우")
    void gatheringIsEnrollmentFullIsTrue() {
        Gathering gathering = new Gathering(100, 100);
        assertThat(gathering.isEnrollmentFull()).isTrue();
    }

    @Test
    @DisplayName("모임인원이 초과했을 경우")
    void gatheringIsEnrollmentFullIsExceed() {
        Gathering gathering = new Gathering(100, 110);
        assertThat(gathering.isEnrollmentFull()).isTrue();
    }

    @Test
    @DisplayName("모임인원의 최대 인원이 0일 경우")
    void gatheringIsMaxNumberOfAttendeesIsZero() {
        Gathering gathering = new Gathering(0, 10);
        assertThat(gathering.isEnrollmentFull()).isFalse();
    }

}
