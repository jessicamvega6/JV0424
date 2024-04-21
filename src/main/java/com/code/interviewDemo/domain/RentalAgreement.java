package com.code.interviewDemo.domain;

import java.util.Date;

public class RentalAgreement {

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private Date checkoutDate;
    private Date dueDate;
    private Long dailyRentalCharge;
    private Integer chargeDays;
    private Long preDiscountCharge;
    private Long discountCharge;
    private int discountPercent;
    private Long discountAmount;
    private Long finalCharge;

    @Override
    public String toString() {
        return
                "toolCode='" + toolCode + '\'' + '\n' +
                "toolType='" + toolType + '\'' + '\n' +
                "toolBrand='" + toolBrand + '\'' + '\n' +
                "rentalDays=" + rentalDays + '\n' +
                "checkoutDate=" + checkoutDate + '\n' +
                "dueDate=" + dueDate + '\n' +
                "dailyRentalCharge=" + dailyRentalCharge + '\n' +
                "chargeDays=" + chargeDays + '\n' +
                "preDiscountCharge=" + preDiscountCharge + '\n' +
                "discountCharge=" + discountCharge + '\n' +
                "discountPercent=" + discountPercent + '\n' +
                "discountAmount=" + discountAmount + '\n' +
                "finalCharge=" + finalCharge;
    }

    public void printToConsole() {
        System.out.println(this);
    }
}
