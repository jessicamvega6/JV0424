package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.Tools;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CalculateCostTest {

    @Test
    void calculateChargableDays_laddar_duringWeekOnly_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 3L;
        Double expectedFinalCharge = Tools.LADDER.getDailyCharge() * expectedChargableDays;

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekOnly_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2023, 7, 3);
        Long expectedChargableDays = 2L;
        Double expectedFinalCharge = Tools.LADDER.getDailyCharge() * expectedChargableDays;

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekAndWeekend_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 5L;
        Double expectedFinalCharge = Tools.LADDER.getDailyCharge() * expectedChargableDays;

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 5L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekAndWeekend_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2022, 7, 3);
        Long expectedChargableDays = 2L;
        Double expectedFinalCharge = Tools.LADDER.getDailyCharge() * expectedChargableDays;

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekAndWeekend_yesHolidayDuringWeekend_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2021, 7, 2);
        Long expectedChargableDays = 2L;
        Double expectedFinalCharge = Tools.LADDER.getDailyCharge() * expectedChargableDays;

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_includesBothFourthOfJulyAndLaborDay_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2023, 7, 2);
        Long expectedChargableDays = 62L;
        Double expectedFinalCharge = Tools.LADDER.getDailyCharge() * expectedChargableDays;

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 64L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_3ChargableDays_25PercentDiscount_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2021, 7, 12);
        Long expectedChargableDays = 3L;
        int discountPercentage = 25;
        Double expectedPreDiscount = Tools.LADDER.getDailyCharge() * expectedChargableDays;

        double test = ( ((Tools.LADDER.getDailyCharge() * expectedChargableDays) * discountPercentage) / 100);
        Double expectedDiscountAmount = (double) (Math.round(test*100) ) /100;
        double expectedFinalChargeNotRounded = expectedPreDiscount - expectedDiscountAmount;
        Double expectedFinalCharge = (double) (Math.round(expectedFinalChargeNotRounded*100) ) /100;

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 25, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.discountPercent, equalTo(25));
        assertThat(calculateCost.preDiscountCharge, equalTo(expectedPreDiscount));
        assertThat(calculateCost.discountAmount, equalTo(1.49));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

}