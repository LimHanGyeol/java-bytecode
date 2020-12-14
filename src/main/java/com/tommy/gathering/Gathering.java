package com.tommy.gathering;

public class Gathering {

    /**
     * 최대 참가자
     */
    private final int maxNumberOfAttendees;

    /**
     * 현재 신청한 인원
     */
    private final int numberOfEnrollment;

    public Gathering(int maxNumberOfAttendees, int numberOfEnrollment) {
        this.maxNumberOfAttendees = maxNumberOfAttendees;
        this.numberOfEnrollment = numberOfEnrollment;
    }

    public boolean isEnrollmentFull() {
        if (maxNumberOfAttendees == 0) {
            return false;
        }
        return numberOfEnrollment >= maxNumberOfAttendees;
    }

}
