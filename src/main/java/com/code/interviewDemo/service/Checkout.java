package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.RentalAgreement;
import com.code.interviewDemo.domain.Tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkout {

    public RentalAgreement checkout(String toolCode, String checkoutDateStr, Long rentalDayCount, int discountPercent ) {
        Tools tool = validateToolExistsAndReturnTool(toolCode);
        validateRentalCount(rentalDayCount);
        validateDiscount(discountPercent);
        LocalDate checkoutDate = validateAndConvertToDateObject(checkoutDateStr);

        RentalTimeService rental = new RentalTimeService(checkoutDate, rentalDayCount);
        CalculateCost cost = new CalculateCost(rental, discountPercent, tool);
        RentalAgreementService service = new RentalAgreementService(rental, cost);
        return service.getRentalAgreement();
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

    private void validateRentalCount(Long rentalDayCount) {
        if(rentalDayCount < 1) {
            throw new RuntimeException("Rental day count of " + rentalDayCount + " is not valid. Please increase number of rental days.");
        }
    }

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

    private Tools validateToolExistsAndReturnTool(String toolCode) {
        Tools tool = findToolInCatalog(toolCode);
        if(tool == null) {
            throw new RuntimeException("Tool code is incorrect, please fix checkout tool code");
        }
        return tool;
    }

    private Tools findToolInCatalog(String toolCode) {
        Tools tool = null;
        for (Tools product : Tools.values()) {
            if (product.getToolCode().equalsIgnoreCase(toolCode)) {
                tool = product;
            }
        }
        return tool;
    }
}
