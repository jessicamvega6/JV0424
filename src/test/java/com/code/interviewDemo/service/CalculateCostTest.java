package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.Tools;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CalculateCostTest {

    @Test
    void calculateChargableDays_laddar_duringWeekOnly_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 3L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekOnly_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2023, 7, 3);
        Long expectedChargableDays = 2L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekAndWeekend_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 5L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 5L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekAndWeekend_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2022, 7, 3);
        Long expectedChargableDays = 4L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 5L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_duringWeekAndWeekend_yesHolidayDuringWeekend_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2021, 7, 2);
        Long expectedChargableDays = 2L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_laddar_laborDay_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        Long expectedChargableDays = 5L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 6L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_includesBothFourthOfJulyAndLaborDay_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2023, 7, 2);
        Long expectedChargableDays = 62L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 64L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_jackhammer_duringWeekOnly_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 3L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.JACKHAMMER_R.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.JACKHAMMER_R);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_jackhammer_duringWeekOnly_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2023, 7, 3);
        Long expectedChargableDays = 2L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.JACKHAMMER_R.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.JACKHAMMER_R);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_jackhammer_duringWeekAndWeekend_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 3L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.JACKHAMMER_R.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 5L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.JACKHAMMER_R);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_jackhammer_duringWeekAndWeekend_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2022, 7, 3);
        Long expectedChargableDays = 4L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.JACKHAMMER_R.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 7L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.JACKHAMMER_R);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_jackhammer_duringWeekAndWeekend_yesHolidayDuringWeekend_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2021, 7, 2);
        Long expectedChargableDays = 0L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.JACKHAMMER_R.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.JACKHAMMER_R);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_chainsaw_duringWeekOnly_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 3L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.CHAINSAW.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.CHAINSAW);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_chainsaw_duringWeekOnly_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2023, 7, 3);
        Long expectedChargableDays = 3L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.CHAINSAW.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.CHAINSAW);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_chainsaw_duringWeekAndWeekend_noHoliday_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 2);
        Long expectedChargableDays = 3L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.CHAINSAW.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 5L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.CHAINSAW);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_chainsaw_duringWeekAndWeekend_yesHolidayDuringWeek_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2022, 7, 2);
        Long expectedChargableDays = 3L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.CHAINSAW.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 4L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.CHAINSAW);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_chainsaw_duringWeekAndWeekend_yesHolidayDuringWeekend_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2021, 7, 2);
        Long expectedChargableDays = 2L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.CHAINSAW.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 4L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.CHAINSAW);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_chainsaw_laborDay_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
        Long expectedChargableDays = 4L;
        BigDecimal expectedFinalCharge = new BigDecimal(Tools.CHAINSAW.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 6L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 0, Tools.CHAINSAW);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }

    @Test
    void calculateChargableDays_3ChargableDays_25PercentDiscount_returnsExpectedResult() {
        LocalDate checkoutDate = LocalDate.of(2021, 7, 12);
        Long expectedChargableDays = 3L;
        int discountPercentage = 25;
        BigDecimal expectedPreDiscount = new BigDecimal(Tools.LADDER.getDailyCharge() * expectedChargableDays).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(1.49).setScale(2, RoundingMode.HALF_UP);
        BigDecimal expectedFinalCharge = expectedPreDiscount.subtract(expectedDiscountAmount).setScale(2, RoundingMode.HALF_UP);

        RentalTimeService rentalTimeService = new RentalTimeService(checkoutDate, 3L);

        CalculateCost calculateCost = new CalculateCost(rentalTimeService, 25, Tools.LADDER);
        assertThat(calculateCost.chargeableDays, equalTo(expectedChargableDays));
        assertThat(calculateCost.discountPercent, equalTo(discountPercentage));
        assertThat(calculateCost.preDiscountCharge, equalTo(expectedPreDiscount));
        assertThat(calculateCost.discountAmount, equalTo(expectedDiscountAmount));
        assertThat(calculateCost.finalCharge, equalTo(expectedFinalCharge));
    }
}