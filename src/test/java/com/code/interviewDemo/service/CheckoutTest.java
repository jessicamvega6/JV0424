package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.RentalAgreement;
import org.junit.Test;


public class CheckoutTest {

    Checkout checkout = new Checkout();

    @Test(expected = RuntimeException.class)
    public void checkout_discountOver100_fails() throws Exception {
        checkout.checkout("JAKR", "9/3/15", 5, 101);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_discountNegative_fails() throws Exception {
        RentalAgreement agreement = checkout.checkout("JAKR", "9/3/15", 5, -11);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_negativeNumberOfDaysRented_fails() throws Exception {
        RentalAgreement agreement = checkout.checkout("JAKR", "9/13/14", -2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_toolDoesntExist_fails() throws Exception {
        RentalAgreement agreement = checkout.checkout("JR", "9/13/14", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_checkoutDateInFuture_fails() throws Exception {
        RentalAgreement agreement = checkout.checkout("JR", "9/13/14", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatyyyymdd_fails() throws Exception {
        RentalAgreement agreement = checkout.checkout("JR", "2014/9/13", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatyyyymmdd_fails() throws Exception {
        RentalAgreement agreement = checkout.checkout("JR", "2014/09/13", 2, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatWithDashes_fails() throws Exception {
        RentalAgreement agreement = checkout.checkout("JR", "9-13-2014", 2, 0);
    }

}