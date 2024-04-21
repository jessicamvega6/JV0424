package com.code.interviewDemo.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RentalTimeServiceTest {

    @Test
    void is4thOfJuly_fourthOfJuly_returnTrue() {

    }

    @Test
    void isLaborDay_laborDay_returnTrue() {
    }

    @Test
    void is4thOfJuly_anyOtherDayButJuly4Th_returnFalse() {

    }

    @Test
    void isLaborDay_anyOtherDayButLaborDay_returnFalse() {

    }

    @Test
    void isWeekend_givenADateOnSaturday_returnTrue() {
    }

    @Test
    void isWeekend_givenADateOnSunday_returnTrue() {
    }

    @Test
    void isWeekend_givenADateOnNonSatOrSun_returnFalse() {
    }

    @Test
    void constructorSetsCorrectDates() {
        LocalDate expectedCheckoutDate = LocalDate.of(2024, 4, 20);
        LocalDate expectedDueDate = LocalDate.of(2024, 4, 24);
        List<LocalDate> expectRentalDates = new ArrayList<>();
        expectRentalDates.add(LocalDate.of(2024, 4, 20));
        expectRentalDates.add(LocalDate.of(2024, 4, 21));
        expectRentalDates.add(LocalDate.of(2024, 4, 22));
        expectRentalDates.add(LocalDate.of(2024, 4, 23));
        expectRentalDates.add(LocalDate.of(2024, 4, 24));

        RentalTimeService service = new RentalTimeService("4/20/24", 4);

        assertThat(service.checkoutDate, equalTo(expectedCheckoutDate));
        assertThat(service.dueDate, equalTo(expectedDueDate));
        assertThat(service.rentalDates.size(), equalTo(5));
        assertThat(service.rentalDates.get(0), equalTo(expectRentalDates.get(0)));
        assertThat(service.rentalDates.get(1), equalTo(expectRentalDates.get(1)));
        assertThat(service.rentalDates.get(2), equalTo(expectRentalDates.get(2)));
        assertThat(service.rentalDates.get(3), equalTo(expectRentalDates.get(3)));
        assertThat(service.rentalDates.get(4), equalTo(expectRentalDates.get(4)));
    }
}