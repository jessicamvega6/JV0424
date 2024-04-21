package com.code.interviewDemo.domain;

public class Holiday {
    String holidayName;

    boolean fourthOfJulyOnWeekday;

    boolean holidayIsObservedDuringRental;

    public boolean isHolidayIsObservedDuringRental() {
        return holidayIsObservedDuringRental;
    }

    public void setHolidayIsObservedDuringRental(boolean holidayIsObservedDuringRental) {
        this.holidayIsObservedDuringRental = holidayIsObservedDuringRental;
    }

    public void setFourthOfJulyOnWeekday(boolean fourthOfJulyOnWeekday) {
        this.fourthOfJulyOnWeekday = fourthOfJulyOnWeekday;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }
}
