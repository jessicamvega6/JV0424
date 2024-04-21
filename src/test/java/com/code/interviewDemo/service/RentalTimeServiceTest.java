package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.Tools;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RentalTimeServiceTest {

    @Test
    void is4thOfJuly_fourthOfJuly_returnTrue() {
        LocalDate checkoutDate = LocalDate.of(2024, 7, 3);

        RentalTimeService service = new RentalTimeService(checkoutDate, 4L);

        assertThat(service.rentalFallsOnHoliday, equalTo(true));
        assertThat(service.getHolidays().size(), equalTo(1));
        assertThat(service.getHolidays().get(0).getHolidayName(), equalTo("July 4th"));
    }

    @Test
    void isLaborDay_laborDay_returnTrue() {
        LocalDate checkoutDate = LocalDate.of(2024, 9, 1);

        RentalTimeService service = new RentalTimeService(checkoutDate, 4L);

        assertThat(service.rentalFallsOnHoliday, equalTo(true));
        assertThat(service.getHolidays().size(), equalTo(1));
        assertThat(service.getHolidays().get(0).getHolidayName(), equalTo("Labor Day"));

    }

    @Test
    void is4thOfJuly_anyOtherDayButJuly4Th_returnFalse() {
        LocalDate checkoutDate = LocalDate.of(2024, 7, 5);

        RentalTimeService service = new RentalTimeService(checkoutDate, 1L);

        assertThat(service.rentalFallsOnHoliday, equalTo(false));
    }

    @Test
    void isLaborDay_anyOtherDayButLaborDay_returnFalse() {
        LocalDate checkoutDate = LocalDate.of(2024, 9, 9);

        RentalTimeService service = new RentalTimeService(checkoutDate, 4L);

        assertThat(service.rentalFallsOnHoliday, equalTo(false));
    }

    @Test
    void isLaborDay_laborDayOnMonday7_returnTrue() {
        LocalDate checkoutDate = LocalDate.of(2026, 9, 6);

        RentalTimeService service = new RentalTimeService(checkoutDate, 4L);

        assertThat(service.rentalFallsOnHoliday, equalTo(true));
    }

    @Test
    void isWeekend_givenADateOnSaturday_returnTrue() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 19);

        RentalTimeService service = new RentalTimeService(checkoutDate, 4L);

        assertThat(service.rentalFallsOnWeekend, equalTo(true));
        assertThat(service.numberOfWeekendDays, equalTo(2));
    }

    @Test
    void isWeekend_givenADateOnSunday_returnTrue() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 20);

        RentalTimeService service = new RentalTimeService(checkoutDate, 4L);

        assertThat(service.rentalFallsOnWeekend, equalTo(true));
        assertThat(service.numberOfWeekendDays, equalTo(1));
    }

    @Test
    void isWeekend_givenADateOnNonSatOrSun_returnFalse() {
        LocalDate checkoutDate = LocalDate.of(2024, 4, 24);

        RentalTimeService service = new RentalTimeService(checkoutDate, 2L);

        assertThat(service.rentalFallsOnWeekend, equalTo(false));
        assertThat(service.numberOfWeekendDays, equalTo(0));
    }

    @Test
    void getHolidays_hasJuly4thandLaborday_returnListSize2() {
        LocalDate checkoutDate = LocalDate.of(2023, 7, 2);

        RentalTimeService service = new RentalTimeService(checkoutDate, 64L);
        assertThat(service.holidays.size(), equalTo(2));
        assertThat(service.holidays.get(0).getHolidayName(), equalTo("July 4th"));
        assertThat(service.holidays.get(1).getHolidayName(), equalTo("Labor Day"));
    }

    @Test
    void constructorSetsCorrectDates() {
        LocalDate expectedCheckoutDate = LocalDate.of(2024, 4, 20);
        LocalDate expectedDueDate = LocalDate.of(2024, 4, 24);
        List<LocalDate> expectRentalDates = new ArrayList<>();
        expectRentalDates.add(LocalDate.of(2024, 4, 21));
        expectRentalDates.add(LocalDate.of(2024, 4, 22));
        expectRentalDates.add(LocalDate.of(2024, 4, 23));
        expectRentalDates.add(LocalDate.of(2024, 4, 24));

        RentalTimeService service = new RentalTimeService(LocalDate.of(2024, 4, 20), 4L);

        assertThat(service.checkoutDate, equalTo(expectedCheckoutDate));
        assertThat(service.dueDate, equalTo(expectedDueDate));
        assertThat(service.rentalDates.size(), equalTo(4));
        assertThat(service.rentalDates.get(0), equalTo(expectRentalDates.get(0)));
        assertThat(service.rentalDates.get(1), equalTo(expectRentalDates.get(1)));
        assertThat(service.rentalDates.get(2), equalTo(expectRentalDates.get(2)));
        assertThat(service.rentalDates.get(3), equalTo(expectRentalDates.get(3)));
    }
}