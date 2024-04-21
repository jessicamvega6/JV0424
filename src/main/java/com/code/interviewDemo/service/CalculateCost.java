package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.Holiday;
import com.code.interviewDemo.domain.RentalAgreement;
import com.code.interviewDemo.domain.Tools;

import java.time.Month;
import java.time.MonthDay;

public class CalculateCost {

    int discountPercent;
    Long chargeableDays;
    Tools tool;
    Double preDiscountCharge = 0.00;
    Double discountAmount = 0.00;
    Double finalCharge;

    CalculateCost(RentalTimeService rentalTime, int discountPercent, Tools tool) {
        this.discountPercent = discountPercent;
        this.tool = tool;
        calculateChargableDays(rentalTime);
        calculatePreDiscountCharge();
        calculateDiscountAmount();
        calculateFinalCharge();
    }

    private void calculatePreDiscountCharge() {
        preDiscountCharge = tool.getDailyCharge() * chargeableDays;
    }

    private void calculateDiscountAmount() {
        double discount = preDiscountCharge * discountPercent / 100;
        discountAmount = (double) Math.round(discount * 100) / 100;
    }

    private void calculateFinalCharge() {
        double finalChargePreRound = chargeableDays * tool.getDailyCharge() - discountAmount;
        finalCharge = (double) Math.round(finalChargePreRound * 100) / 100;
    }

    void calculateChargableDays(RentalTimeService rentalTime) {
        rentalDoesNotFallOnHoliday(rentalTime);
        if(rentalTime.rentalFallsOnHoliday) {
            rentalFallsOnHoliday(rentalTime);
        }
    }

    private void rentalFallsOnHoliday(RentalTimeService rentalTime) {
        for(Holiday holiday: rentalTime.getHolidays()) {
            if(holiday.getHolidayName().equalsIgnoreCase("Labor Day")) {
                chargeableDays--;
            } else {
                fourthOfJulyCalculation(holiday, rentalTime);
            }
        }
    }

    private void fourthOfJulyCalculation(Holiday holiday, RentalTimeService rentalTime) {
        //if the 4th falls on Saturday and the checkout date is on or before the 3rd, minus one day
        //if the 4th falls on Sunday and the due date is on or after the 5th, minus one day
        if(holiday.isFourthOfJulyOnWeekday() || (holiday.isFourthOfJulyOnSat()
                && rentalTime.checkoutDate.isBefore(rentalTime.checkoutDate.minusDays(1)))
                    || (!holiday.isFourthOfJulyOnSat() && rentalTime.dueDate.isAfter(holiday.getHolidayDate())) ) {
            chargeableDays--;
        }
    }

    private void rentalDoesNotFallOnHoliday(RentalTimeService rentalTime) {
        chargeableDays = rentalTime.numberOfDays;
        if(!tool.isWeekendCharge()) {
            chargeableDays -= rentalTime.numberOfWeekendDays;
        }
    }
}
