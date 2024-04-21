package com.code.interviewDemo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.Month.JULY;
import static java.time.Month.SEPTEMBER;

public class RentalTimeService {

    LocalDate checkoutDate;
    LocalDate dueDate;
    List<LocalDate> rentalDates;
    Long numberOfDays;
    boolean rentalFallsOnHoliday;
    String holidayName;
    boolean rentalFallsOnWeekend;
    int numberOfWeekendDays = 0; //figure out better name


    RentalTimeService(LocalDate checkoutDate, Long numberOfDaysRenting) {
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(numberOfDaysRenting);
        getAllDatesInRental(numberOfDaysRenting);
        checkIfRentalIncludesHoliday();
        checkIfRentalIncludesWeekends();
    }

    private void checkIfRentalIncludesWeekends() {
        for(LocalDate day : rentalDates) {
            isWeekend(day);
        }
    }

    private void checkIfRentalIncludesHoliday() {
        boolean laborDay = false;
        boolean july4th = false; 
        for(LocalDate day : rentalDates) {
            laborDay = isLaborDay(day);
            july4th = is4thOfJuly(day);
            if(laborDay || july4th) {
                break;
            }
        }
        rentalFallsOnHoliday = laborDay || july4th;
    }

    private void getAllDatesInRental(Long numberOfDaysRenting) {
        rentalDates = new ArrayList<>();
        for(long i = 1; i <= numberOfDaysRenting; i++){
            rentalDates.add(checkoutDate.plusDays(i));
        }
    }

    public boolean is4thOfJuly(LocalDate date) {
        if(date.getMonth().equals(JULY) && date.getDayOfMonth() == 4) {
            holidayName = "July 4th";
            return true;
        }
        return false;
    }

    public boolean isLaborDay(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.MONDAY && date.getMonth() == SEPTEMBER) {
            if (date.getDayOfMonth() <= 7) {
                holidayName = "Labor Day";
                return true;
            }
        }
        return false;
    }

    public boolean isWeekend(LocalDate date) {
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            numberOfWeekendDays++;
            rentalFallsOnWeekend = true;
        }
        return false;
    }

    //potential methods to work with 4th of July
    //getDayBefore
    //getDayAfter
}
