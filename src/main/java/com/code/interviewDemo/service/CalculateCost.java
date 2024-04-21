package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.Holiday;
import com.code.interviewDemo.domain.Tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateCost {

    int discountPercent;
    Long chargeableDays;
    Tools tool;
    BigDecimal preDiscountCharge;
    BigDecimal discountAmount;
    BigDecimal finalCharge;

    CalculateCost(RentalTimeService rentalTime, int discountPercent, Tools tool) {
        this.discountPercent = discountPercent;
        this.tool = tool;
        calculateChargableDays(rentalTime);
        calculatePreDiscountCharge();
        calculateDiscountAmount();
        calculateFinalCharge();
    }

    private void calculatePreDiscountCharge() {
        double preDiscountChargeBeforeRounding = tool.getDailyCharge() * chargeableDays;
        preDiscountCharge = BigDecimal.valueOf(preDiscountChargeBeforeRounding).setScale(2, RoundingMode.HALF_UP);
    }

    private void calculateDiscountAmount() {
        BigDecimal discount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100)) ;
        discountAmount = discount.setScale(2, RoundingMode.HALF_UP);
    }

    private void calculateFinalCharge() {
        BigDecimal finalChargePreRound = preDiscountCharge.subtract(discountAmount);
        finalCharge = finalChargePreRound.setScale(2, RoundingMode.HALF_UP);
    }

    void calculateChargableDays(RentalTimeService rentalTime) {
        chargeableDays = rentalTime.numberOfDaysRenting;
        if(rentalTime.rentalFallsOnWeekend) {
            rentalFallsOnWeekend(rentalTime);
        }
        if(!rentalTime.holidays.isEmpty()) {
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
        if(!tool.isHolidayCharge() && holiday.isHolidayIsObservedDuringRental()) {
            chargeableDays--;
        }
    }

    private void rentalFallsOnWeekend(RentalTimeService rentalTime) {
        if(!tool.isWeekendCharge()) {
            chargeableDays -= rentalTime.numberDaysInWeekendDuringRental;
        }
    }
}
