package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.Holiday;

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
    Long numberOfHolidaysDuringRental = 0L;

    boolean rentalFallsOnWeekend;
    int numberOfWeekendDays = 0; //figure out better name
    List<Holiday> holidays = new ArrayList<>();

    RentalTimeService(LocalDate checkoutDate, Long numberOfDaysRenting) {
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(numberOfDaysRenting);
        this.numberOfDays = numberOfDaysRenting;
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
        for(LocalDate day : rentalDates) {
            isLaborDay(day); // this needs to be a list so the later dates dont overwrite the boolean
            is4thOfJuly(day);
        }
        rentalFallsOnHoliday = !holidays.isEmpty();
    }

    private void getAllDatesInRental(Long numberOfDaysRenting) {
        rentalDates = new ArrayList<>();
        for(long i = 1; i <= numberOfDaysRenting; i++){
            rentalDates.add(checkoutDate.plusDays(i));
        }
    }


    public void is4thOfJuly(LocalDate date) {
        if(date.getMonth().equals(JULY) && date.getDayOfMonth() == 4) {
            Holiday holiday = new Holiday();
            holiday.setHolidayName("July 4th");
            holiday.setHolidayDate(date);

            if(date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                holiday.setFourthOfJulyOnSat(true);
            } else if (date.getDayOfWeek() != DayOfWeek.SUNDAY ) {
                holiday.setFourthOfJulyOnWeekday(true);
            }
            numberOfHolidaysDuringRental++; //might ot need this anymore
            holidays.add(holiday);
        }
    }

    public void isLaborDay(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.MONDAY && date.getMonth() == SEPTEMBER) {
            if (date.getDayOfMonth() <= 7) {
                Holiday holiday = new Holiday();
                holiday.setHolidayName("Labor Day");
                holiday.setHolidayDate(date);
                numberOfHolidaysDuringRental++;
                holidays.add(holiday);
            }
        }
    }

    public boolean isWeekend(LocalDate date) {
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            numberOfWeekendDays++;
            rentalFallsOnWeekend = true;
        }
        return false;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }
}
