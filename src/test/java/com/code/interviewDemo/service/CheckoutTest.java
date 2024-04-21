package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.RentalAgreement;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckoutTest {


    Checkout checkout = new Checkout();

    @Test(expected = RuntimeException.class)
    public void checkout_discountOver100_fails() throws Exception {
        checkout.checkout("JAKR", "9/3/15", 5L, 101);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_discountNegative_fails() throws Exception {
        checkout.checkout("JAKR", "9/3/15", 5L, -11);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_negativeNumberOfDaysRented_fails() throws Exception {
        checkout.checkout("JAKR", "09/13/14", -2L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_zeroDaysRented_fails() throws Exception {
        checkout.checkout("JAKR", "09/13/14", 0L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_rentalDateIsNull_fails() throws Exception {
        checkout.checkout("JAKR", "09/13/14", null, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_toolDoesntExist_fails() throws Exception {
        checkout.checkout("JR", "09/13/14", 2L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_toolIsNull_fails() throws Exception {
        checkout.checkout(null, "09/13/14", 2L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_checkoutDateInFuture_fails() throws Exception {
        LocalDate weekFromNow = LocalDate.now().plusWeeks(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/dd/yy");
        String futureDate = weekFromNow.format(dateFormatter);
        checkout.checkout("JR", futureDate, 2L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_checkoutDateIsNull_fails() throws Exception {
        checkout.checkout("JAKR", null, 2L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatyyyymdd_fails() throws Exception {
        checkout.checkout("JR", "2014/9/13", 2L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatyyyymmdd_fails() throws Exception {
        checkout.checkout("JR", "2014/09/13", 2L, 0);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_dateInFormatWithDashes_fails() throws Exception {
        checkout.checkout("JR", "9-13-2014", 2L, 0);
    }

    @Test
    public void checkout_toolCodeWeirdCapitalization_DoesNotFail() throws Exception {
        RentalAgreement agreement = checkout.checkout("JakR", "09/13/14", 2L, 0);
        assertNotNull(agreement);
    }

    @Test
    public void checkout_dateInFormatMdyy_doesNotFail() throws Exception {
        RentalAgreement agreement = checkout.checkout("JAKR", "9/3/14", 2L, 0);
        assertNotNull(agreement);
    }

    @Test
    public void checkout_dateInFormatMMddyy_doesNotFail() throws Exception {
        RentalAgreement agreement = checkout.checkout("JAKR", "12/13/14", 2L, 0);
        assertNotNull(agreement);
    }

    @Test(expected = RuntimeException.class)
    public void checkout_test1_returnsCompletedAgreement() {
        checkout.checkout("JAKR", "12/13/14", 5L, 101);
    }

    @Test
    public void checkout_test2_returnsCompletedAgreement() {
        BigDecimal expectedPreDiscountCharge = BigDecimal.valueOf(3.98).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(.40).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedFinalCharge = BigDecimal.valueOf(3.58).setScale(2, BigDecimal.ROUND_HALF_UP);

        RentalAgreement agreement = checkout.checkout("LADW", "7/2/20", 3L, 10);
        assertThat(agreement.getToolCode(), equalTo("LADW"));
        assertThat(agreement.getToolType(), equalTo("Ladder"));
        assertThat(agreement.getToolBrand(), equalTo("Werner"));
        assertThat(agreement.getRentalDays(), equalTo(3L));
        assertThat(agreement.getCheckoutDate(), equalTo("07/02/2020"));
        assertThat(agreement.getDueDate(), equalTo("07/05/2020"));
        assertThat(agreement.getDailyRentalCharge(), equalTo(1.99));
        assertThat(agreement.getChargeDays(), equalTo(2L));
        assertThat(agreement.getDiscountPercent(), equalTo(10));
        assertThat(agreement.getPreDiscountCharge(), equalTo(expectedPreDiscountCharge));
        assertThat(agreement.getDiscountAmount(), equalTo(expectedDiscountAmount));
        assertThat(agreement.getFinalCharge(), equalTo(expectedFinalCharge));
    }

    @Test
    public void checkout_test3_returnsCompletedAgreement() {
        BigDecimal expectedPreDiscountCharge = BigDecimal.valueOf(4.47).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(1.12).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedFinalCharge = BigDecimal.valueOf(3.35).setScale(2, BigDecimal.ROUND_HALF_UP);

        RentalAgreement agreement = checkout.checkout("CHNS", "7/2/15", 5L, 25);
        assertThat(agreement.getToolCode(), equalTo("CHNS"));
        assertThat(agreement.getToolType(), equalTo("Chainsaw"));
        assertThat(agreement.getToolBrand(), equalTo("Stihl"));
        assertThat(agreement.getRentalDays(), equalTo(5L));
        assertThat(agreement.getCheckoutDate(), equalTo("07/02/2015"));
        assertThat(agreement.getDueDate(), equalTo("07/07/2015"));
        assertThat(agreement.getDailyRentalCharge(), equalTo(1.49));
        assertThat(agreement.getChargeDays(), equalTo(3L));
        assertThat(agreement.getPreDiscountCharge(), equalTo(expectedPreDiscountCharge));
        assertThat(agreement.getDiscountPercent(), equalTo(25));
        assertThat(agreement.getDiscountAmount(), equalTo(expectedDiscountAmount));
        assertThat(agreement.getFinalCharge(), equalTo(expectedFinalCharge));
    }

    @Test
    public void checkout_test4_returnsCompletedAgreement() {
        BigDecimal expectedPreDiscountCharge = BigDecimal.valueOf(8.97).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(0).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedFinalCharge = BigDecimal.valueOf(8.97).setScale(2, BigDecimal.ROUND_HALF_UP);

        RentalAgreement agreement = checkout.checkout("JAKD", "9/3/15", 6L, 0);

        assertThat(agreement.getToolCode(), equalTo("JAKD"));
        assertThat(agreement.getToolType(), equalTo("Jackhammer"));
        assertThat(agreement.getToolBrand(), equalTo("DeWalt"));
        assertThat(agreement.getRentalDays(), equalTo(6L));
        assertThat(agreement.getCheckoutDate(), equalTo("09/03/2015"));
        assertThat(agreement.getDueDate(), equalTo("09/09/2015"));
        assertThat(agreement.getDailyRentalCharge(), equalTo(2.99));
        assertThat(agreement.getChargeDays(), equalTo(3L));
        assertThat(agreement.getDiscountPercent(), equalTo(0));;
        assertThat(agreement.getPreDiscountCharge(), equalTo(expectedPreDiscountCharge));
        assertThat(agreement.getDiscountAmount(), equalTo(expectedDiscountAmount));
        assertThat(agreement.getFinalCharge(), equalTo(expectedFinalCharge));
    }

    @Test
    public void checkout_test5_returnsCompletedAgreement() {
        BigDecimal expectedPreDiscountCharge = BigDecimal.valueOf(14.95).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(0).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedFinalCharge = BigDecimal.valueOf(14.95).setScale(2, BigDecimal.ROUND_HALF_UP);

        RentalAgreement agreement = checkout.checkout("JAKR", "7/2/15", 9L, 0);

        assertThat(agreement.getToolCode(), equalTo("JAKR"));
        assertThat(agreement.getToolType(), equalTo("Jackhammer"));
        assertThat(agreement.getToolBrand(), equalTo("Ridgid"));
        assertThat(agreement.getRentalDays(), equalTo(9L));
        assertThat(agreement.getCheckoutDate(), equalTo("07/02/2015"));
        assertThat(agreement.getDueDate(), equalTo("07/11/2015"));
        assertThat(agreement.getDailyRentalCharge(), equalTo(2.99));
        assertThat(agreement.getChargeDays(), equalTo(5L));
        assertThat(agreement.getDiscountPercent(), equalTo(0));
        assertThat(agreement.getPreDiscountCharge(), equalTo(expectedPreDiscountCharge));
        assertThat(agreement.getDiscountAmount(), equalTo(expectedDiscountAmount));
        assertThat(agreement.getFinalCharge(), equalTo(expectedFinalCharge));
    }

    @Test
    public void checkout_test6_returnsCompletedAgreement() {
        BigDecimal expectedPreDiscountCharge = BigDecimal.valueOf(2.99).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(1.50).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal expectedFinalCharge = BigDecimal.valueOf(1.49).setScale(2, BigDecimal.ROUND_HALF_UP);

        RentalAgreement agreement = checkout.checkout("JAKR", "7/2/20", 4L, 50);
        assertThat(agreement.getToolCode(), equalTo("JAKR"));
        assertThat(agreement.getToolType(), equalTo("Jackhammer"));
        assertThat(agreement.getToolBrand(), equalTo("Ridgid"));
        assertThat(agreement.getRentalDays(), equalTo(4L));
        assertThat(agreement.getCheckoutDate(), equalTo("07/02/2020"));
        assertThat(agreement.getDueDate(), equalTo("07/06/2020"));
        assertThat(agreement.getDailyRentalCharge(), equalTo(2.99));
        assertThat(agreement.getChargeDays(), equalTo(1L));
        assertThat(agreement.getDiscountPercent(), equalTo(50));
        assertThat(agreement.getPreDiscountCharge(), equalTo(expectedPreDiscountCharge));
        assertThat(agreement.getDiscountAmount(), equalTo(expectedDiscountAmount));
        assertThat(agreement.getFinalCharge(), equalTo(expectedFinalCharge));
    }


}