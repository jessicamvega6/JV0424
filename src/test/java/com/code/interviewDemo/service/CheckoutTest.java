package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.RentalAgreement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    Checkout checkout = new Checkout();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkout_discountOver100_fails() {
        RentalAgreement agreement = checkout.checkout("JAKR", "9/3/15", 5, 101);
        assertThat(agreement, equalTo(null));
    }

    @Test
    void checkout_discountNegative_fails() {
        RentalAgreement agreement = checkout.checkout("JAKR", "9/3/15", 5, -11);
        assertThat(agreement, equalTo(null));
    }

    @Test
    void checkout_negativeNumberOfDaysRented_fails() {
        RentalAgreement agreement = checkout.checkout("JAKR", "9/13/14", -2, 0);
        assertThat(agreement, equalTo(null));
    }

    @Test
    void checkout_toolDoesntExist_fails() {
        RentalAgreement agreement = checkout.checkout("JR", "9/13/14", 2, 0);
        assertThat(agreement, equalTo(null));
    }

    @Test
    void checkout_checkoutDateInFuture_fails() {
        RentalAgreement agreement = checkout.checkout("JR", "9/13/14", 2, 0);
        assertThat(agreement, equalTo(null));
    }

}