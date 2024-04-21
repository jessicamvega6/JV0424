package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.RentalAgreement;
import com.code.interviewDemo.domain.Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkout {

    public RentalAgreement checkout(String toolCode, String checkoutDateStr, Integer rentalDayCount, int discountPercent ) {
        validateTool(toolCode);
        validateCheckoutDate(checkoutDateStr);
        LocalDate checkoutDate = validateAndConvertToDateObject(checkoutDateStr);
        validateRentalCount(rentalDayCount);
        validateDiscount(discountPercent);

        RentalTimeService rental = new RentalTimeService(checkoutDate, rentalDayCount);

        return new RentalAgreement();
    }

    private LocalDate validateAndConvertToDateObject(String checkoutDateStr) {
        if(checkIfNull(checkoutDateStr)) {
            throw new RuntimeException("Checkout date is null. Please fix checkout date");
        }

        LocalDate formatedDateMdyy = checkAndConvertIfMatchingFormat(checkoutDateStr, "^\\d{1}/\\d{1}/\\d{2}$", "M/d/yy");
        LocalDate formatedDateMMddyy = checkAndConvertIfMatchingFormat(checkoutDateStr, "^\\d{2}/\\d{2}/\\d{2}$", "MM/dd/yy");

        if(formatedDateMdyy == null && formatedDateMMddyy == null) {
            throw new RuntimeException("Checkout date format of " + checkoutDateStr + " is incorrect, please correct date.");
        }

        LocalDate checkoutDate = checkIfDateIsInFuture(formatedDateMdyy, formatedDateMMddyy);
        return checkoutDate;
    }

    private void validateDiscount(int discountPercent) {
        if(discountPercent > 100 || discountPercent < 0) {
            throw new RuntimeException("This discount of " + discountPercent + " is not valid, please fix discount");
        }
    }

    private void validateRentalCount(Integer rentalDayCount) {
        if(rentalDayCount < 1) {
            throw new RuntimeException("Rental day count of " + rentalDayCount + " is not valid. Please increase number of rental days.");
        }
    }

    private void validateCheckoutDate(String checkoutDate) {

    }

//    private void checkIfDateIsInFuture(String checkoutDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yy");
//        LocalDate localDate = LocalDate.parse(checkoutDate, formatter);
//        if(localDate.isAfter(LocalDate.now())) {
//            throw new RuntimeException("Checkout date is in the future. Sorry we do not hold tools for future dates");
//        }
//    }

    private LocalDate checkIfDateIsInFuture(LocalDate dateFormat1, LocalDate dateFormat2) {
        LocalDate date = dateFormat1 != null ? dateFormat1 : dateFormat2;
        if(date.isAfter(LocalDate.now())) {
            throw new RuntimeException("Checkout date is in the future. Sorry we do not hold tools for future dates");
        }

        return date;
    }

    private LocalDate checkAndConvertIfMatchingFormat(String checkoutDate, String pattern, String format) {
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(checkoutDate);
        if(mat.matches()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(checkoutDate, formatter);
        }
        return null;
    }

    private boolean checkIfNull(String value) {
        return value == null;
    }

    private void validateTool(String toolCode) {
        if(!toolExistsInCatalog(toolCode)){
            throw new RuntimeException("Tool code is incorrect, please fix checkout tool code");
        };
    }

    private boolean toolExistsInCatalog(String toolCode) {
        for (Tools product : Tools.values()) {
            if (product.getToolCode().equalsIgnoreCase(toolCode)) {
                return true;
            }
        }
        return false;
    }
}
