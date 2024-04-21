package com.code.interviewDemo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RentalTimeService {

    LocalDate checkoutDate;
    LocalDate dueDate;
    List<LocalDate> rentalDates;
    boolean rentalFallsOnHoliday;
    String holidayName;

    RentalTimeService(String checkoutString, int numberOfDaysRenting) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yy");
        LocalDate checkoutDate = LocalDate.parse(checkoutString, dateTimeFormatter);
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(numberOfDaysRenting);
        getAllDatesInRental(numberOfDaysRenting);
    }

    private void getAllDatesInRental(int numberOfDaysRenting) {
        rentalDates = new ArrayList<>();
        for(int i = 0; i <= numberOfDaysRenting; i++){
            rentalDates.add(checkoutDate.plusDays(i));
        }
    }

    public boolean is4thOfJuly(LocalDate date) {
        return true;
    }

    public boolean isLaborDay(LocalDate date) {
        return true;
    }

    public boolean isWeekend(LocalDate date) {
        return true;
    }

    //potential methods to work with 4th of July
    //getDayBefore
    //getDayAfter
}
