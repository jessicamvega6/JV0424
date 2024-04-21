package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.RentalAgreement;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isNotNull;


public class CheckoutTest {

    Checkout checkout = new Checkout();

    @Test(expected = RuntimeException.class)
    public void checkout_discountOver100_fails() throws Exception {
        checkout.checkout("JAKR", "9/3/15", 5, 101);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_discountNegative_fails() throws Exception {
        checkout.checkout("JAKR", "9/3/15", 5, -11);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_negativeNumberOfDaysRented_fails() throws Exception {
        checkout.checkout("JAKR", "9/13/14", -2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_rentalDateIsNull_fails() throws Exception {
        checkout.checkout("JAKR", "9/13/14", null, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_toolDoesntExist_fails() throws Exception {
        checkout.checkout("JR", "9/13/14", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_toolIsNull_fails() throws Exception {
        checkout.checkout(null, "9/13/14", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_checkoutDateInFuture_fails() throws Exception {
        LocalDate weekFromNow = LocalDate.now().plusWeeks(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/dd/yy");
        String futureDate = weekFromNow.format(dateFormatter);
        checkout.checkout("JR", futureDate, 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_checkoutDateIsNull_fails() throws Exception {
        checkout.checkout("JR", null, 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatyyyymdd_fails() throws Exception {
        checkout.checkout("JR", "2014/9/13", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatyyyymmdd_fails() throws Exception {
        checkout.checkout("JR", "2014/09/13", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatWithDashes_fails() throws Exception {
        checkout.checkout("JR", "9-13-2014", 2, 0);
    }

    @Test
    public void checkout_toolCodeWeirdCapitalization_DoesNotFail() throws Exception {
        RentalAgreement agreement = checkout.checkout("JakR", "9/13/14", 2, 0);
        assertNotNull(agreement);
    }

}