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
    Long numberOfDaysRenting;
    boolean rentalFallsOnWeekend;
    int numberDaysInWeekendDuringRental = 0;
    List<Holiday> holidays = new ArrayList<>();

    RentalTimeService(LocalDate checkoutDate, Long numberOfDaysRenting) {
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(numberOfDaysRenting);
        this.numberOfDaysRenting = numberOfDaysRenting;
        getAllDatesInRental(numberOfDaysRenting);
        setHolidaysDuringRental();
        checkIfRentalIncludesWeekends();
    }

    private void checkIfRentalIncludesWeekends() {
        for(LocalDate day : rentalDates) {
            isWeekend(day);
        }
    }

    private void setHolidaysDuringRental() {
        for(LocalDate day : rentalDates) {
            isLaborDay(day);
            is4thOfJuly(day, rentalDates);
        }
    }

    private void getAllDatesInRental(Long numberOfDaysRenting) {
        rentalDates = new ArrayList<>();
        for(long i = 1; i <= numberOfDaysRenting; i++){
            rentalDates.add(checkoutDate.plusDays(i));
        }
    }

    public void is4thOfJuly(LocalDate date, List<LocalDate> rentalDates) {
        if(date.getMonth().equals(JULY) && date.getDayOfMonth() == 4) {
            Holiday holiday = new Holiday();
            holiday.setHolidayName("July 4th");

            if(date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                holidayObservedDuringRentalRange(date.minusDays(1), rentalDates, holiday);
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY ) {
                holidayObservedDuringRentalRange(date.plusDays(1), rentalDates, holiday);
            } else {
                holiday.setFourthOfJulyOnWeekday(true);
                holiday.setHolidayIsObservedDuringRental(true);
            }
            holidays.add(holiday);
        }
    }

    private void holidayObservedDuringRentalRange(LocalDate date, List<LocalDate> rentalDates, Holiday holiday) {
        for(LocalDate day : rentalDates) {
            if(day.equals(date)) {
                holiday.setHolidayIsObservedDuringRental(true);
            }
        }
    }

    public void isLaborDay(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.MONDAY && date.getMonth() == SEPTEMBER) {
            if (date.getDayOfMonth() <= 7) {
                Holiday holiday = new Holiday();
                holiday.setHolidayName("Labor Day");
                holidays.add(holiday);
                holiday.setHolidayIsObservedDuringRental(true);
            }
        }
    }

    public boolean isWeekend(LocalDate date) {
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            numberDaysInWeekendDuringRental++;
            rentalFallsOnWeekend = true;
        }
        return false;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }
}
