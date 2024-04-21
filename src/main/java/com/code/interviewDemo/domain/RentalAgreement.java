package com.code.interviewDemo.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    final DateTimeFormatter FORMMATER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private Double dailyRentalCharge;
    private Integer chargeDays;
    private Double preDiscountCharge;
    private Double discountCharge;
    private int discountPercent;
    private Double discountAmount;
    private Double finalCharge;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Double getDailyRentalCharge() {
        return Math.round(dailyRentalCharge * 100.0) / 100.0;
    }

    public void setDailyRentalCharge(Double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public Integer getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(Integer chargeDays) {
        this.chargeDays = chargeDays;
    }

    public Double getPreDiscountCharge() {
        return Math.round(preDiscountCharge * 100.0) / 100.0;
    }

    public void setPreDiscountCharge(Double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public Double getDiscountCharge() {
        return Math.round(discountCharge * 100.0) / 100.0;
    }

    public void setDiscountCharge(Double discountCharge) {
        this.discountCharge = discountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getDiscountAmount() {
        return Math.round(discountAmount * 100.0) / 100.0;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getFinalCharge() {
        return Math.round(finalCharge * 100.0) / 100.0;
    }

    public void setFinalCharge(double finalCharge) {
        this.finalCharge = finalCharge;
    }

    public String getDueDate() {
        return dueDate.format(FORMMATER);
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCheckoutDate() {
        return checkoutDate.format(FORMMATER);
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return
                "toolCode='" + toolCode + '\'' + '\n' +
                "toolType='" + toolType + '\'' + '\n' +
                "toolBrand='" + toolBrand + '\'' + '\n' +
                "rentalDays=" + rentalDays + '\n' +
                "checkoutDate=" + checkoutDate.format(FORMMATER) + '\n' +
                "dueDate=" + dueDate.format(FORMMATER) + '\n' +
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
