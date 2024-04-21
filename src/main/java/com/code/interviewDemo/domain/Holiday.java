package com.code.interviewDemo.domain;

import java.time.LocalDate;

public class Holiday {
    LocalDate holidayDate;

    String holidayName;

    boolean fourthOfJulyOnSat;

    boolean fourthOfJulyOnWeekday;

    public boolean isFourthOfJulyOnWeekday() {
        return fourthOfJulyOnWeekday;
    }

    public void setFourthOfJulyOnWeekday(boolean fourthOfJulyOnWeekday) {
        this.fourthOfJulyOnWeekday = fourthOfJulyOnWeekday;
    }

    public boolean isFourthOfJulyOnSat() {
        return fourthOfJulyOnSat;
    }

    public void setFourthOfJulyOnSat(boolean fourthOfJulyOnSat) {
        this.fourthOfJulyOnSat = fourthOfJulyOnSat;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }
}
